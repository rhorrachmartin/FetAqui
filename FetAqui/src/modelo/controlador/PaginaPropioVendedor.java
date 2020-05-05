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
import modelo.ejb.ProductoEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Categoria;
import modelo.pojo.Formato;
import modelo.pojo.Producto;
import modelo.pojo.Vendedor;

/**
 * Servlet implementation class AñadirProducto
 */
@WebServlet("/PaginaPropioVendedor")
public class PaginaPropioVendedor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * EJB para trabajar con Usuarios
	 */
	@EJB
	VendedorEJB vendedorEJB;

	@EJB
	CategoriaEJB categoriaEJB;

	@EJB
	ProductoEJB productoEJB;

	@EJB
	FormatoEJB formatoEJB;
	/**
	 * EJB para trabajar con los logger
	 */
	@EJB
	LoggersEJB logger;

	static final String PAGINA_PROPIA_VENDEDOR = "/PaginaPropiaVendedor.jsp";
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PAGINA_PROPIA_VENDEDOR);

		HttpSession session = request.getSession(false);

		Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");

		response.setContentType(CONTENT_TYPE);

		try {

			if (vendedor != null) {
				if (request.getParameter("selectCategorias") == null) {
					ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
					ArrayList<Formato> formatos = formatoEJB.getFormatos();
					ArrayList<Producto> productos = productoEJB.getProductosVendedor(vendedor.getId_vendedor());

					request.setAttribute("vendedor", vendedor);
					request.setAttribute("formatos", formatos);
					request.setAttribute("productos", productos);
					request.setAttribute("categorias", categorias);

					rs = getServletContext().getRequestDispatcher(PAGINA_PROPIA_VENDEDOR);
					rs.forward(request, response);
				} else {
					if (!request.getParameter("selectCategorias").equals("todos")) {
						Integer id_categoria = Integer.valueOf(request.getParameter("selectCategorias"));

						ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
						ArrayList<Producto> productos = productoEJB.getProductosVendedorCategoria(vendedor.getId_vendedor(),
								id_categoria);
						
						Categoria categoria = categoriaEJB.getCategoriaPorId(id_categoria);

						if (productos.isEmpty()) {
							String error = "No hay productos en esta categoría";
							request.setAttribute("error", error);
						}

						request.setAttribute("categoria", categoria);
						request.setAttribute("vendedor", vendedor);
						request.setAttribute("productos", productos);
						request.setAttribute("categorias", categorias);

						rs.forward(request, response);
					} else {
						ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
						ArrayList<Producto> productos = productoEJB.getProductosVendedor(vendedor.getId_vendedor());

						request.setAttribute("vendedor", vendedor);
						request.setAttribute("productos", productos);
						request.setAttribute("categorias", categorias);

						rs.forward(request, response);
					}
				}
			} else {

				response.sendRedirect("Principal");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

