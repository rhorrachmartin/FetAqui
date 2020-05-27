package modelo.controlador;

import java.io.IOException;
import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ejb.DetallesPedidoEJB;
import modelo.ejb.PedidoEJB;
import modelo.ejb.ProductoEJB;
import modelo.ejb.SesionClienteEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Cliente;
import modelo.pojo.DetallePedido;
import modelo.pojo.Pedido;
import modelo.pojo.Producto;

/**
 * Servlet implementation class Carrito
 */
@WebServlet("/InsertarPedido")
public class InsertarPedido extends HttpServlet {

	@EJB
	SesionClienteEJB sesionClienteEJB;

	@EJB
	PedidoEJB pedidoEJB;
	
	@EJB
	ProductoEJB productoEJB;

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
		
		Cliente c = sesionClienteEJB.clienteLogeado(session);

		if (c != null) {

			if (session.getAttribute("pedido") == null) {

				if (c.getDireccion() != null) {
					Timestamp fecha_pedido = new Timestamp(System.currentTimeMillis());
					Timestamp fecha_entrega = new Timestamp(System.currentTimeMillis());
					Integer destino = c.getIdDireccion();
					String estado = "pendiente";
					Pedido pedido = new Pedido();

					pedido.setFecha_pedido(fecha_pedido);
					pedido.setFecha_entrega(fecha_entrega);
					pedido.setCliente(c.getId_cliente());
					pedido.setDestino(destino);
					pedido.setEstado(estado);

					pedidoEJB.insertarPedido(pedido);

					session.setAttribute("pedido", pedido);

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
						
					}else if(request.getParameter("paginaProducto") != null) {
						
						Producto producto = productoEJB.getProductoPorId(id_producto);
						
						response.sendRedirect("PaginaProducto?id_producto=" + id_producto +"&id_vendedor="+producto.getId_vendedor());
						
					} else {

						response.sendRedirect("ObtenerTodosProductos");
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
				}else if(request.getParameter("paginaProducto") != null) {
					
					Producto producto = productoEJB.getProductoPorId(id_producto);
					
					response.sendRedirect("PaginaProducto?id_producto=" + id_producto +"&id_vendedor="+producto.getId_vendedor());
					
				} else {

					response.sendRedirect("ObtenerTodosProductos");
				}
			}

		} else {
			response.sendRedirect("Principal");
		}

	}
}
