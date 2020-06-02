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
import modelo.ejb.ValoracionPostEJB;
import modelo.ejb.ValoracionProductoEJB;
import modelo.pojo.Categoria;
import modelo.pojo.Formato;
import modelo.pojo.Post;
import modelo.pojo.Producto;
import modelo.pojo.ValoracionPost;
import modelo.pojo.Vendedor;

/**
 * Clase encargada de insertar y publicar una noticia de un Vendedor
 * 
 * @author ramon
 *
 */
@WebServlet("/PublicarNoticia")
public class PublicarNoticia extends HttpServlet {
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
	ValoracionPostEJB valoracionPostEJB;

	@EJB
	LoggersEJB logger;

	static final String PAGINA_PROPIA_VENDEDOR = "/PaginaPropiaVendedor.jsp";
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PAGINA_PROPIA_VENDEDOR);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			// Intentamos recoger al usuario vendedor de la sesión
			Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");

			// Si hay sesión y vendedor en la misma
			if (session != null && vendedor.getNombre() != null) {

				// Recogemos el texto de la noticia
				String post = request.getParameter("post");

				// Controlamos los espacios, saltos de línea y tabulaciones
				post.replace("\n", "").replace("\t", "").replace("\r", "").replace(" ", "");

				// Creamos el pojo de la noticia
				Post p = new Post();

				// Lo seteamos
				p.setTexto(post);
				p.setAutor(vendedor.getId_vendedor());

				// Comprobamos que el post no esté completamente en blanco
				if (StringUtils.isNotBlank(p.getTexto())) {

					// Lo insertamos en BD
					postEJB.insertarPost(p);

					// Reocgemos todos los productos, categorias, formatos y posts
					ArrayList<Producto> productos = productoEJB.getProductosVendedor(vendedor.getId_vendedor());
					ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
					ArrayList<Formato> formatos = formatoEJB.getFormatos();
					ArrayList<Post> posts = postEJB.getPostsVendedor(vendedor.getId_vendedor());

					// Generamos el pojo valoracion para insertar la valoración por defecto de un
					// post
					ValoracionPost valoracion = new ValoracionPost();

					// La valoración por defecto está asociada al usuario creado por defecto en el
					// script de la BD, este no se debe borrar
					valoracion.setId_cliente(1);
					valoracion.setId_post(p.getId());
					valoracion.setValoracion(5);

					// Insertamos la valoración
					valoracionPostEJB.insertarValoracionPostPorDefecto(valoracion);

					// Lo metemos todo en la request
					request.setAttribute("productos", productos);
					request.setAttribute("posts", posts);
					request.setAttribute("vendedor", vendedor);
					request.setAttribute("categorias", categorias);
					request.setAttribute("formatos", formatos);

					// Redirigimos a PAGINA_PROPIA_VENDEDOR
					rs.forward(request, response);

				} else {

					// Si el post está en blanco no hacemos nada, volvemos a cargar todos los datos
					// necesarios
					// para cargar la propia página del vendedor

					ArrayList<Producto> productos = productoEJB.getProductosVendedor(vendedor.getId_vendedor());
					ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
					ArrayList<Formato> formatos = formatoEJB.getFormatos();
					ArrayList<Post> posts = postEJB.getPostsVendedor(vendedor.getId_vendedor());

					request.setAttribute("productos", productos);
					request.setAttribute("posts", posts);
					request.setAttribute("vendedor", vendedor);
					request.setAttribute("categorias", categorias);
					request.setAttribute("formatos", formatos);

					// Redirigimos a PAGINA_PROPIA_VENDEDOR
					rs.forward(request, response);
				}

			} else {

				// Si no hay sesión redirigimos a Principal
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

}
