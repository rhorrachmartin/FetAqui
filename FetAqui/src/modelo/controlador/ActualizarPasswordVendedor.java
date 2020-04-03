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
import modelo.ejb.SesionVendedorEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Poblacion;
import modelo.pojo.Vendedor;

/**
 * Servlet implementation class ActualizarPerfilCliente
 */
@WebServlet("/ActualizarPasswordVendedor")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ActualizarPasswordVendedor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * EJB para trabajar con Usuarios
	 */
	@EJB
	VendedorEJB vendedorEJB;
	/**
	 * EJB para trabajar con sesiones
	 */
	@EJB
	SesionVendedorEJB sesionVendedorEJB;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		if (session == null) {
			response.sendRedirect("Principal");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Creamos el RequestDispatcher por defecto hacia Registro.jsp
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PERFIL_VENDEDOR_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");
		Vendedor vendedorExiste = null;
		String passAntiguo = request.getParameter("passAntiguo");
		String passNuevo1 = request.getParameter("passNuevo1");
		String passNuevo2 = request.getParameter("passNuevo2");
		ArrayList<Poblacion> poblaciones = null;
		try {

			if (vendedor.getNombre() != null) {
				vendedorExiste = vendedorEJB.getVendedor(vendedor.getEmail(), vendedor.getPassword());

				if (vendedorExiste.getPassword().equals(passAntiguo) && passNuevo1.equals(passNuevo2)) {

					vendedorEJB.updatePassword(passNuevo2, vendedorExiste.getId_vendedor());

					Vendedor vendedorActualizado = vendedorEJB.getVendedor(vendedor.getEmail(), passNuevo2);

					request.getSession().setAttribute("vendedor", vendedorActualizado);

					poblaciones = poblacionEJB.getPoblaciones();

					request.setAttribute("poblaciones", poblaciones);

					request.setAttribute("vendedor", vendedorActualizado);

					rs.forward(request, response);
				} else {
					String error = "Las contraseñas no coinciden";
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
		} catch (Exception e) {
			e.getMessage();
		}

	}

}
