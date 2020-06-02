package modelo.controlador;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ejb.DetallesPedidoEJB;
import modelo.ejb.LoggersEJB;
import modelo.ejb.PedidoEJB;
import modelo.ejb.ProductoEJB;
import modelo.ejb.SesionClienteEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Cliente;
import modelo.pojo.DetallePedido;
import modelo.pojo.Pedido;
import modelo.pojo.Producto;

/**
 * Controlador encargado de insertar un pedido de un Cliente en BD
 * 
 * @author ramon
 *
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
	
	@EJB
	LoggersEJB logger;

	private static final long serialVersionUID = 1L;
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	/**
	 * Método doPost encargado de introducir el pedido en BD y en la sesión
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);
		
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			//Intentamos recoger el usuario cliente de la sesión
			Cliente c = sesionClienteEJB.clienteLogeado(session);
			
			//Si la sesión existe y el usuario Cliente también
			if (session != null && c != null) {
				
				//Si no existe el atributo de sesion pedido
				if (session.getAttribute("pedido") == null) {
					
					//Y si el cliente tiene dirección
					if (c.getDireccion() != null) {
						
						//Recogemos la fecha actual para la fecha de pedido y la pasamos a Timestamp 
						LocalDate hoy = LocalDate.now();
				        LocalTime ahora = LocalTime.now();				        
				        LocalDateTime fecha_pedido1 = LocalDateTime.of(hoy, ahora);
				        Timestamp fecha_pedido = Timestamp.valueOf(fecha_pedido1);
				        
				        //Recogemos la fecha actual y le añadimos dos días para la fecha de entrega y la pasamos a Timestamp
				        LocalDateTime hoy2 = LocalDateTime.now();
				        LocalDateTime hoyMasDos = hoy2.plusDays(2);				        
						Timestamp fecha_entrega = Timestamp.valueOf(hoyMasDos);	
						
						//El destino de entrega del pedido es la dirección del cliente
						Integer destino = c.getIdDireccion();
						
						//Por defecto un pedido no confirmado queda en estado pendiente
						String estado = "pendiente";
						
						//Generamos la instancia de un Pedido
						Pedido pedido = new Pedido();
						
						//Lo seteamos
						pedido.setFecha_pedido(fecha_pedido);
						pedido.setFecha_entrega(fecha_entrega);
						pedido.setCliente(c.getId_cliente());
						pedido.setDestino(destino);
						pedido.setEstado(estado);
						
						//Lo insertamos en BD
						pedidoEJB.insertarPedido(pedido);
						
						//Lo introducimos en la sesión
						session.setAttribute("pedido", pedido);
						
						//Recogemos la id del pedido introducido en la BD, MyBatis permite recoger ID'S autogeneradas
						Integer id_pedido = pedido.getId();
						
						//Recogemos los parámetros necesarios
						Integer id_producto = Integer.valueOf(request.getParameter("id_producto"));
						Integer cantidad = Integer.valueOf(request.getParameter("cantidad"));
						Double precio = Double.valueOf(request.getParameter("precio"));
						
						//Instancia de DetallePedido
						DetallePedido detallePedido = new DetallePedido();
						
						//La seteamos
						detallePedido.setPedido(id_pedido);
						detallePedido.setProducto(id_producto);
						detallePedido.setPrecioUnitario(precio);
						detallePedido.setCantidad(cantidad);
						
						//Lo insertamos en BD	
						detallePedidoEJB.insertarDetallePedido(detallePedido);
						
						//Recogemos la cantidad de productos que contiene el pedido
						Integer numProductos = pedidoEJB.getNumeroProductos(id_pedido);
						
						//Lo introducimos en la sesión 
						session.setAttribute("numProductos", numProductos);
						
						//Si como parámetro recibimos la id del vendedor redirigimos a Pagina Vendedor
						if (request.getParameter("id_vendedor") != null) {

							Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));

							response.sendRedirect("PaginaVendedor?id_vendedor=" + id_vendedor);
							
						//Si como parámetro recibimos Pagina Principal redirigimos a Principal
						} else if (request.getParameter("paginaPrincipal") != null) {

							response.sendRedirect("Principal");
							
						//Si como parámetro recibimos paginaProducto redirigimos a la página de ese producto
						} else if (request.getParameter("paginaProducto") != null) {

							Producto producto = productoEJB.getProductoPorId(id_producto);

							response.sendRedirect("PaginaProducto?id_producto=" + id_producto + "&id_vendedor="
									+ producto.getId_vendedor());

						} else {
							//Si no se recibe ninguno de los mencionados redirigimos a ObtenerTodosProductos
							response.sendRedirect("ObtenerTodosProductos");
						}
					} else {
						
						//Si el cliente no tiene dirección le avisamos
						String error = "Para realizar una compra primero introduzca su dirección en su perfil de usuario.";
						
						//Introducimos el mensaje en la sesión
						session.setAttribute("error", error);
						
						//REdirigimos a ObtenerTodosProductos
						response.sendRedirect("ObtenerTodosProductos");
					}

				} else {
					
					//Si existe el atributo de sesión pedido lo recogemos
					Pedido pedido = (Pedido) session.getAttribute("pedido");
					
					//Recogemos la id del mismo
					Integer id_pedido = pedido.getId();
					
					//Recogemos los parámetros necesarios
					Integer id_producto = Integer.valueOf(request.getParameter("id_producto"));
					Integer cantidad = Integer.valueOf(request.getParameter("cantidad"));
					Double precio = Double.valueOf(request.getParameter("precio"));
					
					//Instancia de detallePedido
					DetallePedido detallePedido = new DetallePedido();
					
					//Lo seteamos
					detallePedido.setPedido(id_pedido);
					detallePedido.setProducto(id_producto);
					detallePedido.setPrecioUnitario(precio);
					detallePedido.setCantidad(cantidad);
					
					//Lo insertamos en BD
					detallePedidoEJB.insertarDetallePedido(detallePedido);
					
					//Obtenemos el número de productos en el pedido
					Integer numProductos = pedidoEJB.getNumeroProductos(id_pedido);
					
					//Lo introducimos en la sesión
					session.setAttribute("numProductos", numProductos);
					
					//Si como parámetro recibimos la id del vendedor redirigimos a la página del propio vendedor
					if (request.getParameter("id_vendedor") != null) {

						Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));

						response.sendRedirect("PaginaVendedor?id_vendedor=" + id_vendedor);
					
					//Si como parámetro recibimos paginaPrincipal redirigimos a Principal
					} else if (request.getParameter("paginaPrincipal") != null) {
						
						response.sendRedirect("Principal");
					
					//Si como parámetro recibimos paginaProducto redirigimos la página del producto
					} else if (request.getParameter("paginaProducto") != null) {

						Producto producto = productoEJB.getProductoPorId(id_producto);

						response.sendRedirect("PaginaProducto?id_producto=" + id_producto + "&id_vendedor="
								+ producto.getId_vendedor());
					
					//Si no recibimos ninguno de esos parámetros redirigimos a ObtenerTodosProductos
					} else {

						response.sendRedirect("ObtenerTodosProductos");
					}
				}
				
				//Si no hay sesión redirigimos a Principal
			} else {
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}
}
