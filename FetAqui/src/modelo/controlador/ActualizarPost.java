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

import org.apache.commons.lang3.StringUtils;

import modelo.ejb.CategoriaEJB;
import modelo.ejb.FormatoEJB;
import modelo.ejb.LoggersEJB;
import modelo.ejb.PostEJB;
import modelo.ejb.ProductoEJB;
import modelo.ejb.ValoracionProductoEJB;
import modelo.pojo.Categoria;
import modelo.pojo.Formato;
import modelo.pojo.Post;
import modelo.pojo.Producto;
import modelo.pojo.Vendedor;

/**
 * Controlador encargado de actualizar un Post
 * 
 * @author ramon
 *
 */
@WebServlet("/ActualizarPost")
public class ActualizarPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	PostEJB postEJB;

	@EJB
	CategoriaEJB categoriaEJB;

	@EJB
	FormatoEJB formatoEJB;

	@EJB
	ProductoEJB productoEJB;

	@EJB
	ValoracionProductoEJB valoracionProductoEJB;

	@EJB
	LoggersEJB logger;

	static final String PAGINA_PROPIA_VENDEDOR = "/PaginaPropiaVendedor.jsp";
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PAGINA_PROPIA_VENDEDOR);

		response.setContentType(CONTENT_TYPE);
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {

			// REcogemos el usuario vendedor de la sesión
			Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");

			// COmprobamos que exista el usuario en la sesión
			if (session != null && vendedor.getNombre() != null) {

				// Recogemos el post escrito por el vendedor
				String post = request.getParameter("post");

				// Contramos los espacios en blanco, tabulaciones y saltos de linea
				post.replace("\n", "").replace("\t", "").replace("\r", "").replace(" ", "");

				// Recogemos la id del post a actualizar
				Integer id_post = Integer.valueOf(request.getParameter("idPost"));

				// Seteamos el pojo del post
				Post p = new Post();
				p.setId(id_post);
				p.setTexto(post);
				p.setAutor(vendedor.getId_vendedor());

				// Si el texto del post no está en blanco
				if (StringUtils.isNotBlank(p.getTexto())) {

					// Actualizamos el post
					postEJB.editarPost(p);

					// Recogemos todos los parámetros necesarios
					ArrayList<Producto> productos = productoEJB.getProductosVendedor(vendedor.getId_vendedor());
					ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
					ArrayList<Formato> formatos = formatoEJB.getFormatos();
					ArrayList<Post> posts = postEJB.getPostsVendedor(vendedor.getId_vendedor());

					// Lo pasamos todo a la request
					request.setAttribute("productos", productos);
					request.setAttribute("posts", posts);
					request.setAttribute("vendedor", vendedor);
					request.setAttribute("categorias", categorias);
					request.setAttribute("formatos", formatos);

					// Redirigimos a PAGINA_PROPIA_VENDEDOR
					rs.forward(request, response);

				} else {

					// Si el texto del post a actualizar está en blanco no lo actualizamos

					// Recogemos todos los parámetros necesarios
					ArrayList<Producto> productos = productoEJB.getProductosVendedor(vendedor.getId_vendedor());
					ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
					ArrayList<Formato> formatos = formatoEJB.getFormatos();
					ArrayList<Post> posts = postEJB.getPostsVendedor(vendedor.getId_vendedor());

					// Lo pasamos todo a la request
					request.setAttribute("productos", productos);
					request.setAttribute("posts", posts);
					request.setAttribute("vendedor", vendedor);
					request.setAttribute("categorias", categorias);
					request.setAttribute("formatos", formatos);

					// Redirigimos a PAGINA_PROPIA_VENDEDOR
					rs.forward(request, response);
				}

			} else {
				// Si no hay un usuario vendedor o no hay sesión redirigimos a Principal
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

}
