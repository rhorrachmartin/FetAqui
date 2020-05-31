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
import modelo.ejb.ProductoEJB;
import modelo.pojo.Producto;
import modelo.pojo.Vendedor;

/**
 * Controlador encargado de eliminar un producto de un Vendedor
 * 
 * @author ramon
 *
 */
@WebServlet("/EliminarProducto")
public class EliminarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	ProductoEJB productoEJB;

	@EJB
	LoggersEJB logger;

	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			if (session == null && session.getAttribute("vendedor") == null
					&& session.getAttribute("cliente") == null) {
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {

			Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");

			Integer idProducto = Integer.valueOf(request.getParameter("producto"));

			Producto producto = productoEJB.getProductoPorId(idProducto);

			if (vendedor.getNombre() != null) {

				if (producto != null) {

					productoEJB.borrarProducto(idProducto);

					response.sendRedirect("ObtenerProductosVendedor");

				} else {

					response.sendRedirect("ObtenerProductosVendedor");
				}

			} else {
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

}
