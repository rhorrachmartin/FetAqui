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
 * Clase encargada de cargar la página de un vendedor
 * 
 * @author ramon
 *
 */
@WebServlet("/PaginaVendedor")
public class PaginaVendedor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	VendedorEJB vendedorEJB;

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

	static final String VENDEDOR_NO_LOGEADO_JSP = "/VendedorNoLogeado.jsp";
	static final String VENDEDOR_LOGEADO_VENDEDOR_JSP = "/VendedorLogeadoVendedor.jsp";
	static final String VENDEDOR_LOGEADO_CLIENTE_JSP = "/VendedorLogeadoCliente.jsp";
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	/**
	 * Método doGet encargado de cargar la página de un vendedor en función del tipo de usuario logeado
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);

		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(VENDEDOR_NO_LOGEADO_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			// Intentamos obtener el usuario de la sesión
			Vendedor v = sesionVendedorEJB.vendedorLogeado(session);
			Cliente c = sesionClienteEJB.clienteLogeado(session);

			// Comprobamos que exista usuario en la sesión
			if (v != null || c != null) {

				// Si el usuario es cliente
				if (c != null) {

					// Lo metemos en la request
					request.setAttribute("c", c);

					// RS hacia VENDEDOR_LOGEADO_CLIENTE_JSP
					rs = getServletContext().getRequestDispatcher(VENDEDOR_LOGEADO_CLIENTE_JSP);

					// Recogemos el id del vendedor
					Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));

					// Cargamos todas las categorias y productos junto con el vendedor
					ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
					ArrayList<Producto> productos = productoEJB.getProductosVendedor(id_vendedor);
					Vendedor vendedor2 = vendedorEJB.getVendedorPorId(id_vendedor);

					// Lo metemos en la request
					request.setAttribute("vendedor", vendedor2);
					request.setAttribute("productos", productos);
					request.setAttribute("categorias", categorias);

					// Redirigimos a VENDEDOR_LOGEADO_CLIENTE_JSP
					rs.forward(request, response);

				} else {

					// Si no es cliente es vendedor, lo metemos en la request
					request.setAttribute("v", v);

					// RS hacia VENDEDOR_LOGEADO_VENDEDOR_JSP
					rs = getServletContext().getRequestDispatcher(VENDEDOR_LOGEADO_VENDEDOR_JSP);
					Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));

					// CArgamos todas las categorias, productos y al vendedor
					ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
					ArrayList<Producto> productos = productoEJB.getProductosVendedor(id_vendedor);
					Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);

					// Lo metemos en la request
					request.setAttribute("vendedor", vendedor);
					request.setAttribute("productos", productos);
					request.setAttribute("categorias", categorias);

					// Redirigimos a VENDEDOR_LOGEADO_VENDEDOR_JSP
					rs.forward(request, response);
				}

			} else {

				// Si no hay sesión redirigimos a VENDEDOR_NO_LOGEADO_JSP

				// Recogemos el id del vendedor
				Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));

				// Recogemos las categorias, productos y al vendedor
				ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
				ArrayList<Producto> productos = productoEJB.getProductosVendedor(id_vendedor);
				Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);

				// Lo metemos todo en la request
				request.setAttribute("vendedor", vendedor);
				request.setAttribute("productos", productos);
				request.setAttribute("categorias", categorias);

				// Redirigimos hacia VENDEDOR_NO_LOGEADO_JSP
				rs.forward(request, response);
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}
	}
	
	/**
	 * Método doPost encargado de mostrar la página de un vendedor y de filtrar sus productos por categorías
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(VENDEDOR_NO_LOGEADO_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			// Intentamos obtener el usuario de la sesión
			Vendedor v = sesionVendedorEJB.vendedorLogeado(session);
			Cliente c = sesionClienteEJB.clienteLogeado(session);

			// Comprobamos que existe algún usuario en la sesión
			if (v != null || c != null) {
				
				//Si el usuario es cliente
				if (c != null) {
					
					//Lo metemos en la request
					request.setAttribute("c", c);
					
					//RS hacia VENDEDOR_LOGEADO_CLIENTE_JSP
					rs = getServletContext().getRequestDispatcher(VENDEDOR_LOGEADO_CLIENTE_JSP);
					
					//Recogemos la id del vendedor
					Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));
					
					//Comprobamos si recibimos parámetro selectCategorias
					if (request.getParameter("selectCategorias") == null) {
						
						//Si es nulo cargamos todas las categorias, todos los productos y el vendedor
						ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
						ArrayList<Producto> productos = productoEJB.getProductosVendedor(id_vendedor);
						Vendedor vendedor2 = vendedorEJB.getVendedorPorId(id_vendedor);
						
						//Lo metemos todo en la request
						request.setAttribute("vendedor", vendedor2);
						request.setAttribute("productos", productos);
						request.setAttribute("categorias", categorias);
						
						//Redirigimos hacia VENDEDOR_LOGEADO_CLIENTE_JSP
						rs.forward(request, response);

					} else {	
						
						//Si selectCategorias no equivale a "todos"
						if (!request.getParameter("selectCategorias").equals("todos")) {
							
							//REcogemos la id de la categoria
							Integer id_categoria = Integer.valueOf(request.getParameter("selectCategorias"));
							
							//Recogemos todas las categorias, los productos y al vendedor
							ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
							ArrayList<Producto> productos = productoEJB.getProductosVendedorCategoria(id_vendedor,
									id_categoria);
							Vendedor vendedor3 = vendedorEJB.getVendedorPorId(id_vendedor);
							Categoria categoria = categoriaEJB.getCategoriaPorId(id_categoria);
							
							//Si el arraylist de productos está vacío mostramos un mensaje de error
							if (productos.isEmpty()) {
								String error = "No hay productos en esta categoría";
								request.setAttribute("error", error);
							}
							
							//Lo metemos todo en la request
							request.setAttribute("categoria", categoria);
							request.setAttribute("vendedor", vendedor3);
							request.setAttribute("productos", productos);
							request.setAttribute("categorias", categorias);
							
							//Redirigimos hacia VENDEDOR_LOGEADO_CLIENTE_JSP
							rs.forward(request, response);
						} else {
							
							//Si equivale a todos cargamos todos los productos de todas las categorias y al vendedor
							ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
							ArrayList<Producto> productos = productoEJB.getProductosVendedor(id_vendedor);
							Vendedor vendedor4 = vendedorEJB.getVendedorPorId(id_vendedor);
							
							//Lo metemos todo en la request
							request.setAttribute("vendedor", vendedor4);
							request.setAttribute("productos", productos);
							request.setAttribute("categorias", categorias);
							
							//RS hacia VENDEDOR_LOGEADO_CLIENTE_JSP
							rs.forward(request, response);
						}

					}

				} else {
					
					//Si no es cliente es vendedor
					request.setAttribute("v", v);
					
					//RS hacia VENDEDOR_LOGEADO_VENDEDOR_JSP
					rs = getServletContext().getRequestDispatcher(VENDEDOR_LOGEADO_VENDEDOR_JSP);
					
					//Recogemos la id del vendedor
					Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));
					
					//Comprobamos si recibimos el parámetro selectCategorias
					if (request.getParameter("selectCategorias") == null) {
						
						//Si es nulo cargamos todos los productos de todas las categorias y al vendedor
						ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
						ArrayList<Producto> productos = productoEJB.getProductosVendedor(id_vendedor);
						Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);
						
						//Lo metemos todo en la request
						request.setAttribute("vendedor", vendedor);
						request.setAttribute("productos", productos);
						request.setAttribute("categorias", categorias);
						
						//Redirigimos hacia VENDEDOR_LOGEADO_VENDEDOR_JSP
						rs.forward(request, response);
					} else {
						
						//Si el parámetro selectCategorias no equivale a "todos"
						if (!request.getParameter("selectCategorias").equals("todos")) {
							
							//Recogemos la id de la categoria
							Integer id_categoria = Integer.valueOf(request.getParameter("selectCategorias"));
							
							//Recogemos todas las categorias, los productos de la categoria y al vendedor
							ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
							ArrayList<Producto> productos = productoEJB.getProductosVendedorCategoria(id_vendedor,
									id_categoria);
							Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);
							Categoria categoria = categoriaEJB.getCategoriaPorId(id_categoria);
							
							//Si el arraylist está vacío mostramos un mensaje de error
							if (productos.isEmpty()) {
								String error = "No hay productos en esta categoría";
								request.setAttribute("error", error);
							}
							
							//Lo metemos todo en la request
							request.setAttribute("categoria", categoria);
							request.setAttribute("vendedor", vendedor);
							request.setAttribute("productos", productos);
							request.setAttribute("categorias", categorias);
							
							//RS hacia VENDEDOR_LOGEADO_VENDEDOR_JSP
							rs.forward(request, response);
						} else {
							
							//Si equivale a todos cargamos todos los productos de todas las categorias y al vendedor
							ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
							ArrayList<Producto> productos = productoEJB.getProductosVendedor(id_vendedor);
							Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);
							
							//Lo metemos todo en la request
							request.setAttribute("vendedor", vendedor);
							request.setAttribute("productos", productos);
							request.setAttribute("categorias", categorias);
							
							//RS hacia VENDEDOR_LOGEADO_VENDEDOR_JSP
							rs.forward(request, response);
						}

					}
				}
			} else {
				
				//Si no hay sesión 
				
				//Recogemos la id del vendedor
				Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));
				
				
				//Comprobamos si el parámetro selectCategorias es nulo
				if (request.getParameter("selectCategorias") == null) {
					
					//CArgamos todos los productos de todas las categorias y al vendedor
					ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
					ArrayList<Producto> productos = productoEJB.getProductosVendedor(id_vendedor);
					Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);
					
					//Lo metemos en la request
					request.setAttribute("vendedor", vendedor);
					request.setAttribute("productos", productos);
					request.setAttribute("categorias", categorias);
					
					//Redirigimos hacia VENDEDOR_LOGEADO_VENDEDOR_JSP
					rs.forward(request, response);
				} else {
					
					//Si noes nulo comprobamos si equivale a "todos"
					if (!request.getParameter("selectCategorias").equals("todos")) {
						
						//Si no equivale a "todos" cargamos los productos de la categoria recibida  al vendedor
						Integer id_categoria = Integer.valueOf(request.getParameter("selectCategorias"));
						ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
						ArrayList<Producto> productos = productoEJB.getProductosVendedorCategoria(id_vendedor,
								id_categoria);
						Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);
						Categoria categoria = categoriaEJB.getCategoriaPorId(id_categoria);
						
						//Si el arraylist está vacío mostramos un mensaje de error
						if (productos.isEmpty()) {
							String error = "No hay productos en esta categoría";
							request.setAttribute("error", error);
						}
						
						//Lo metemos en la request
						request.setAttribute("categoria", categoria);
						request.setAttribute("vendedor", vendedor);
						request.setAttribute("productos", productos);
						request.setAttribute("categorias", categorias);
						
						//Redirigimos hacia VENDEDOR_NO_LOGEADO_JSP
						rs.forward(request, response);
					} else {
						
						//Si selectCategorias equivale a "todos"
						
						//CArgamos todos los productos de todas las categorias y al vendedor
						ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
						ArrayList<Producto> productos = productoEJB.getProductosVendedor(id_vendedor);
						Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);
						
						//Lo metemos en la request
						request.setAttribute("vendedor", vendedor);
						request.setAttribute("productos", productos);
						request.setAttribute("categorias", categorias);
						
						//Redirigimos hacia VENDEDOR_NO_LOGEADO_JSP
						rs.forward(request, response);
					}

				}
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

}
