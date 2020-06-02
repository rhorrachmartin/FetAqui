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
 * Clase encargada activar y desactivar la venta online de un producto
 * 
 * @author ramon
 *
 */
@WebServlet("/VentaOnlineProducto")
public class VentaOnlineProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	ProductoEJB productoEJB;

	@EJB
	LoggersEJB logger;

	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	/**
	 * Método doPost encargado de activar y desactivar la venta online de un
	 * producto en concreto
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			// Intentamos recoger al usuario vendedor de la sesión
			Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");

			// Si existe
			if (vendedor.getNombre() != null) {

				// Recogemos la id del producto
				Integer idProducto = Integer.valueOf(request.getParameter("producto"));

				// Recogemos el producto en función de la id
				Producto producto = productoEJB.getProductoPorId(idProducto);

				// Si la venta online está desactiva la activamos
				if (producto.getVenta_online() == 0) {

					productoEJB.activarVentaOnline(idProducto);

					response.sendRedirect("ObtenerProductosVendedor");

				} else {

					// Si está activada la desactibamos
					productoEJB.desactivarVentaOnline(idProducto);

					response.sendRedirect("ObtenerProductosVendedor");
				}

			} else {

				// Si no hay sesión redirigimos a Principal
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

}
