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
import modelo.pojo.Pedido;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);

		RequestDispatcher rs = getServletContext().getRequestDispatcher(PEDIDO_CLIENTE_JSP);

		HttpSession session = request.getSession(false);

		try {
			Cliente c = sesionClienteEJB.clienteLogeado(session);

			if (c != null) {

				request.setAttribute("cliente", c);

				if (session.getAttribute("pedido") != null) {
					Pedido pedido = (Pedido) session.getAttribute("pedido");

					ArrayList<PedidoDetallado> pedidoDetallado = pedidoEJB.getPedidoDetalladoPorId(pedido.getId());

					if (pedidoDetallado.isEmpty()) {
						String mensaje = "Cesta vacía";

						Integer numProductos = pedidoEJB.getNumeroProductos(pedido.getId());

						session.setAttribute("numProductos", numProductos);

						request.setAttribute("error", mensaje);

						rs.forward(request, response);
					} else {
						request.setAttribute("pedidoDetallado", pedidoDetallado);

						rs.forward(request, response);
					}

				} else {
					String mensaje = "Sin artículos en la cesta";

					request.setAttribute("error", mensaje);

					rs.forward(request, response);
				}

			} else {
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		RequestDispatcher rs = getServletContext().getRequestDispatcher(PEDIDO_CLIENTE_JSP);

		HttpSession session = request.getSession(false);

		try {
			Cliente c = sesionClienteEJB.clienteLogeado(session);

			if (c != null) {

				if (request.getParameter("id_pedido") != null) {

					Integer id_pedido = Integer.valueOf(request.getParameter("id_pedido"));

					if (pedidoEJB.getPedidoDetalladoPorId(id_pedido) != null) {

						ArrayList<PedidoDetallado> pDetallado = pedidoEJB.getPedidoDetalladoPorId(id_pedido);

						if (pDetallado.size() == 0) {
							String error = "No existen datos para este pedido";

							request.setAttribute("error", error);
							request.setAttribute("id_pedido", id_pedido);
							rs.forward(request, response);
						} else {
							request.setAttribute("pedidoDetallado", pDetallado);

							rs.forward(request, response);
						}

					} else {

						String error = "No existen datos para este pedido";

						request.setAttribute("error", error);

						rs.forward(request, response);
					}

				} else {

					response.sendRedirect("ObtenerPedidosCliente");
				}

			} else {
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}
}
