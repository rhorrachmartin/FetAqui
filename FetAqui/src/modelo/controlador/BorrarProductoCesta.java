package modelo.controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ejb.DetallesPedidoEJB;
import modelo.ejb.LoggersEJB;
import modelo.ejb.PedidoEJB;
import modelo.ejb.SesionClienteEJB;
import modelo.pojo.Cliente;

/**
 * Controlador encargado de quitar un producto de la cesta de compra
 * 
 * @author ramon
 *
 */
@WebServlet("/BorrarProductoCesta")
public class BorrarProductoCesta extends HttpServlet {

	@EJB
	SesionClienteEJB sesionClienteEJB;

	@EJB
	PedidoEJB pedidoEJB;

	@EJB
	DetallesPedidoEJB detallePedidoEJB;

	@EJB
	LoggersEJB logger;

	private static final long serialVersionUID = 1L;
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	/**
	 * Método doPost encargado de borrar un producto de la cesta de compra
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);
		
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			//Recogemos al usuario cliente de la sesión
			Cliente c = sesionClienteEJB.clienteLogeado(session);
			
			//Comprobamos que existe la sesión y el usuario en la misma
			if (session != null && c != null) {
				
				//Si el parámetro id_detalle existe
				if (request.getParameter("id_detalle") != null) {
					
					//Recogemos los parámetros necesarios
					Integer id_detalle = Integer.valueOf(request.getParameter("id_detalle"));
					Integer id_pedido = Integer.valueOf(request.getParameter("id_pedido"));
					
					//Borramos el producto de la cesta
					pedidoEJB.borrarProductoCesta(id_detalle);
					
					//Actualizamos la cantidad de productos de la cesta en la sesión
					Integer numProductos = pedidoEJB.getNumeroProductos(id_pedido);
					session.removeAttribute("numProductos");
					session.setAttribute("numProductos", numProductos);
					
					//Redirigimos a Cesta
					response.sendRedirect("Cesta");

				} else {
					
					//Si el parámetro id_detalle es nulo redirigimos a Cesta
					response.sendRedirect("Cesta");
				}

			} else {
				
				//Si no hay sesión redirigimos a Principal
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}
}
