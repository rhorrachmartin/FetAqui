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
import modelo.ejb.PedidoEJB;
import modelo.pojo.Cliente;

/**
 * Controlador encargado de anular un pedido
 * 
 * @author ramon
 *
 */
@WebServlet("/AnularPedido")
public class AnularPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	PedidoEJB pedidoEJB;
	
	@EJB
	LoggersEJB logger;
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	
	/**
	 * Método doPost encargado de anular un pedido
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			
			//Recogemos el usuario CLiente de la sesión
			Cliente cliente = (Cliente) session.getAttribute("cliente");
			
			//Si existe el usuario
			if (cliente.getNombre() != null) {
				
				//Recogemos el id del pedido de la request.
				Integer id_pedido = Integer.valueOf(request.getParameter("id_pedido"));
				
				//Si el pedido del pedido existe
				if (id_pedido != null) {
					
					//Borramos el pedido de la BD
					pedidoEJB.borrarPedidoPorId(id_pedido);
					
					//Borramos el pedido de la sesión
					session.removeAttribute("pedido");
					
					//Redirigimos a ObtenerPedidosCliente
					response.sendRedirect("ObtenerPedidosCliente");

				} else {
					//Si no existe redirigimos a ObtenerPedidosCliente
					response.sendRedirect("ObtenerPedidosCliente");
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
