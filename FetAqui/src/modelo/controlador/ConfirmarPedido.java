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
 * Controlador encargado de obtener un pedido concreto y pasarlo a confirmado
 * para enviar los datos del mismo a Cliente y Vendedor
 * 
 * @author ramon
 *
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

	@EJB
	LoggersEJB logger;

	private static final long serialVersionUID = 1L;
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	static final String CONFIRMAR_PEDIDO_JSP = "/ConfirmarPedido.jsp";

	/**
	 * Método doGet encargado de mostrar la página de confirmación de pedido
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);
		RequestDispatcher rs = getServletContext().getRequestDispatcher(CONFIRMAR_PEDIDO_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			// Intentamos recoger el usuario cliente de la sesión
			Cliente c = sesionClienteEJB.clienteLogeado(session);

			// Si existe la sesión y el usuario cliente en la misma
			if (session != null && c != null) {

				// Y si existe el parámetro id_pedido
				if (request.getParameter("id_pedido") != null) {

					// REcogemos el id del pedido a confirmar
					Integer id_pedido = Integer.valueOf(request.getParameter("id_pedido"));

					// Si el pedido en cuestión existe
					if (pedidoEJB.getPedidoDetalladoPorId(id_pedido) != null) {
						
						//Y el cliente tiene una dirección introducida en BD
						if (c.getDireccion() != null) {

							// Lo recogemos
							ArrayList<PedidoDetallado> pDetallado = pedidoEJB.getPedidoDetalladoPorId(id_pedido);

							// Recogemos el valor del total de pedido
							double total = Double.valueOf(request.getParameter("totalPedido"));

							//Lo introducimos en la request
							request.setAttribute("pedidoDetallado", pDetallado);
							request.setAttribute("total", total);
							
							//Redirigimos a CONFIRMAR_PEDIDO_JSP
							rs.forward(request, response);

						} else {
							
							//Si no existe dirección avisamos al cliente
							String error = "Para realizar una compra primero introduzca su dirección en su perfil de usuario.";
							
							//Pasamos el mensaje de error a la sesión
							session.setAttribute("error", error);
							
							//Redirigimos a ObtenerTodosProductos
							response.sendRedirect("ObtenerTodosProductos");
						}

					} else {
						
						//Si no existen datos para ese pedido mostramos el mensaje
						String error = "No hay datos para este pedido.";
						
						//Lo pasamos a la sesión
						session.setAttribute("error", error);
						
						//Redirigimos a ObtenerTodosProductos
						response.sendRedirect("ObtenerTodosProductos");
					}

				} else {
					
					//Si no existe el parámetro id pedido redirigimos a ObtenerTodosProductos
					response.sendRedirect("ObtenerTodosProductos");
				}

			} else {
				
				//Si no hay sesión redirigimos a Principal
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}
	
	/**
	 * Método doPost encargado de pasar un pedido a confirmado y envar los emails de los pedidos
	 * tanto a los vendedores que correspondan como al cliente.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);
		
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			//Intentamos recoger al usuario cliente de la sesión
			Cliente c = sesionClienteEJB.clienteLogeado(session);
			
			//Si la sesión existe y el cliente también
			if (session != null && c != null) {
				
				//Si el atributo de sesión pedido existe
				if (session.getAttribute("pedido") != null) {
					
					//Y si el cliente tiene una dirección introducida en BD
					if (c.getDireccion() != null) {
						
						//REcogemos el pedido de la sesión
						Pedido pedido = (Pedido) session.getAttribute("pedido");
						
						//REcogemos su id
						Integer id_pedido = pedido.getId();
						
						//Obtenemos un arraylist de los DISTINTOS vendedores que existen en ese pedido
						ArrayList<Vendedor> vendedores = pedidoEJB.getVendedoresPorPedido(id_pedido);
						
						//Para cada vendedor del arraylist
						for (Vendedor ve : vendedores) {
							
							//Recogemos el pedido detallado de cada vendedor diferente
							//Para enviar a cada uno lo que le corresponda del pedido en cuestión
							ArrayList<PedidoDetallado> pDetalladoVendedor = pedidoEJB
									.getPedidoDetalladoPorIdVendedorYpedido(ve.getId_vendedor(), id_pedido);
							
							//Generamos una instancia de la clase HtmlEmail
							HtmlEmail pedidoVendedores = new HtmlEmail();
							
							//Generamos el mensaje
							String mensajeVendedor = pedidoVendedores.mailPedidoVendedor(pDetalladoVendedor);
							
							//Lo enviamos a cada vendedor
							mailOfficeEJB.sendMail2(ve.getEmail(), "fetaquimallorca@gmail.com",
									"Nuevo pedido de fetaquimallorca.com", mensajeVendedor);							

						}
						
						//Pasamos el pedido a confirmado
						pedidoEJB.updatePedidoAconfirmado(id_pedido);
						
						//Recogemos el pedido integro para enviarselo al usuario cliente por email
						ArrayList<PedidoDetallado> pedidoDetalladoCliente = pedidoEJB
								.getPedidoDetalladoPorId(pedido.getId());
						
						//Generamos una instancia de la clase HtmlEmail
						HtmlEmail pedidoCliente = new HtmlEmail();
						
						//Generemos el mensaje para el cliente
						String mensajeCliente = pedidoCliente.mailPedidoCliente(pedidoDetalladoCliente);
						
						//Lo enviamos por email
						mailOfficeEJB.sendMail2(c.getEmail(), "fetaquimallorca@gmail.com",
								"Confirmación de su pedido de fetaquimallorca.com", mensajeCliente);
						
						//Borramos el atributo de sesión pedido
						session.removeAttribute("pedido");
						
						//Borramos el atributo de sesion numProductos para eliminar el numero de productos del icono de la cesta
						session.removeAttribute("numProductos");
						
						//Redirigimos a ObtenerPedidosCliente
						response.sendRedirect("ObtenerPedidosCliente");

					} else {
						
						//Si el cliente no tiene ninguna dirección introducida en BD le avisamos
						String error = "Para realizar una compra primero introduzca su dirección en su perfil de usuario.";
						
						//Introducimos el mensaje en la sesión
						session.setAttribute("error", error);
						
						//Redirigimos a ObtenerTodosProductos
						response.sendRedirect("ObtenerTodosProductos");
					}

				} else {
					
					//Si no existe el atributo de sesión pedido y si el usuario tiene dirección
					if (c.getDireccion() != null) {
						
						//REcogemos el parámetro de id pedido
						Integer id_pedido = Integer.valueOf(request.getParameter("id_pedido"));
						
						//Obtenemos un arraylist de los DISTINTOS vendedores que existen en ese pedido
						ArrayList<Vendedor> vendedores = pedidoEJB.getVendedoresPorPedido(id_pedido);
						
						//Para cada vendedor del arraylist
						for (Vendedor ve : vendedores) {
							
							//Recogemos el pedido detallado de cada vendedor diferente
							//Para enviar a cada uno lo que le corresponda del pedido en cuestión
							ArrayList<PedidoDetallado> pDetalladoVendedor = pedidoEJB
									.getPedidoDetalladoPorIdVendedorYpedido(ve.getId_vendedor(), id_pedido);
							
							//Generamos una instancia de la clase HtmlEmail
							HtmlEmail pedidoVendedores = new HtmlEmail();
							
							//Generamos el mensaje
							String mensajeVendedor = pedidoVendedores.mailPedidoVendedor(pDetalladoVendedor);
							
							//Lo enviamos por email
							mailOfficeEJB.sendMail2(ve.getEmail(), "fetaquimallorca@gmail.com",
									"Nuevo pedido de fetaquimallorca.com", mensajeVendedor);
						}
						
						//Pasamos el pedido a confirmado
						pedidoEJB.updatePedidoAconfirmado(id_pedido);
						
						//Obtenemos el pedido detallado para enviarlo al cliente
						ArrayList<PedidoDetallado> pedidoDetalladoCliente = pedidoEJB
								.getPedidoDetalladoPorId(id_pedido);
						
						//Generamos una instancia de HtmlEmail
						HtmlEmail pedidoCliente = new HtmlEmail();
						
						//Generamos el mensaje
						String mensajeCliente = pedidoCliente.mailPedidoCliente(pedidoDetalladoCliente);
						
						//Enviamos el email
						mailOfficeEJB.sendMail2(c.getEmail(), "fetaquimallorca@gmail.com",
								"Confirmación de su pedido de fetaquimallorca.com", mensajeCliente);
						
						//Borramos el atributo de sesión pedido
						session.removeAttribute("pedido");
						
						//Borramos el atributo de sesion numProductos para eliminar el numero de productos del icono de la cesta
						session.removeAttribute("numProductos");
						
						//Redirigimos a ObtenerPedidosCliente
						response.sendRedirect("ObtenerPedidosCliente");

					} else {
						
						//Si el cliente no tiene una dirección introducida le avisamos
						String error = "Para realizar una compra primero introduzca su dirección en su perfil de usuario.";
						
						//Introducimos el mensaje en la sesión
						session.setAttribute("error", error);
						
						//Redigirimos a ObtenerTodosProductos
						response.sendRedirect("ObtenerTodosProductos");
					}
				}

			} else {
				//Si no hay sesiń redirigimos a Principal
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}
}
