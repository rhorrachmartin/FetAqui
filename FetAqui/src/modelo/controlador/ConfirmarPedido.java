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
import modelo.ejb.MailOfficeEJB;
import modelo.ejb.PedidoEJB;
import modelo.ejb.SesionClienteEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Cliente;
import modelo.pojo.Pedido;
import modelo.pojo.PedidoDetallado;
import modelo.pojo.Vendedor;
import vista.HtmlEmail;

/**
 * Servlet implementation class Carrito
 */
@WebServlet("/ConfirmarPedido")
public class ConfirmarPedido extends HttpServlet {

	@EJB
	SesionClienteEJB sesionClienteEJB;

	@EJB
	PedidoEJB pedidoEJB;

	@EJB
	DetallesPedidoEJB detallePedidoEJB;

	@EJB
	VendedorEJB vendedorEJB;

	@EJB
	MailOfficeEJB mailOfficeEJB;

	private static final long serialVersionUID = 1L;
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	static final String CONFIRMAR_PEDIDO_JSP = "/ConfirmarPedido.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);
		RequestDispatcher rs = getServletContext().getRequestDispatcher(CONFIRMAR_PEDIDO_JSP);

		HttpSession session = request.getSession(false);

		Cliente c = sesionClienteEJB.clienteLogeado(session);

		if (c != null) {

			if (request.getParameter("id_pedido") != null) {

				Integer id_pedido = Integer.valueOf(request.getParameter("id_pedido"));

				if (pedidoEJB.getPedidoDetalladoPorId(id_pedido) != null) {

					ArrayList<PedidoDetallado> pDetallado = pedidoEJB.getPedidoDetalladoPorId(id_pedido);
					double total = Double.valueOf(request.getParameter("totalPedido"));

					if (c.getDireccion() != null) {

						request.setAttribute("pedidoDetallado", pDetallado);
						request.setAttribute("total", total);
						rs.forward(request, response);

					} else {

						String error = "Para realizar una compra actualice su perfil de usuario";

						session.setAttribute("error", error);
						response.sendRedirect("ObtenerTodosProductos");
					}

				} else {
					String error = "No hay datos para este pedido.";

					session.setAttribute("error", error);
					response.sendRedirect("ObtenerTodosProductos");
				}

			} else {
				response.sendRedirect("ObtenerTodosProductos");
			}

		} else {
			response.sendRedirect("Principal");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		HttpSession session = request.getSession(false);

		Cliente c = sesionClienteEJB.clienteLogeado(session);

		if (c != null) {

			if (session.getAttribute("pedido") != null) {

				if (c.getDireccion() != null) {

					Pedido pedido = (Pedido) session.getAttribute("pedido");

					Integer id_pedido = pedido.getId();

					ArrayList<Vendedor> vendedores = pedidoEJB.getVendedoresPorPedido(id_pedido);

					for (Vendedor ve : vendedores) {
						ArrayList<PedidoDetallado> pDetalladoVendedor = pedidoEJB
								.getPedidoDetalladoPorIdVendedorYpedido(ve.getId_vendedor(), id_pedido);

						HtmlEmail pedidoVendedores = new HtmlEmail();

						String mensajeVendedor = pedidoVendedores.mailPedidoVendedor(pDetalladoVendedor);

						mailOfficeEJB.sendMail2(ve.getEmail(), "fetaquimallorca@gmail.com",
								"Nuevo pedido de fetaquimallorca.com", mensajeVendedor);

						pedidoEJB.updatePedidoAconfirmado(id_pedido);

					}

					ArrayList<PedidoDetallado> pedidoDetalladoCliente = pedidoEJB
							.getPedidoDetalladoPorId(pedido.getId());

					HtmlEmail pedidoCliente = new HtmlEmail();

					String mensajeCliente = pedidoCliente.mailPedidoCliente(pedidoDetalladoCliente);

					mailOfficeEJB.sendMail2(c.getEmail(), "fetaquimallorca@gmail.com",
							"Confirmación de su pedido de fetaquimallorca.com", mensajeCliente);

					session.removeAttribute("pedido");

					response.sendRedirect("ObtenerPedidosCliente");

				} else {

					String error = "Para realizar una compra actualice su perfil de usuario";

					session.setAttribute("error", error);
					response.sendRedirect("ObtenerTodosProductos");
				}

			} else {

				if (c.getDireccion() != null) {

					Integer id_pedido = Integer.valueOf(request.getParameter("id_pedido"));

					ArrayList<Vendedor> vendedores = pedidoEJB.getVendedoresPorPedido(id_pedido);

					for (Vendedor ve : vendedores) {
						ArrayList<PedidoDetallado> pDetalladoVendedor = pedidoEJB
								.getPedidoDetalladoPorIdVendedorYpedido(ve.getId_vendedor(), id_pedido);

						HtmlEmail pedidoVendedores = new HtmlEmail();

						String mensajeVendedor = pedidoVendedores.mailPedidoVendedor(pDetalladoVendedor);

						mailOfficeEJB.sendMail2(ve.getEmail(), "fetaquimallorca@gmail.com",
								"Nuevo pedido de fetaquimallorca.com", mensajeVendedor);

						pedidoEJB.updatePedidoAconfirmado(id_pedido);

					}

					ArrayList<PedidoDetallado> pedidoDetalladoCliente = pedidoEJB.getPedidoDetalladoPorId(id_pedido);

					HtmlEmail pedidoCliente = new HtmlEmail();

					String mensajeCliente = pedidoCliente.mailPedidoCliente(pedidoDetalladoCliente);

					mailOfficeEJB.sendMail2(c.getEmail(), "fetaquimallorca@gmail.com",
							"Confirmación de su pedido de fetaquimallorca.com", mensajeCliente);

					session.removeAttribute("pedido");

					response.sendRedirect("ObtenerPedidosCliente");

				} else {

					String error = "Para realizar una compra actualice su perfil de usuario";

					session.setAttribute("error", error);
					response.sendRedirect("ObtenerTodosProductos");
				}
			}

		} else {
			response.sendRedirect("Principal");
		}

	}
}
