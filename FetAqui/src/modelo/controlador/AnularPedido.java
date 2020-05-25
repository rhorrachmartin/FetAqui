package modelo.controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ejb.LoggersEJB;
import modelo.ejb.PedidoEJB;
import modelo.ejb.ProductoEJB;
import modelo.pojo.Cliente;
import modelo.pojo.Producto;
import modelo.pojo.Vendedor;

/**
 * Servlet implementation class ActualizarPerfilCliente
 */
@WebServlet("/AnularPedido")
public class AnularPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@EJB
	PedidoEJB pedidoEJB;

	/**
	 * EJB para trabajar con los logger
	 */
	@EJB
	LoggersEJB logger;
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		if (session == null && session.getAttribute("vendedor") == null && session.getAttribute("cliente") == null) {
			response.sendRedirect("Principal");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		Cliente cliente = (Cliente) session.getAttribute("cliente");

		try {

			

			if (cliente.getNombre() != null) {
				
				Integer id_pedido = Integer.valueOf(request.getParameter("id_pedido"));
				
				if (id_pedido != null) {
					
					System.out.println(id_pedido);

					pedidoEJB.borrarPedidoPorId(id_pedido);
					session.removeAttribute("pedido");
					response.sendRedirect("ObtenerPedidosCliente");

				} else {

					response.sendRedirect("ObtenerPedidosCliente");
				}

			} else {
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			e.getMessage();
		}

	}

}
