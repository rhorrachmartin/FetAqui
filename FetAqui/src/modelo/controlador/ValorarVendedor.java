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
import modelo.ejb.ValoracionVendedorEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Post;
import modelo.pojo.Producto;
import modelo.pojo.ValoracionCv;
import modelo.pojo.Vendedor;

/**
 * Clase encargada de insertar y actualizar la valoración de un Vendedor
 * 
 * @author ramon
 *
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

	@EJB
	LoggersEJB logger;

	static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	static final String PRODUCTOS_LOGEADO_JSP = "/ProductosLogeadoCliente.jsp";
	static final String VENDEDOR_LOGEADO_CLIENTE_JSP = "/VendedorLogeadoCliente.jsp";
	static final String HOME_LOGEADO_JSP = "/HomeLogeado.jsp";

	/**
	 * Método doPost encargado de insertar la valoración de un vendedor
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {

			// Comprobamos que exista el usuario cliente en la sesión
			if (session.getAttribute("cliente") != null) {

				// Si recibimos los parámetros de valoracion, id vendedor e id cliente
				if (request.getParameter("valoracion") != null && request.getParameter("id_vendedor") != null
						&& request.getParameter("id_cliente") != null) {

					// Creamos el pojo valoracion cliente vendedor
					ValoracionCv valoracionCv = new ValoracionCv();

					// Recogemos los parámetros y lo seteamos
					Integer valoracion = Integer.valueOf(request.getParameter("valoracion"));
					Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));
					Integer id_cliente = Integer.valueOf(request.getParameter("id_cliente"));

					valoracionCv.setId_cliente(id_cliente);
					valoracionCv.setId_vendedor(id_vendedor);
					valoracionCv.setValoracion(valoracion);

					// Cargamos todos los productos vendedores y posts
					ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();
					ArrayList<Producto> productos = productoEJB.getProductos();
					ArrayList<Post> posts = postEJB.getPosts();

					// Lo pasamos todo a la request
					request.setAttribute("vendedores", vendedores);
					request.setAttribute("productos", productos);
					request.setAttribute("posts", posts);

					// Insertamos la valoración en BD
					valoracionVendedorEJB.insertarValoracionVendedor(valoracionCv);

					// Si recibimos el parámetro paginaVendedor
					if (request.getParameter("paginaVendedor") != null) {

						// REdirigimos a la página de ese vendedor
						response.sendRedirect("PaginaVendedor?id_vendedor=" + id_vendedor);

						// Si recibimos el parámetro "principal"
					} else if (request.getParameter("principal") != null) {

						// Redirigimos a HOME_LOGEADO_JSP
						RequestDispatcher rs = getServletContext().getRequestDispatcher(HOME_LOGEADO_JSP);
						rs.forward(request, response);

						// Si recibimos el parámetro paginaVendedores
					} else if (request.getParameter("paginaVendedores") != null) {

						// REdirigimos a ObtenerTodosVendedores
						response.sendRedirect("ObtenerTodosVendedores");
					}

				} else {

					// Si no hemos recibido el parámetro ObtenerTodosProductos
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
