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
import modelo.ejb.SesionVendedorEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Vendedor;

/**
 * Controlador encargado de dar de baja un usuario Vendedor
 * 
 * @author ramon
 *
 */
@WebServlet("/BajaVendedor")
public class BajaVendedor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	VendedorEJB vendedorEJB;

	@EJB
	SesionVendedorEJB sesionVendedorEJB;

	@EJB
	LoggersEJB logger;

	static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	static final String HOME_JSP = "/Home.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");

			if (vendedor.getEmail() != null) {
				Integer id_vendedor = vendedor.getId_vendedor();

				vendedorEJB.bajaVendedor(id_vendedor);

				sesionVendedorEJB.cerrarSesion(session);

				response.sendRedirect("Principal");
			} else {
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

}
