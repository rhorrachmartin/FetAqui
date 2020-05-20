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

import modelo.ejb.CategoriaEJB;
import modelo.ejb.FormatoEJB;
import modelo.ejb.ImagenesEJB2;
import modelo.ejb.LoggersEJB;
import modelo.ejb.PedidoEJB;
import modelo.ejb.ProductoEJB;
import modelo.pojo.Categoria;
import modelo.pojo.Cliente;
import modelo.pojo.Formato;
import modelo.pojo.Pedido;
import modelo.pojo.Producto;
import modelo.pojo.Vendedor;

@WebServlet("/ObtenerPedidosCliente")
public class ObtenerPedidosCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	CategoriaEJB categoriaEJB;

	@EJB
	FormatoEJB formatoEJB;

	@EJB
	ProductoEJB productoEJB;

	@EJB
	PedidoEJB pedidoEJB;
	/**
	 * EJB para trabajar con los logger
	 */
	@EJB
	LoggersEJB logger;

	@EJB
	ImagenesEJB2 imagenesEJB;

	static final String PEDIDOS_CLIENTE_JSP = "/PedidosCliente.jsp";
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PEDIDOS_CLIENTE_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		Cliente cliente = (Cliente) session.getAttribute("cliente");

		if (session != null && cliente.getNombre() != null) {

			if (pedidoEJB.getPedidosCliente(cliente.getId_cliente()) != null) {

				ArrayList<Pedido> pedidos = pedidoEJB.getPedidosCliente(cliente.getId_cliente());

				if (pedidos.isEmpty()) {
					String error = "No existen pedidos";

					request.setAttribute("error", error);

					rs.forward(request, response);
				} else {
					request.setAttribute("pedidos", pedidos);

					rs.forward(request, response);
				}

			} else {
				String error = "No existen pedidos";

				request.setAttribute("error", error);

				rs.forward(request, response);
			}

		} else {
			response.sendRedirect("Principal");
		}

	}

}
