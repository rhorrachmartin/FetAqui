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
 * 
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

	/**
	 * Método doPost encargado de visualizar el pedido recibido por un usuario
	 * vendedor
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Creamos el RequestDispatcher por defecto hacia PEDIDO_VENDEDOR_JSP
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PEDIDO_VENDEDOR_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {

			// Intentamos recoger al usuario vendedor de la sesión
			Vendedor v = sesionVendedorEJB.vendedorLogeado(session);

			// Si hay sesión y existe usuario vendedor en la misma
			if (session != null && v != null) {

				// Si recibimos la id del pedido a visualizar
				if (request.getParameter("id_pedido") != null) {

					// REcogemos la id del pedido
					Integer id_pedido = Integer.valueOf(request.getParameter("id_pedido"));

					// Si existe un pedido con esa id
					if (pedidoEJB.getPedidoDetalladoPorId(id_pedido) != null) {

						// Recogemos el detalle del pedido
						ArrayList<PedidoDetallado> pDetallado = pedidoEJB
								.getPedidoDetalladoPorIdVendedorYpedido(v.getId_vendedor(), id_pedido);

						// SI existe pero no tiene productos
						if (pDetallado.size() == 0) {

							// Mostramos un mensaje de error
							String error = "No existen datos para este pedido";

							request.setAttribute("error", error);

							rs.forward(request, response);
						} else {

							// Si tiene productos metemos el detalle en la request y redirigimos
							request.setAttribute("pedidoDetallado", pDetallado);

							rs.forward(request, response);
						}

					} else {

						// Si no existe el pedido mostramos un mensaje de error
						String error = "No existen datos para este pedido";

						request.setAttribute("error", error);

						rs.forward(request, response);
					}

					// Si no recibimos el parámetro necesario redirigimos a ObtenerPedidosVendedor
				} else {

					response.sendRedirect("ObtenerPedidosVendedor");
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
