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
import modelo.ejb.LoggersEJB;
import modelo.ejb.PedidoEJB;
import modelo.ejb.ProductoEJB;
import modelo.pojo.Pedido;
import modelo.pojo.Vendedor;

/**
 * Clase controlador encargado de obtener todos los pedidos que le han hecho a un vendedor
 * @author ramon
 *
 */
@WebServlet("/ObtenerPedidosVendedor")
public class ObtenerPedidosVendedor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	CategoriaEJB categoriaEJB;

	@EJB
	FormatoEJB formatoEJB;

	@EJB
	ProductoEJB productoEJB;

	@EJB
	PedidoEJB pedidoEJB;
	
	@EJB
	LoggersEJB logger;

	
	static final String PEDIDOS_VENDEDOR_JSP = "/PedidosVendedor.jsp";
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType(CONTENT_TYPE);
		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PEDIDOS_VENDEDOR_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			
			//Intentamos recoger al usuario Vendedor de la sesión
			Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");
			
			//Si hay sesión y existe el usuario vendedor en la misma
			if (session != null && vendedor.getNombre() != null) {
				
				//Si el vendedor tiene pedidos
				if (pedidoEJB.getPedidosVendedor(vendedor.getId_vendedor()) != null) {
					
					//Los recogemos en un arraylist
					ArrayList<Pedido> pedidos = pedidoEJB.getPedidosVendedor(vendedor.getId_vendedor());
					
					//Si el arraylist está vacío mostramos un mensaje de error
					if (pedidos.isEmpty()) {
						String error = "No existen pedidos";
						
						//Lo pasamos a la request
						request.setAttribute("error", error);
						
						//Redirigimos a PEDIDOS_VENDEDOR_JSP
						rs.forward(request, response);
					} else {
						
						//Si no está vacío lo pasamos a la request
						request.setAttribute("pedidos", pedidos);
						
						//REdirigimos a PEDIDOS_VENDEDOR_JSP
						rs.forward(request, response);
					}

				} else {
					
					//Si no tiene pedidos mostramos un mensaje de error
					String error = "No existen pedidos";
					
					//Lo insertamos en la request
					request.setAttribute("error", error);
					
					//Redirigimos a PEDIDOS_VENDEDOR_JSP
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

}
