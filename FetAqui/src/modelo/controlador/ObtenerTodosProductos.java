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
import modelo.pojo.Categoria;
import modelo.pojo.Cliente;
import modelo.pojo.Producto;
import modelo.pojo.Vendedor;

/**
 * Clase controlador encargado de obtener todos los productos de todos los
 * vendedores
 * 
 * @author ramon
 *
 */
@WebServlet("/ObtenerTodosProductos")
public class ObtenerTodosProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	CategoriaEJB categoriaEJB;

	@EJB
	ProductoEJB productoEJB;

	@EJB
	SesionVendedorEJB sesionVendedorEJB;

	@EJB
	SesionClienteEJB sesionClienteEJB;

	@EJB
	LoggersEJB logger;

	static final String PRODUCTOS_NO_LOGEADO_JSP = "/ProductosNoLogeado.jsp";
	static final String PRODUCTOS_LOGEADO_VENDEDOR_JSP = "/ProductosLogeadoVendedor.jsp";
	static final String PRODUCTOS_LOGEADO_CLIENTE_JSP = "/ProductosLogeadoCliente.jsp";
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PRODUCTOS_NO_LOGEADO_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			// Intentamos obtener el usuario de la sesión
			Vendedor v = sesionVendedorEJB.vendedorLogeado(session);
			Cliente c = sesionClienteEJB.clienteLogeado(session);

			request.setAttribute("vendedor", v);
			request.setAttribute("cliente", c);

			if (v != null || c != null) {
				try {

					if (c != null) {
						rs = getServletContext().getRequestDispatcher(PRODUCTOS_LOGEADO_CLIENTE_JSP);

						if (request.getParameter("selectCategorias") == null) {

							ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
							ArrayList<Producto> productos = productoEJB.getProductos();

							request.setAttribute("productos", productos);
							request.setAttribute("categorias", categorias);

							if (session.getAttribute("error") != null) {
								String error = (String) session.getAttribute("error");

								request.setAttribute("error", error);
							}

							rs.forward(request, response);

						} else {
							if (request.getParameter("selectCategorias").equals("todos")) {
								Integer id_categoria = Integer.valueOf(request.getParameter("selectCategorias"));

								ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
								ArrayList<Producto> productos = productoEJB.getProductosCategoria(id_categoria);
								Categoria categoria = categoriaEJB.getCategoriaPorId(id_categoria);

								if (productos.isEmpty()) {
									String error = "No existen productos en esta categoría.";
									request.setAttribute("categoria", categoria);
									request.setAttribute("error", error);
								}
								request.setAttribute("categoria", categoria);
								request.setAttribute("productos", productos);
								request.setAttribute("categorias", categorias);

								rs.forward(request, response);
							} else {
								ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
								ArrayList<Producto> productos = productoEJB.getProductos();
								request.setAttribute("productos", productos);
								request.setAttribute("categorias", categorias);
								rs.forward(request, response);
							}

						}

					} else {
						rs = getServletContext().getRequestDispatcher(PRODUCTOS_LOGEADO_VENDEDOR_JSP);

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

							if (productos.isEmpty()) {
								String error = "No existen productos en esta categoría.";
								request.setAttribute("categoria", categoria);
								request.setAttribute("error", error);
							}
							request.setAttribute("categoria", categoria);
							request.setAttribute("productos", productos);
							request.setAttribute("categorias", categorias);

							rs.forward(request, response);

						}

					}
				} catch (Exception e) {
					logger.setErrorLogger(e.getMessage());
				}

			} else {
				// No está logeado, mostramos página principal
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

					if (productos.isEmpty()) {
						String error = "No existen productos en esta categoría.";
						request.setAttribute("categoria", categoria);
						request.setAttribute("error", error);
					}
					request.setAttribute("categoria", categoria);
					request.setAttribute("productos", productos);
					request.setAttribute("categorias", categorias);

					rs.forward(request, response);

				}
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PRODUCTOS_NO_LOGEADO_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			// Intentamos obtener el usuario de la sesión
			Vendedor v = sesionVendedorEJB.vendedorLogeado(session);
			Cliente c = sesionClienteEJB.clienteLogeado(session);

			request.setAttribute("vendedor", v);
			request.setAttribute("cliente", c);

			if (v != null || c != null) {
				try {

					if (c != null) {
						rs = getServletContext().getRequestDispatcher(PRODUCTOS_LOGEADO_CLIENTE_JSP);

						if (request.getParameter("selectCategorias") == null) {

							ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
							ArrayList<Producto> productos = productoEJB.getProductos();

							request.setAttribute("productos", productos);
							request.setAttribute("categorias", categorias);

							rs.forward(request, response);

						} else {
							if (!request.getParameter("selectCategorias").equals("todos")) {
								Integer id_categoria = Integer.valueOf(request.getParameter("selectCategorias"));

								ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
								ArrayList<Producto> productos = productoEJB.getProductosCategoria(id_categoria);
								Categoria categoria = categoriaEJB.getCategoriaPorId(id_categoria);

								if (productos.isEmpty()) {
									String error = "No existen productos en esta categoría.";
									request.setAttribute("categoria", categoria);
									request.setAttribute("error", error);
								}
								request.setAttribute("categoria", categoria);
								request.setAttribute("productos", productos);
								request.setAttribute("categorias", categorias);

								rs.forward(request, response);
							} else {
								ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
								ArrayList<Producto> productos = productoEJB.getProductos();

								request.setAttribute("productos", productos);
								request.setAttribute("categorias", categorias);

								rs.forward(request, response);
							}

						}

					} else {
						rs = getServletContext().getRequestDispatcher(PRODUCTOS_LOGEADO_VENDEDOR_JSP);

						if (request.getParameter("selectCategorias") == null) {

							ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
							ArrayList<Producto> productos = productoEJB.getProductos();

							request.setAttribute("productos", productos);
							request.setAttribute("categorias", categorias);

							rs.forward(request, response);

						} else {
							if (!request.getParameter("selectCategorias").equals("todos")) {
								Integer id_categoria = Integer.valueOf(request.getParameter("selectCategorias"));

								ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
								ArrayList<Producto> productos = productoEJB.getProductosCategoria(id_categoria);
								Categoria categoria = categoriaEJB.getCategoriaPorId(id_categoria);

								if (productos.isEmpty()) {
									String error = "No existen productos en esta categoría.";
									request.setAttribute("categoria", categoria);
									request.setAttribute("error", error);
								}
								request.setAttribute("categoria", categoria);
								request.setAttribute("productos", productos);
								request.setAttribute("categorias", categorias);

								rs.forward(request, response);
							} else {

								ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
								ArrayList<Producto> productos = productoEJB.getProductos();

								request.setAttribute("productos", productos);
								request.setAttribute("categorias", categorias);

								rs.forward(request, response);

							}

						}

					}
				} catch (Exception e) {
					logger.setErrorLogger(e.getMessage());
				}

			} else {
				// No está logeado, mostramos página principal
				if (request.getParameter("selectCategorias") == null) {

					ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
					ArrayList<Producto> productos = productoEJB.getProductos();

					request.setAttribute("productos", productos);
					request.setAttribute("categorias", categorias);

					rs.forward(request, response);

				} else {
					if (!request.getParameter("selectCategorias").equals("todos")) {
						Integer id_categoria = Integer.valueOf(request.getParameter("selectCategorias"));

						ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
						ArrayList<Producto> productos = productoEJB.getProductosCategoria(id_categoria);
						Categoria categoria = categoriaEJB.getCategoriaPorId(id_categoria);

						if (productos.isEmpty()) {
							String error = "No existen productos en esta categoría.";
							request.setAttribute("categoria", categoria);
							request.setAttribute("error", error);
						}
						request.setAttribute("categoria", categoria);
						request.setAttribute("productos", productos);
						request.setAttribute("categorias", categorias);

						rs.forward(request, response);
					} else {

						ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
						ArrayList<Producto> productos = productoEJB.getProductos();

						request.setAttribute("productos", productos);
						request.setAttribute("categorias", categorias);

						rs.forward(request, response);
					}

				}
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}
	}

}
