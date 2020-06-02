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
import modelo.ejb.PostEJB;
import modelo.ejb.ProductoEJB;
import modelo.pojo.Categoria;
import modelo.pojo.Formato;
import modelo.pojo.Post;
import modelo.pojo.Producto;
import modelo.pojo.Vendedor;

/**
 * Controlador encargado de eliminar un post de un usuario Vendedor
 * 
 * @author ramon
 *
 */
@WebServlet("/EliminarPost")
public class EliminarPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	PostEJB postEJB;

	@EJB
	CategoriaEJB categoriaEJB;

	@EJB
	ProductoEJB productoEJB;

	@EJB
	FormatoEJB formatoEJB;

	@EJB
	LoggersEJB logger;
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	static final String PAGINA_PROPIA_VENDEDOR = "/PaginaPropiaVendedor.jsp";
	

	/**
	 * Método doPost encargado de eliminar un post de un usuario Vendedor
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rs = getServletContext().getRequestDispatcher(PAGINA_PROPIA_VENDEDOR);
		response.setContentType(CONTENT_TYPE);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			
			//Intentamos recoger el usuario vendedor de la sesión
			Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");
			
			//Si la sesión existe y el usuario también
			if (session != null && vendedor.getNombre() != null) {
				
				//REcogemos el parámetro id post de la request
				Integer id_post = Integer.valueOf(request.getParameter("id_post"));
				
				//Obtenemos el post a través de su id
				Post post = postEJB.getPostPorId(id_post);
				
				//Si existe
				if (post != null) {
					
					//Lo borramos
					postEJB.borrarPost(id_post);
					
					//Recogemos todos los parámetros necesarios
					ArrayList<Post> posts = postEJB.getPostsVendedor(vendedor.getId_vendedor());
					ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
					ArrayList<Formato> formatos = formatoEJB.getFormatos();
					ArrayList<Producto> productos = productoEJB.getProductosVendedor(vendedor.getId_vendedor());
					
					//Lo pasamos todo a la request.
					request.setAttribute("posts", posts);	
					request.setAttribute("vendedor", vendedor);
					request.setAttribute("formatos", formatos);
					request.setAttribute("productos", productos);
					request.setAttribute("categorias", categorias);
					
					//Redigirimos a PAGINA_PROPIA_VENDEDOR
					rs.forward(request, response);
				}

			} else {
				
				//Si no existe la sesión redirigimos a Principal
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

}
