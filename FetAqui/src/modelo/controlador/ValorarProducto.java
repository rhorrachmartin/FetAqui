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
import modelo.ejb.ValoracionProductoEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Categoria;
import modelo.pojo.Post;
import modelo.pojo.Producto;
import modelo.pojo.ValoracionProducto;
import modelo.pojo.Vendedor;

/**
 * Clase encargada de insertar y actualizar la valoración de un producto
 * 
 * @author ramon
 *
 */
@WebServlet("/ValorarProducto")
public class ValorarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	ValoracionProductoEJB valoracionProductoEJB;

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
	static final String PRODUCTOS_LOGEADO_CLIENTE_JSP = "/ProductosLogeadoCliente.jsp";
	static final String HOME_LOGEADO_JSP = "/HomeLogeado.jsp";

	/**
	 * Método doPost encargado de insertar la valoración de un producto
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {

			// Si existe un usuario cliente en la sesión
			if (session.getAttribute("cliente") != null) {

				// Y recibimos los parámetros de valoracion, id del producto y la id del cliente
				if (request.getParameter("valoracion") != null && request.getParameter("id_producto") != null
						&& request.getParameter("id_cliente") != null) {

					// Creamos el pojo la valoracion del producto
					ValoracionProducto valoracionProducto = new ValoracionProducto();

					// Recogemos los parámetros para setearlo
					Integer valoracion = Integer.valueOf(request.getParameter("valoracion"));
					Integer id_producto = Integer.valueOf(request.getParameter("id_producto"));
					Integer id_cliente = Integer.valueOf(request.getParameter("id_cliente"));

					// Lo seteamos
					valoracionProducto.setCliente(id_cliente);
					valoracionProducto.setProducto(id_producto);
					valoracionProducto.setValoracion(valoracion);

					// Cargamos todas las categorias, productos y posts
					ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
					ArrayList<Producto> productos = productoEJB.getProductos();
					ArrayList<Post> posts = postEJB.getPosts();

					// Lo metemos en la request
					request.setAttribute("posts", posts);
					request.setAttribute("productos", productos);
					request.setAttribute("categorias", categorias);

					// Insertamos la valoracion en BD
					valoracionProductoEJB.insertarValoracionProducto(valoracionProducto);

					// Si recibimos el parámetro "productos"
					if (request.getParameter("productos") != null) {

						// RS hacia PRODUCTOS_LOGEADO_CLIENTE_JSP
						RequestDispatcher rs = getServletContext().getRequestDispatcher(PRODUCTOS_LOGEADO_CLIENTE_JSP);
						rs.forward(request, response);

						// Si recibimos el parámetro "inicio"
					} else if (request.getParameter("inicio") != null) {

						// RS hacia HOME_LOGEADO_JSP
						RequestDispatcher rs = getServletContext().getRequestDispatcher(HOME_LOGEADO_JSP);

						// Recogemos los vendedores
						ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();

						// Lo pasamos todo a la request
						request.setAttribute("posts", posts);
						request.setAttribute("productos", productos);
						request.setAttribute("vendedores", vendedores);

						// Redirigimos
						rs.forward(request, response);
					}

				} else {
					// Si no recibimos los parámetros necesarios redirigimos a ObtenerTodosProductos
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
