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
import modelo.ejb.LoggersEJB;
import modelo.ejb.PostEJB;
import modelo.ejb.ProductoEJB;
import modelo.ejb.ValoracionPostEJB;
import modelo.ejb.ValoracionVendedorEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Post;
import modelo.pojo.Producto;
import modelo.pojo.ValoracionCv;
import modelo.pojo.ValoracionPost;
import modelo.pojo.Vendedor;

/**
 * Servlet implementation class ActualizarPerfilCliente
 */
@WebServlet("/ValorarVendedor")
public class ValorarVendedor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	ValoracionVendedorEJB valoracionVendedorEJB;
	
	@EJB
	CategoriaEJB categoriaEJB;

	@EJB
	ProductoEJB productoEJB;
	
	@EJB
	VendedorEJB vendedorEJB;
	
	@EJB
	PostEJB postEJB;

	/**
	 * EJB para trabajar con los logger
	 */
	@EJB
	LoggersEJB logger;

	static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	static final String PRODUCTOS_LOGEADO_JSP = "/ProductosLogeadoCliente.jsp";
	static final String VENDEDOR_LOGEADO_CLIENTE_JSP = "/VendedorLogeadoCliente.jsp";
	static final String HOME_LOGEADO_JSP = "/HomeLogeado.jsp";

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
		
		
		if(session.getAttribute("cliente") != null) {
			
			if(request.getParameter("valoracion") != null && request.getParameter("id_vendedor") != null && request.getParameter("id_cliente") != null) {
				
				ValoracionCv valoracionCv = new ValoracionCv();
				
				Integer valoracion = Integer.valueOf(request.getParameter("valoracion"));
				Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));
				Integer id_cliente = Integer.valueOf(request.getParameter("id_cliente"));
				
				valoracionCv.setId_cliente(id_cliente);
				valoracionCv.setId_vendedor(id_vendedor);
				valoracionCv.setValoracion(valoracion);
				
				ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();
				ArrayList<Producto> productos = productoEJB.getProductos();
				ArrayList<Post> posts = postEJB.getPosts();
				
				request.setAttribute("vendedores", vendedores);
				request.setAttribute("productos", productos);
				request.setAttribute("posts", posts);
				
				valoracionVendedorEJB.insertarValoracionVendedor(valoracionCv);
				
				
				if(request.getParameter("paginaVendedor")!= null) {
					response.sendRedirect("PaginaVendedor?id_vendedor="+id_vendedor);
				}else if(request.getParameter("principal") != null) {
					RequestDispatcher rs = getServletContext().getRequestDispatcher(HOME_LOGEADO_JSP);
					rs.forward(request, response);
				}else if(request.getParameter("paginaVendedores") != null) {
					response.sendRedirect("ObtenerTodosVendedores");
				}
				
				
				
				
			}else {
				response.sendRedirect("ObtenerTodosProductos");
			}
			
			
		}else {
			response.sendRedirect("Principal");
		}

		ValorarVendedor valoracionProducto = new ValorarVendedor();

	}

}
