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

import modelo.ejb.CategoriaEJB;
import modelo.ejb.ImagenesEJB2;
import modelo.ejb.LoggersEJB;
import modelo.ejb.ProductoEJB;
import modelo.pojo.Categoria;
import modelo.pojo.Producto;

/**
 * Servlet implementation class AñadirProducto
 */
@WebServlet("/ObtenerTodosProductos")
public class ObtenerTodosProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@EJB
	CategoriaEJB categoriaEJB;

	@EJB
	ProductoEJB productoEJB;
	/**
	 * EJB para trabajar con los logger
	 */
	@EJB
	LoggersEJB logger;

	static final String PRODUCTOS_NO_LOGEADO_JSP = "/ProductosNoLogeado.jsp";
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PRODUCTOS_NO_LOGEADO_JSP);

		response.setContentType(CONTENT_TYPE);

		try {
			if (request.getParameter("selectCategorias") == null) {

				ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
				ArrayList<Producto> productos = productoEJB.getProductos();

				request.setAttribute("productos", productos);
				request.setAttribute("categorias", categorias);

				rs.forward(request, response);

			} else {
				Integer id_categoria = Integer.valueOf(request.getParameter("selectCategorias"));
				
				ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
				ArrayList<Producto> productos = productoEJB.getProductosCategoria(id_categoria);
				Categoria categoria = categoriaEJB.getCategoriaPorId(id_categoria);
				
				if(productos.isEmpty()) {
					String error = "No existen productos en esta categoría.";
					request.setAttribute("categoria", categoria);
					request.setAttribute("error", error);
				}
				request.setAttribute("categoria", categoria);
				request.setAttribute("productos", productos);
				request.setAttribute("categorias", categorias);

				rs.forward(request, response);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
