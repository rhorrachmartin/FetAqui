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

import modelo.ejb.DetallesPedidoEJB;
import modelo.ejb.LoggersEJB;
import modelo.ejb.PedidoEJB;
import modelo.ejb.SesionClienteEJB;
import modelo.pojo.Cliente;
import modelo.pojo.PedidoDetallado;

/**
 * Clase encargada de cargar cargar un pedido de un cliente
 * 
 * @author ramon
 *
 */
@WebServlet("/VerPedido")
public class VerPedido extends HttpServlet {

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
	static final String PEDIDO_CLIENTE_JSP = "/PedidoCliente.jsp";

	/**
	 * Método doPost encargado de visualizar un pedido en concreto para un usuario
	 * cliente
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Creamos el RequestDispatcher por defecto hacia PEDIDO_CLIENTE_JSP
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PEDIDO_CLIENTE_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {

			// Comprobamos que exista un usuario cliente en la sesión
			Cliente c = sesionClienteEJB.clienteLogeado(session);

			if (c != null) {

				// Si recibimos la id del pedido
				if (request.getParameter("id_pedido") != null) {

					// La recogemos
					Integer id_pedido = Integer.valueOf(request.getParameter("id_pedido"));

					// Si existe el pedido con esa id
					if (pedidoEJB.getPedidoDetalladoPorId(id_pedido) != null) {

						// LO recogemos
						ArrayList<PedidoDetallado> pDetallado = pedidoEJB.getPedidoDetalladoPorId(id_pedido);

						// Si existe pero está vacío mostramos un mensaje de error
						if (pDetallado.size() == 0) {
							String error = "No existen datos para este pedido";

							request.setAttribute("error", error);
							request.setAttribute("id_pedido", id_pedido);
							rs.forward(request, response);
						} else {

							// Si tiene contenido lo pasamos a la request y redirigimos
							request.setAttribute("pedidoDetallado", pDetallado);

							rs.forward(request, response);
						}

					} else {

						// Si no existe el pedido mostramos un mensaje de error
						String error = "No existen datos para este pedido";

						request.setAttribute("error", error);

						rs.forward(request, response);
					}

				} else {
					// Si no recibimos la id del pedido redirigimos a ObtenerPedidosCliente
					response.sendRedirect("ObtenerPedidosCliente");
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
