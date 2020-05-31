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
import modelo.ejb.SesionVendedorEJB;
import modelo.pojo.PedidoDetallado;
import modelo.pojo.Vendedor;

/**
 * Clase encargada de cargar un pedido de un Vendedor
 * @author ramon
 *
 */
@WebServlet("/VerPedidoVendedor")
public class VerPedidoVendedor extends HttpServlet {

	@EJB
	SesionVendedorEJB sesionVendedorEJB;

	@EJB
	PedidoEJB pedidoEJB;

	@EJB
	DetallesPedidoEJB detallePedidoEJB;
	
	@EJB
	LoggersEJB logger;

	private static final long serialVersionUID = 1L;
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	static final String PEDIDO_VENDEDOR_JSP = "/PedidoVendedor.jsp";



	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		RequestDispatcher rs = getServletContext().getRequestDispatcher(PEDIDO_VENDEDOR_JSP);

		HttpSession session = request.getSession(false);

		try {
			Vendedor v = sesionVendedorEJB.vendedorLogeado(session);

			if (v != null) {

				if (request.getParameter("id_pedido") != null) {

					Integer id_pedido = Integer.valueOf(request.getParameter("id_pedido"));

					if (pedidoEJB.getPedidoDetalladoPorId(id_pedido) != null) {

						ArrayList<PedidoDetallado> pDetallado = pedidoEJB.getPedidoDetalladoPorIdVendedorYpedido(v.getId_vendedor(), id_pedido);

						if (pDetallado.size() == 0) {
							String error = "No existen datos para este pedido";

							request.setAttribute("error", error);

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

					response.sendRedirect("ObtenerPedidosVendedor");
				}

			} else {
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}
}
