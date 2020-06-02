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

	/**
	 * Método doPost encargado de eliminar un producto de un usuario Vendedor
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			
			//Intentamos recoger al usuario Vendedor de la sesión
			Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");
			
			//Si la sesión existe y el usuario Vendedor también
			if (session != null && vendedor.getNombre() != null) {
				
				//Recogemos el parámetro id producto
				Integer idProducto = Integer.valueOf(request.getParameter("producto"));
				
				//Obtenemos el producto a través de su id
				Producto producto = productoEJB.getProductoPorId(idProducto);
				
				//Si existe
				if (producto != null) {
					
					//Lo borramos
					productoEJB.borrarProducto(idProducto);
					
					//REdirigimos a ObtenerProductosVendedor
					response.sendRedirect("ObtenerProductosVendedor");

				} else {
					//Si no existe, redirigimos a ObtenerProductosVendedor
					response.sendRedirect("ObtenerProductosVendedor");
				}

			} else {
				//Si no existe la sesión redirigimos a Principal
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

}
