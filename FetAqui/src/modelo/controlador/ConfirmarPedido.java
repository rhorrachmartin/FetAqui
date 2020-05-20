package modelo.controlador;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ejb.DetallesPedidoEJB;
import modelo.ejb.PedidoEJB;
import modelo.ejb.SesionClienteEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Cliente;
import modelo.pojo.DetallePedido;
import modelo.pojo.Pedido;
import modelo.pojo.PedidoDetallado;
import modelo.pojo.Vendedor;

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

	private static final long serialVersionUID = 1L;
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		HttpSession session = request.getSession(false);
		ArrayList<Integer> id_vendedores = null;
		
		
		Cliente c = sesionClienteEJB.clienteLogeado(session);

		if (c != null) {

			if (session.getAttribute("pedido") != null) {

				if (c.getDireccion() != null) {
					
					Pedido pedido = (Pedido) session.getAttribute("pedido");
					
					Integer id_pedido = pedido.getId();
					ArrayList<PedidoDetallado> pDetallado = pedidoEJB.getPedidoDetalladoPorId(id_pedido);
				
					ArrayList<Vendedor> vendedores = pedidoEJB.getVendedoresPorPedido(id_pedido);
					
					for(Vendedor ve : vendedores) {
						ArrayList<PedidoDetallado> pDetalladoVendedor = pedidoEJB.getPedidoDetalladoPorIdVendedorYpedido(ve.getId_vendedor(), id_pedido);
						
						System.out.println(pDetalladoVendedor.toString());
					}
					
				} else {

					String error = "Para realizar una compra actualice su perfil de usuario";

					session.setAttribute("error", error);
					response.sendRedirect("ObtenerTodosProductos");
				}

			} else {
				Pedido pedido = (Pedido) session.getAttribute("pedido");

				Integer id_pedido = pedido.getId();

				Integer id_producto = Integer.valueOf(request.getParameter("id_producto"));

				Integer cantidad = Integer.valueOf(request.getParameter("cantidad"));

				Double precio = Double.valueOf(request.getParameter("precio"));

				DetallePedido detallePedido = new DetallePedido();

				detallePedido.setPedido(id_pedido);
				detallePedido.setProducto(id_producto);
				detallePedido.setPrecioUnitario(precio);
				detallePedido.setCantidad(cantidad);

				detallePedidoEJB.insertarDetallePedido(detallePedido);

				Integer numProductos = pedidoEJB.getNumeroProductos(id_pedido);

				session.setAttribute("numProductos", numProductos);

				if (request.getParameter("id_vendedor") != null) {

					Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));

					response.sendRedirect("PaginaVendedor?id_vendedor=" + id_vendedor);
				} else if (request.getParameter("paginaPrincipal") != null) {
					response.sendRedirect("Principal");
				} else {

					response.sendRedirect("ObtenerTodosProductos");
				}
			}

		} else {
			response.sendRedirect("Principal");
		}

	}
}
