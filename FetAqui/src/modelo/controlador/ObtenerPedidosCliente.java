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

import modelo.ejb.CategoriaEJB;
import modelo.ejb.FormatoEJB;
import modelo.ejb.LoggersEJB;
import modelo.ejb.PedidoEJB;
import modelo.ejb.ProductoEJB;
import modelo.pojo.Cliente;
import modelo.pojo.Pedido;

/**
 * Clase encargada de obtener todos los pedidos de un cliente
 * 
 * @author ramon
 *
 */
@WebServlet("/ObtenerPedidosCliente")
public class ObtenerPedidosCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	CategoriaEJB categoriaEJB;

	@EJB
	FormatoEJB formatoEJB;

	@EJB
	ProductoEJB productoEJB;

	@EJB
	PedidoEJB pedidoEJB;

	@EJB
	LoggersEJB logger;

	static final String PEDIDOS_CLIENTE_JSP = "/PedidosCliente.jsp";
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);
		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PEDIDOS_CLIENTE_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {

			// Intentamos recoger al usuario cliente de la sesión
			Cliente cliente = (Cliente) session.getAttribute("cliente");

			// Si hay sesión y usuario cliente en la misma
			if (session != null && cliente.getNombre() != null) {

				// Si existen pedidos asociados a ese cliente
				if (pedidoEJB.getPedidosCliente(cliente.getId_cliente()) != null) {

					// Recogemos todos los pedidos de ese cliente
					ArrayList<Pedido> pedidos = pedidoEJB.getPedidosCliente(cliente.getId_cliente());

					// Si el arraylist está vacío mostramos un mensaje de error
					if (pedidos.isEmpty()) {

						String error = "No existen pedidos";

						// Lo pasamos a la request
						request.setAttribute("error", error);

						// Redirigimos a PEDIDOS_CLIENTE_JSP
						rs.forward(request, response);
					} else {

						// Si tiene contenido lo introducimos en la request
						request.setAttribute("pedidos", pedidos);

						// REdirigimos a PEDIDOS_CLIENTE_JSP
						rs.forward(request, response);
					}

				} else {

					// Si no existen pedidos asociados a ese cliente mostramos un mensaje de error
					String error = "No existen pedidos";

					// Lo pasamos a la request
					request.setAttribute("error", error);

					// Redirigimos a PEDIDOS_CLIENTE_JSP
					rs.forward(request, response);
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
