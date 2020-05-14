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
import modelo.ejb.ProductoEJB;
import modelo.ejb.SesionClienteEJB;
import modelo.ejb.SesionVendedorEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Categoria;
import modelo.pojo.Cliente;
import modelo.pojo.Producto;
import modelo.pojo.Vendedor;

/**
 * Servlet implementation class AñadirProducto
 */
@WebServlet("/PaginaVendedor")
public class PaginaVendedor extends HttpServlet {
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
	SesionVendedorEJB sesionVendedorEJB;

	/**
	 * EJB para trabajar con sesiones de cliente
	 */
	@EJB
	SesionClienteEJB sesionClienteEJB;
	/**
	 * EJB para trabajar con los logger
	 */
	@EJB
	LoggersEJB logger;

	static final String VENDEDOR_NO_LOGEADO_JSP = "/VendedorNoLogeado.jsp";
	static final String VENDEDOR_LOGEADO_VENDEDOR_JSP = "/VendedorLogeadoVendedor.jsp";
	static final String VENDEDOR_LOGEADO_CLIENTE_JSP = "/VendedorLogeadoCliente.jsp";
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);

		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(VENDEDOR_NO_LOGEADO_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		// Intentamos obtener el usuario de la sesión
		Vendedor v = sesionVendedorEJB.vendedorLogeado(session);
		Cliente c = sesionClienteEJB.clienteLogeado(session);

		request.setAttribute("v", v);
		request.setAttribute("c", c);

		if (v != null || c != null) {
			try {
				if (c != null) {
					rs = getServletContext().getRequestDispatcher(VENDEDOR_LOGEADO_CLIENTE_JSP);

					Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));

					if (request.getParameter("selectCategorias") == null) {

						ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
						ArrayList<Producto> productos = productoEJB.getProductosVendedor(id_vendedor);
						Vendedor vendedor2 = vendedorEJB.getVendedorPorId(id_vendedor);

						request.setAttribute("vendedor", vendedor2);
						request.setAttribute("productos", productos);
						request.setAttribute("categorias", categorias);

						rs.forward(request, response);

					} else {

						if (!request.getParameter("selectCategorias").equals("todos")) {
							Integer id_categoria = Integer.valueOf(request.getParameter("selectCategorias"));

							ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
							ArrayList<Producto> productos = productoEJB.getProductosVendedorCategoria(id_vendedor,
									id_categoria);

							Vendedor vendedor3 = vendedorEJB.getVendedorPorId(id_vendedor);
							Categoria categoria = categoriaEJB.getCategoriaPorId(id_categoria);

							if (productos.isEmpty()) {
								String error = "No hay productos en esta categoría";
								request.setAttribute("error", error);
							}

							request.setAttribute("categoria", categoria);
							request.setAttribute("vendedor", vendedor3);
							request.setAttribute("productos", productos);
							request.setAttribute("categorias", categorias);

							rs.forward(request, response);
						} else {
							ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
							ArrayList<Producto> productos = productoEJB.getProductosVendedor(id_vendedor);

							Vendedor vendedor4 = vendedorEJB.getVendedorPorId(id_vendedor);

							request.setAttribute("vendedor", vendedor4);
							request.setAttribute("productos", productos);
							request.setAttribute("categorias", categorias);

							rs.forward(request, response);
						}

					}

				} else {
					rs = getServletContext().getRequestDispatcher(VENDEDOR_LOGEADO_VENDEDOR_JSP);
					Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));

					if (request.getParameter("selectCategorias") == null) {
						ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
						ArrayList<Producto> productos = productoEJB.getProductosVendedor(id_vendedor);
						Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);

						request.setAttribute("vendedor", vendedor);
						request.setAttribute("productos", productos);
						request.setAttribute("categorias", categorias);

						rs.forward(request, response);
					} else {

						if (!request.getParameter("selectCategorias").equals("todos")) {
							Integer id_categoria = Integer.valueOf(request.getParameter("selectCategorias"));

							ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
							ArrayList<Producto> productos = productoEJB.getProductosVendedorCategoria(id_vendedor,
									id_categoria);

							Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);
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
							ArrayList<Producto> productos = productoEJB.getProductosVendedor(id_vendedor);

							Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);

							request.setAttribute("vendedor", vendedor);
							request.setAttribute("productos", productos);
							request.setAttribute("categorias", categorias);

							rs.forward(request, response);
						}

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));

			if (request.getParameter("selectCategorias") == null) {
				ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
				ArrayList<Producto> productos = productoEJB.getProductosVendedor(id_vendedor);
				Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);

				request.setAttribute("vendedor", vendedor);
				request.setAttribute("productos", productos);
				request.setAttribute("categorias", categorias);

				rs.forward(request, response);
			} else {

				if (!request.getParameter("selectCategorias").equals("todos")) {
					Integer id_categoria = Integer.valueOf(request.getParameter("selectCategorias"));

					ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
					ArrayList<Producto> productos = productoEJB.getProductosVendedorCategoria(id_vendedor,
							id_categoria);

					Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);
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
					ArrayList<Producto> productos = productoEJB.getProductosVendedor(id_vendedor);

					Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);

					request.setAttribute("vendedor", vendedor);
					request.setAttribute("productos", productos);
					request.setAttribute("categorias", categorias);

					rs.forward(request, response);
				}

			}
		}
	}

}
