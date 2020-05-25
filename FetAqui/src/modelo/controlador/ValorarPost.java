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
import modelo.ejb.VendedorEJB;
import modelo.pojo.Categoria;
import modelo.pojo.Post;
import modelo.pojo.Producto;
import modelo.pojo.ValoracionPost;
import modelo.pojo.Vendedor;

/**
 * Servlet implementation class ActualizarPerfilCliente
 */
@WebServlet("/ValorarPost")
public class ValorarPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	ValoracionPostEJB valoracionPostEJB;
	
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
		
		System.out.println(request.getParameter("valoracion") +", producto: " + request.getParameter("id_producto") +", cliente: " + request.getParameter("id_cliente"));
		
		if(session.getAttribute("cliente") != null) {
			
			if(request.getParameter("valoracion") != null && request.getParameter("id_post") != null && request.getParameter("id_cliente") != null) {
				
				ValoracionPost valoracionPost = new ValoracionPost();
				
				Integer valoracion = Integer.valueOf(request.getParameter("valoracion"));
				Integer id_post = Integer.valueOf(request.getParameter("id_post"));
				Integer id_cliente = Integer.valueOf(request.getParameter("id_cliente"));
				
				valoracionPost.setId_cliente(id_cliente);
				valoracionPost.setId_post(id_post);
				valoracionPost.setValoracion(valoracion);
				
				ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();
				ArrayList<Producto> productos = productoEJB.getProductos();
				ArrayList<Post> posts = postEJB.getPosts();
				
				request.setAttribute("vendedores", vendedores);
				request.setAttribute("productos", productos);
				request.setAttribute("posts", posts);
				
				valoracionPostEJB.insertarValoracionPostPorDefecto(valoracionPost);
				
				
				RequestDispatcher rs = getServletContext().getRequestDispatcher(HOME_LOGEADO_JSP);
				rs.forward(request, response);
				
				
				
				
			}else {
				response.sendRedirect("ObtenerTodosProductos");
			}
			
			
		}else {
			response.sendRedirect("Principal");
		}

		ValorarPost valoracionProducto = new ValorarPost();

	}

}
