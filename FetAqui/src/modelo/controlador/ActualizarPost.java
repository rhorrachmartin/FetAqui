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
import modelo.ejb.ImagenesEJB;
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
 * Servlet implementation class PublicarNoticia
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
	/**
	 * EJB para trabajar con los logger
	 */
	@EJB
	LoggersEJB logger;

	@EJB
	ImagenesEJB imagenesEJB;

	static final String PAGINA_PROPIA_VENDEDOR = "/PaginaPropiaVendedor.jsp";
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PAGINA_PROPIA_VENDEDOR);

		response.setContentType(CONTENT_TYPE);
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");

		if (session != null && vendedor.getNombre() != null) {

			String post = request.getParameter("post");

			post.replace("\n", "").replace("\t", "").replace("\r", "").replace(" ", "");

			Integer id_post = Integer.valueOf(request.getParameter("idPost"));

			Post p = new Post();

			p.setId(id_post);
			p.setTexto(post);
			p.setAutor(vendedor.getId_vendedor());

			if (StringUtils.isNotBlank(p.getTexto())) {

				postEJB.editarPost(p);
				ArrayList<Producto> productos = productoEJB.getProductosVendedor(vendedor.getId_vendedor());
				ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
				ArrayList<Formato> formatos = formatoEJB.getFormatos();
				ArrayList<Post> posts = postEJB.getPostsVendedor(vendedor.getId_vendedor());

				request.setAttribute("productos", productos);
				request.setAttribute("posts", posts);
				request.setAttribute("vendedor", vendedor);
				request.setAttribute("categorias", categorias);
				request.setAttribute("formatos", formatos);

				rs.forward(request, response);

			} else {
				ArrayList<Producto> productos = productoEJB.getProductosVendedor(vendedor.getId_vendedor());
				ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
				ArrayList<Formato> formatos = formatoEJB.getFormatos();
				ArrayList<Post> posts = postEJB.getPostsVendedor(vendedor.getId_vendedor());

				request.setAttribute("productos", productos);
				request.setAttribute("posts", posts);
				request.setAttribute("vendedor", vendedor);
				request.setAttribute("categorias", categorias);
				request.setAttribute("formatos", formatos);

				rs.forward(request, response);
			}

		} else {
			response.sendRedirect("Principal");
		}

	}

}
