package modelo.controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ejb.LoggersEJB;
import modelo.ejb.SesionClienteEJB;
import modelo.ejb.SesionVendedorEJB;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	// Constantes
	private static final long serialVersionUID = 1L;
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	static final String LOGIN_JSP = "/Login.jsp";

	// EJB's necesarios
	@EJB
	SesionVendedorEJB sesionVendedorEJB;
	
	@EJB
	SesionClienteEJB sesionClienteEJB;

	@EJB
	LoggersEJB logger;

	/**
	 * Método doGet encargado de cerrar sesión
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		// Miramos si hay sesión y si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			// Comprobamos si hay sesión
			if (session != null && session.getAttribute("cliente") != null) {
				// Llamamos al método correspondiente para cerrar la sesión
				sesionClienteEJB.cerrarSesion(session);
				// Redirigimos
				response.sendRedirect("Principal");
			}
			
			if(session != null && session.getAttribute("vendedor") != null) {
				// Llamamos al método correspondiente para cerrar la sesión
				sesionVendedorEJB.cerrarSesion(session);
				// Redirigimos
				response.sendRedirect("Principal");
			} else {
				// Si no hay sesión, a principal
				response.sendRedirect("Principal");
			}
			
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

}
