package modelo.controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ejb.LoggersEJB;
import modelo.ejb.PoblacionEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Poblacion;
import modelo.pojo.Vendedor;

/**
 * Servlet implementation class ActualizarPerfilCliente
 */
@WebServlet("/VentasOnline")
public class VentasOnline extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * EJB para trabajar con Usuarios
	 */
	@EJB
	VendedorEJB vendedorEJB;

	@EJB
	PoblacionEJB poblacionEJB;

	/**
	 * EJB para trabajar con los logger
	 */
	@EJB
	LoggersEJB logger;

	static final String ADMINISTRAR_PAGINA_VENDEDOR_JSP = "/AdministrarPaginaVendedor.jsp";
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		if (session == null) {
			response.sendRedirect("Principal");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Creamos el RequestDispatcher por defecto hacia Registro.jsp
		RequestDispatcher rs = getServletContext().getRequestDispatcher(ADMINISTRAR_PAGINA_VENDEDOR_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");
		ArrayList<Poblacion> poblaciones = null;
		try {
			Boolean ventaOnline = Boolean.parseBoolean(request.getParameter("ventaOnline"));
			System.out.println(ventaOnline);
			if (vendedor.getNombre() != null) {
				
				if (!ventaOnline) {
					vendedorEJB.activarVentaOnline(vendedor.getId_vendedor());

					Vendedor vendedorActualizado = vendedorEJB.getVendedor(vendedor.getEmail(), vendedor.getPassword());

					request.getSession().setAttribute("vendedor", vendedorActualizado);

					poblaciones = poblacionEJB.getPoblaciones();

					request.setAttribute("poblaciones", poblaciones);
					request.setAttribute("vendedor", vendedorActualizado);
					rs.forward(request, response);
				} else {
					vendedorEJB.desactivarVentaOnline(vendedor.getId_vendedor());

					Vendedor vendedorActualizado = vendedorEJB.getVendedor(vendedor.getEmail(), vendedor.getPassword());
					poblaciones = poblacionEJB.getPoblaciones();
					request.setAttribute("poblaciones", poblaciones);
					request.setAttribute("vendedor", vendedorActualizado);
					rs.forward(request, response);
				}

			} else {
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			e.getMessage();
		}

	}

}
