package modelo.controlador;

import java.io.IOException;

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
import modelo.pojo.Cliente;

/**
 * Servlet implementation class Carrito
 */
@WebServlet("/BorrarProductoCesta")
public class BorrarProductoCesta extends HttpServlet {

	@EJB
	SesionClienteEJB sesionClienteEJB;

	@EJB
	PedidoEJB pedidoEJB;

	@EJB
	DetallesPedidoEJB detallePedidoEJB;

	private static final long serialVersionUID = 1L;
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	static final String CESTA_JSP = "/Cesta.jsp";
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		response.setContentType(CONTENT_TYPE);
//		
//		RequestDispatcher rs = getServletContext().getRequestDispatcher(CESTA_JSP);
//
//		HttpSession session = request.getSession(false);
//
//		Cliente c = sesionClienteEJB.clienteLogeado(session);
//		
//		if (c != null) {
//			
//			request.setAttribute("cliente", c);
//			
//			if(session.getAttribute("pedido")  != null) {
//				Pedido pedido = (Pedido) session.getAttribute("pedido");
//				
//				ArrayList<PedidoDetallado> pedidoDetallado = pedidoEJB.getPedidoDetalladoPorId(pedido.getId());
//				
//				
//				request.setAttribute("pedidoDetallado", pedidoDetallado);
//				
//				rs.forward(request, response);
//			}else {
//				String mensaje = "Sin artículos en la cesta";
//				
//				request.setAttribute("error", mensaje);
//				
//				rs.forward(request, response);
//			}
//			
//		}else {
//			response.sendRedirect("Principal");
//		}
//		
//
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		HttpSession session = request.getSession(false);

		Cliente c = sesionClienteEJB.clienteLogeado(session);

		if (c != null) {

			if (request.getParameter("id_detalle") != null) {

				Integer id_detalle = Integer.valueOf(request.getParameter("id_detalle"));
				Integer id_pedido = Integer.valueOf(request.getParameter("id_pedido"));
				
				pedidoEJB.borrarProductoCesta(id_detalle);
				
				Integer numProductos = pedidoEJB.getNumeroProductos(id_pedido);
				session.removeAttribute("numProductos");
				session.setAttribute("numProductos", numProductos);
				
				response.sendRedirect("Cesta");

			} else {

				response.sendRedirect("Cesta");
			}

		} else {
			response.sendRedirect("Principal");
		}

	}
}
