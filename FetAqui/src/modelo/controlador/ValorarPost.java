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
import modelo.pojo.Post;
import modelo.pojo.Producto;
import modelo.pojo.ValoracionPost;
import modelo.pojo.Vendedor;

/**
 * Clase encargada de insertar y actualziar la valoración de un post de un
 * Vendedor
 * 
 * @author ramon
 *
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

	@EJB
	LoggersEJB logger;

	static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	static final String PRODUCTOS_LOGEADO_JSP = "/ProductosLogeadoCliente.jsp";
	static final String HOME_LOGEADO_JSP = "/HomeLogeado.jsp";

	/**
	 * Método doPost encargado de insertar la valoración de una noticia creada por
	 * un usuario Vendedor
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {

			// Comprobamos que exista el usuario cliente en la sesión
			if (session.getAttribute("cliente") != null) {

				// Si recibimos la valoracion, la id del post y la id del cliente
				if (request.getParameter("valoracion") != null && request.getParameter("id_post") != null
						&& request.getParameter("id_cliente") != null) {

					// Creamos el pojo
					ValoracionPost valoracionPost = new ValoracionPost();

					// Recogempos los parámetros necesarios
					Integer valoracion = Integer.valueOf(request.getParameter("valoracion"));
					Integer id_post = Integer.valueOf(request.getParameter("id_post"));
					Integer id_cliente = Integer.valueOf(request.getParameter("id_cliente"));

					// Seteamos el pojo
					valoracionPost.setId_cliente(id_cliente);
					valoracionPost.setId_post(id_post);
					valoracionPost.setValoracion(valoracion);

					// Obtenemos los vendedores, los productos y los posts
					ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();
					ArrayList<Producto> productos = productoEJB.getProductos();
					ArrayList<Post> posts = postEJB.getPosts();

					// Lo metemos en la request
					request.setAttribute("vendedores", vendedores);
					request.setAttribute("productos", productos);
					request.setAttribute("posts", posts);

					// Insertamos la valoración en BD
					valoracionPostEJB.insertarValoracionPostPorDefecto(valoracionPost);

					// RS hacia HOME_LOGEADO_JSP
					RequestDispatcher rs = getServletContext().getRequestDispatcher(HOME_LOGEADO_JSP);

					rs.forward(request, response);

				} else {
					// Si no hemos recibido los parámetros necesarios redirigimos hacia
					// ObtenerTodosProductos
					response.sendRedirect("ObtenerTodosProductos");
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
