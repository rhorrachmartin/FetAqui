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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			if (session == null && session.getAttribute("vendedor") == null
					&& session.getAttribute("cliente") == null) {
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			if (session.getAttribute("cliente") != null) {

				if (request.getParameter("valoracion") != null && request.getParameter("id_producto") != null
						&& request.getParameter("id_cliente") != null) {

					ValoracionProducto valoracionProducto = new ValoracionProducto();

					Integer valoracion = Integer.valueOf(request.getParameter("valoracion"));
					Integer id_producto = Integer.valueOf(request.getParameter("id_producto"));
					Integer id_cliente = Integer.valueOf(request.getParameter("id_cliente"));

					valoracionProducto.setCliente(id_cliente);
					valoracionProducto.setProducto(id_producto);
					valoracionProducto.setValoracion(valoracion);

					ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
					ArrayList<Producto> productos = productoEJB.getProductos();
					ArrayList<Post> posts = postEJB.getPosts();

					request.setAttribute("posts", posts);
					request.setAttribute("productos", productos);
					request.setAttribute("categorias", categorias);

					valoracionProductoEJB.insertarValoracionProducto(valoracionProducto);

					if (request.getParameter("productos") != null) {
						RequestDispatcher rs = getServletContext().getRequestDispatcher(PRODUCTOS_LOGEADO_CLIENTE_JSP);
						rs.forward(request, response);
					} else if (request.getParameter("inicio") != null) {
						RequestDispatcher rs = getServletContext().getRequestDispatcher(HOME_LOGEADO_JSP);

						ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();

						request.setAttribute("posts", posts);
						request.setAttribute("productos", productos);
						request.setAttribute("vendedores", vendedores);

						rs.forward(request, response);
					}

				} else {
					response.sendRedirect("ObtenerTodosProductos");
				}

			} else {
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

}
