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
 * Controlador encargado de mostrar los productos de la cesta
 * 
 * @author ramon
 *
 */
@WebServlet("/Cesta")
public class Cesta extends HttpServlet {

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
	static final String CESTA_JSP = "/Cesta.jsp";
	
	/**
	 * Método doGet encargado de mostrar los pedidos de la cesta y el número de productos 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType(CONTENT_TYPE);

		RequestDispatcher rs = getServletContext().getRequestDispatcher(CESTA_JSP);
		
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			//Recogemos al usuario cliente de la sesión
			Cliente c = sesionClienteEJB.clienteLogeado(session);
			
			//Si hay sesión y exite el usuario cliente en la misma
			if (session != null && c != null) {
				
				//Introducimos el usuario en la request
				request.setAttribute("cliente", c);
				
				//Si el atributo de sesión pedido existe
				if (session.getAttribute("pedido") != null) {
					
					//Lo recogemos de la sesión
					Pedido pedido = (Pedido) session.getAttribute("pedido");
					
					//Obtenemos el detalle del pedido
					ArrayList<PedidoDetallado> pedidoDetallado = pedidoEJB.getPedidoDetalladoPorId(pedido.getId());
						
					//Si el pedido detallado está vacío
					if (pedidoDetallado.isEmpty()) {
						
						//Mostramos un mensaje como tal
						String mensaje = "Sin artículos en la cesta";
						
						//REcogemos el número de productos en la cesta
						Integer numProductos = pedidoEJB.getNumeroProductos(pedido.getId());
						
						//LO introducimos en la sesión
						session.setAttribute("numProductos", numProductos);
						
						//Introducimos el mensaje de cesta vacía en la request
						request.setAttribute("error", mensaje);
						
						//Redirigimos a CESTA_JSP
						rs.forward(request, response);
					} else {
						request.setAttribute("pedidoDetallado", pedidoDetallado);

						rs.forward(request, response);
					}

				} else {
					//Si no hay ningún pedido en la sesión mostramos otro mensaje
					String mensaje = "Sin artículos en la cesta";
					
					//Lo introducimos en la request
					request.setAttribute("error", mensaje);
					
					//Si hubiera algún atributo de sesión de número de productos lo eliminamos
					session.removeAttribute("numProductos");
					
					//Redirigimos a CESTA_JSP
					rs.forward(request, response);
				}

			} else {
				//Si no hay sesión redirigimos a Principal
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}
	
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		response.setContentType(CONTENT_TYPE);
//		
//		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
//		HttpSession session = request.getSession(false);
//
//		try {
//			//Recogemos al usuario cliente de la sesión
//			Cliente c = sesionClienteEJB.clienteLogeado(session);
//			
//			//Si hay sesión y exite el usuario cliente en la misma
//			if (session != null && c != null) {
//				
//				//Si no existe el atributo de sesión pedido
//				if (session.getAttribute("pedido") == null) {
//					
//					//Y si el cliente tiene asociada una dirección
//					if (c.getDireccion() != null) {
//						
//						LocalDate hoy = LocalDate.now();
//				        LocalTime ahora = LocalTime.now();				        
//				        LocalDateTime fecha_pedido1 = LocalDateTime.of(hoy, ahora);
//				        Timestamp fecha_pedido = Timestamp.valueOf(fecha_pedido1);
//				        
//				        LocalDateTime hoy2 = LocalDateTime.now();
//				        LocalDateTime hoyMasDos = hoy2.plusDays(2);
//				        
//						Timestamp fecha_entrega = Timestamp.valueOf(hoyMasDos);	
//						
//						Integer destino = c.getIdDireccion();
//						String estado = "pendiente";
//						Pedido pedido = new Pedido();
//
//						pedido.setFecha_pedido(fecha_pedido);
//						pedido.setFecha_entrega(fecha_entrega);
//						pedido.setCliente(c.getId_cliente());
//						pedido.setDestino(destino);
//						pedido.setEstado(estado);
//
//						pedidoEJB.insertarPedido(pedido);
//
//						session.setAttribute("pedido", pedido);
//
//						Integer id_pedido = pedido.getId();
//
//						Integer id_producto = Integer.valueOf(request.getParameter("id_producto"));
//
//						Integer cantidad = Integer.valueOf(request.getParameter("cantidad"));
//
//						Double precio = Double.valueOf(request.getParameter("precio"));
//
//						DetallePedido detallePedido = new DetallePedido();
//
//						detallePedido.setPedido(id_pedido);
//						detallePedido.setProducto(id_producto);
//						detallePedido.setPrecioUnitario(precio);
//						detallePedido.setCantidad(cantidad);
//
//						detallePedidoEJB.insertarDetallePedido(detallePedido);
//
//						Integer numProductos = pedidoEJB.getNumeroProductos(id_pedido);
//
//						session.setAttribute("numProductos", numProductos);
//
//						response.sendRedirect("ObtenerTodosProductos");
//					} else {
//
//						String error = "Para realizar una compra actualice su perfil de usuario, su dirección es importante para poderle realizar el envío correctamente";
//
//						session.setAttribute("error", error);
//						response.sendRedirect("ObtenerTodosProductos");
//					}
//
//				} else {
//					Pedido pedido = (Pedido) session.getAttribute("pedido");
//
//					Integer id_pedido = pedido.getId();
//
//					Integer id_producto = Integer.valueOf(request.getParameter("id_producto"));
//
//					Integer cantidad = Integer.valueOf(request.getParameter("cantidad"));
//
//					Double precio = Double.valueOf(request.getParameter("precio"));
//
//					DetallePedido detallePedido = new DetallePedido();
//
//					detallePedido.setPedido(id_pedido);
//					detallePedido.setProducto(id_producto);
//					detallePedido.setPrecioUnitario(precio);
//					detallePedido.setCantidad(cantidad);
//
//					detallePedidoEJB.insertarDetallePedido(detallePedido);
//
//					Integer numProductos = pedidoEJB.getNumeroProductos(id_pedido);
//
//					session.setAttribute("numProductos", numProductos);
//
//					response.sendRedirect("ObtenerTodosProductos");
//				}
//
//			} else {
//				response.sendRedirect("Principal");
//			}
//		} catch (Exception e) {
//			logger.setErrorLogger(e.getMessage());
//		}
//
//	}
}
