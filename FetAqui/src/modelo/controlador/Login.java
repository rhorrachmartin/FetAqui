package modelo.controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ejb.ClienteEJB;
import modelo.ejb.LoggersEJB;
import modelo.ejb.SesionClienteEJB;
import modelo.ejb.SesionVendedorEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Cliente;
import modelo.pojo.Vendedor;

/**
 * Clase encarga de logear al usuario en la app
 * 
 * @author ramon
 *
 */
@WebServlet("/Login")

public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@EJB
	VendedorEJB vendedorEJB;

	@EJB
	ClienteEJB clienteEJB;

	@EJB
	SesionVendedorEJB sesionVendedorEJB;

	@EJB
	SesionClienteEJB sesionClienteEJB;

	@EJB
	LoggersEJB logger;

	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	/**
	 * Método doPost que Logea al usuario en la APP
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType(CONTENT_TYPE);
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			// Recogemos los datos necesarios
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			// Buscamos al usuario en BD
			Vendedor vendedor = null;
			Cliente c = null;

			// Si el usuario no existe con esos parámetros devolvemos un error
			if (vendedorEJB.getVendedorEmailPass(email, password) == null
					&& clienteEJB.getClienteEmailPass(email, password) == null) {

				response.sendRedirect("Principal?error=Usuario inexistente");

			} else if (vendedorEJB.getVendedorEmailPass(email, password) != null) {

				vendedor = vendedorEJB.getVendedor(email, password);

				if (vendedor.getActivado() == 1) {
					// Iniciamos la sesión
					session = request.getSession(true);
					sesionVendedorEJB.crearSesion(session, vendedor);
					request.setAttribute("vendedor", vendedor);
					response.sendRedirect("PaginaPropioVendedor");
				} else {
					response.sendRedirect("Principal?error=Usuario no activado");
				}

			} else {

				c = clienteEJB.getCliente(email, password);
				if (c.getActivado() == 1) {
					// Iniciamos la sesión
					session = request.getSession(true);
					sesionClienteEJB.crearSesion(session, c);
					response.sendRedirect("Principal");
				} else {
					response.sendRedirect("Principal?error=Usuario no activado");
				}

			}
		} catch (

		Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

}
