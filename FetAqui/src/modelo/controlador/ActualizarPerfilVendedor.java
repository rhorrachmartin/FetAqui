package modelo.controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ejb.DireccionEJB;
import modelo.ejb.LoggersEJB;
import modelo.ejb.PoblacionEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Direccion;
import modelo.pojo.Poblacion;
import modelo.pojo.Vendedor;

/**
 * Servlet implementation class ActualizarPerfilCliente
 */
@WebServlet("/ActualizarPerfilVendedor")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ActualizarPerfilVendedor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * EJB para trabajar con Usuarios
	 */
	@EJB
	VendedorEJB vendedorEJB;

	@EJB
	DireccionEJB direccionEJB;

	@EJB
	PoblacionEJB poblacionEJB;

	/**
	 * EJB para trabajar con los logger
	 */
	@EJB
	LoggersEJB logger;

	static final String PERFIL_VENDEDOR_JSP = "/PerfilVendedor.jsp";
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Creamos el RequestDispatcher por defecto hacia Registro.jsp
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PERFIL_VENDEDOR_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");
		Vendedor vendedorExiste = null;
		String nif = request.getParameter("nif");
		String nombre = request.getParameter("nombre");
		String telefono = request.getParameter("telefono");
		String passAntiguo = request.getParameter("passAntiguo");
		String direccion = request.getParameter("direccion");

		Direccion dir = new Direccion();
		ArrayList<Poblacion> poblaciones = null;

		Integer id_poblacion = Integer.valueOf(request.getParameter("poblacion"));
		if (vendedor.getNombre() != null) {
			vendedorExiste = vendedorEJB.getVendedor(vendedor.getEmail(), vendedor.getPassword());
			
			if (vendedorExiste.getPassword().equals(passAntiguo)) {
				
				vendedorEJB.updateTelefono(telefono, vendedorExiste.getId_vendedor());
				vendedorEJB.updateNombre(nombre, vendedorExiste.getId_vendedor());
				vendedorEJB.updateNif(nif, vendedorExiste.getId_vendedor());

				if (!vendedorExiste.getDireccion().equals(direccion)
						|| vendedorExiste.getIdPoblacion() != id_poblacion) {
					dir.setDireccion(direccion);
					dir.setId_poblacion(id_poblacion);
					direccionEJB.insertarDireccion(dir);
					vendedorEJB.updateDireccion(vendedorExiste.getId_vendedor());
				}

				Vendedor vendedorActualizado = vendedorEJB.getVendedor(vendedor.getEmail(), vendedor.getPassword());

				request.getSession().setAttribute("vendedor", vendedorActualizado);

				poblaciones = poblacionEJB.getPoblaciones();

				request.setAttribute("poblaciones", poblaciones);
				request.setAttribute("vendedor", vendedorActualizado);
				rs.forward(request, response);
				
			} else {
				
				String error = "Contraseña incorrecta";
				Vendedor vendedor3 = (Vendedor) session.getAttribute("vendedor");
				poblaciones = poblacionEJB.getPoblaciones();
				request.setAttribute("poblaciones", poblaciones);
				request.setAttribute("vendedor", vendedor3);
				request.setAttribute("error", error);
				rs.forward(request, response);
			}

		} else {
			response.sendRedirect("Principal");
		}

	}

}
