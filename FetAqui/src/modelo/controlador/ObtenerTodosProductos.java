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
	
	/**
	 * Método doGet encargado en eneseñar la página de productos en función de un usuario no logeado, cliente o vendedor.
	 */	
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

			// Si existe algún tipo de usuario en la sesión
			if (v != null || c != null) {

				// Si el usuario es cliente
				if (c != null) {
					// Lo metemos en la request
					request.setAttribute("cliente", c);

					// Cambiamos el request dispatcher a PRODUCTOS_LOGEADO_CLIENTE_JSP
					rs = getServletContext().getRequestDispatcher(PRODUCTOS_LOGEADO_CLIENTE_JSP);

					// Obtenemos todos los productos y todas las categorias
					ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
					ArrayList<Producto> productos = productoEJB.getProductos();

					// Lo introducimos en la request
					request.setAttribute("productos", productos);
					request.setAttribute("categorias", categorias);

					// Si existe un atributo de error en la sesión
					if (session.getAttribute("error") != null) {

						// Lo recogemos y lo pasamos a la request
						String error = (String) session.getAttribute("error");

						request.setAttribute("error", error);
					}

					// REdirigimos a PRODUCTOS_LOGEADO_CLIENTE_JSP
					rs.forward(request, response);

				} else {

					// Si el usuario es vendedor lo metemos en la request
					request.setAttribute("vendedor", v);

					// Cambiamos el request dispatcher a PRODUCTOS_LOGEADO_VENDEDOR_JSP
					rs = getServletContext().getRequestDispatcher(PRODUCTOS_LOGEADO_VENDEDOR_JSP);
					
					// Obtenemos todos los productos y todas las categorias
					ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
					ArrayList<Producto> productos = productoEJB.getProductos();
					
					// Lo introducimos en la request
					request.setAttribute("productos", productos);
					request.setAttribute("categorias", categorias);
					
					// REdirigimos a PRODUCTOS_LOGEADO_VENDEDOR_JSP
					rs.forward(request, response);

				}

			} else {
				//Si no hay sesión solo cargamos los productos y categorias

				ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
				ArrayList<Producto> productos = productoEJB.getProductos();
				
				// Lo introducimos en la request
				request.setAttribute("productos", productos);
				request.setAttribute("categorias", categorias);
				
				// REdirigimos a PRODUCTOS_NO_LOGEADO_JSP
				rs.forward(request, response);

			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}
	}
	
	/**
	 * Método doPost encargado de filtrar los productos por categorías
	 */
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
			
			//Comprobamos si hay algún tipo de usuario en la sesión
			if (v != null || c != null) {
				
				//Si existe un usuario cliente en la sesión
				if (c != null) {
					
					//Introducimos el cliente en la request
					request.setAttribute("cliente", c);
					
					//Request Dispatcher hacia PRODUCTOS_LOGEADO_CLIENTE_JSP
					rs = getServletContext().getRequestDispatcher(PRODUCTOS_LOGEADO_CLIENTE_JSP);
					
					//Si no existe parámetro selectCategorias
					if (request.getParameter("selectCategorias") == null) {
						
						//Cargamos todos los productos y categrías
						ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
						ArrayList<Producto> productos = productoEJB.getProductos();
						
						//Lo pasamos a la request
						request.setAttribute("productos", productos);
						request.setAttribute("categorias", categorias);
						
						//Redirigimos a PRODUCTOS_LOGEADO_CLIENTE_JSP
						rs.forward(request, response);

					} else {
						//Si existe parámetro selectCategorias
						//Comprobamos si es diferente a "todos"
						if (!request.getParameter("selectCategorias").equals("todos")) {
							
							//Recogemos la id de la categoría de productos a mostrar
							Integer id_categoria = Integer.valueOf(request.getParameter("selectCategorias"));
							
							//Recogemos los productos de la categoría y las categorias
							ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
							ArrayList<Producto> productos = productoEJB.getProductosCategoria(id_categoria);
							Categoria categoria = categoriaEJB.getCategoriaPorId(id_categoria);
							
							//Si es arraylist está vacío mostramos un mensaje de error
							if (productos.isEmpty()) {
								String error = "No existen productos en esta categoría.";
								request.setAttribute("categoria", categoria);
								request.setAttribute("error", error);
							}
							
							//Lo metemos todo en la request
							request.setAttribute("categoria", categoria);
							request.setAttribute("productos", productos);
							request.setAttribute("categorias", categorias);
							
							//Redirigimos a PRODUCTOS_LOGEADO_CLIENTE_JSP
							rs.forward(request, response);
						} else {
							
							//Si selectCategoria es igual a "todos"
							
							//Cargamos todos los productos y categorías
							ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
							ArrayList<Producto> productos = productoEJB.getProductos();
							
							//Lo metemos en la request
							request.setAttribute("productos", productos);
							request.setAttribute("categorias", categorias);
							
							//Redirigimos a PRODUCTOS_LOGEADO_CLIENTE_JSP
							rs.forward(request, response);
						}

					}

				} else {
					
					//Si no es cliente es Vendedor, así que lo metemos en la request.
					request.setAttribute("vendedor", v);
					
					//Request Dispatcher hacia PRODUCTOS_LOGEADO_VENDEDOR_JSP
					rs = getServletContext().getRequestDispatcher(PRODUCTOS_LOGEADO_VENDEDOR_JSP);
					
					//Si no recibimos ningun parámetro selectCategorias
					if (request.getParameter("selectCategorias") == null) {
						//CArgamos todos los productos y categorías
						ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
						ArrayList<Producto> productos = productoEJB.getProductos();
						
						//Lo metemos en la request
						request.setAttribute("productos", productos);
						request.setAttribute("categorias", categorias);
						
						//Reidirigmos a PRODUCTOS_LOGEADO_VENDEDOR_JSP
						rs.forward(request, response);

					} else {
						
						//Si recibimos parámetro selectCategorias y es diferente  a "todos"
						if (!request.getParameter("selectCategorias").equals("todos")) {
							
							//Recogemos el id de la categoría de productos
							Integer id_categoria = Integer.valueOf(request.getParameter("selectCategorias"));
							
							//Cargamos todas las categorías y los productos de la categoría
							ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
							ArrayList<Producto> productos = productoEJB.getProductosCategoria(id_categoria);
							Categoria categoria = categoriaEJB.getCategoriaPorId(id_categoria);
							
							//Si el arraylist está vacío mostramos un mensaje de error
							if (productos.isEmpty()) {
								String error = "No existen productos en esta categoría.";
								request.setAttribute("categoria", categoria);
								request.setAttribute("error", error);
							}
							
							//Lo insertamos todo en la request.
							request.setAttribute("categoria", categoria);
							request.setAttribute("productos", productos);
							request.setAttribute("categorias", categorias);
							
							//REdirigimos a PRODUCTOS_LOGEADO_VENDEDOR_JSP
							rs.forward(request, response);
						} else {
							//Si el parámetro selectCategorias es igual a "todos"
							//Cargamos todos los productos y categorías
							
							ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
							ArrayList<Producto> productos = productoEJB.getProductos();
							
							//Lo metemos en la request
							request.setAttribute("productos", productos);
							request.setAttribute("categorias", categorias);
							
							//Redirigimos a PRODUCTOS_LOGEADO_VENDEDOR_JSP
							rs.forward(request, response);

						}

					}

				}

			} else {
				//Si no ha sesión hacemos el mismo procedimiento pero sin carga ningún usuario en la request ni cambiando
				//el RS
				
				//Si no recibimos ningun parámetro selectCategorias
				if (request.getParameter("selectCategorias") == null) {
					
					//Cargamos todos los productos y categorías
					ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
					ArrayList<Producto> productos = productoEJB.getProductos();
					
					//Lo metemos todo en la request
					request.setAttribute("productos", productos);
					request.setAttribute("categorias", categorias);
					
					//Redirigimos a PRODUCTOS_NO_LOGEADO_JSP
					rs.forward(request, response);

				} else {
					//Si recibimos parámetro selectCategorias y es diferente  a "todos"
					if (!request.getParameter("selectCategorias").equals("todos")) {
						
						//Recogemos el id de la categoría de productos
						Integer id_categoria = Integer.valueOf(request.getParameter("selectCategorias"));
						
						//Cargamos todas las categorías y los productos de la categoría
						ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
						ArrayList<Producto> productos = productoEJB.getProductosCategoria(id_categoria);
						Categoria categoria = categoriaEJB.getCategoriaPorId(id_categoria);
						
						//Si el arraylist está vacío mostramos un mensaje de error
						if (productos.isEmpty()) {
							String error = "No existen productos en esta categoría.";
							request.setAttribute("categoria", categoria);
							request.setAttribute("error", error);
						}
						
						//Lo insertamos todo en la request.
						request.setAttribute("categoria", categoria);
						request.setAttribute("productos", productos);
						request.setAttribute("categorias", categorias);
						
						//Redirigimos a PRODUCTOS_NO_LOGEADO_JSP
						rs.forward(request, response);
					} else {
						
						//Si el parámetro selectCategorias es igual a "todos"
						//Cargamos todos los productos y categorías
						ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
						ArrayList<Producto> productos = productoEJB.getProductos();
						
						//Lo insertamos todo en la request.
						request.setAttribute("productos", productos);
						request.setAttribute("categorias", categorias);
						
						//Redirigimos a PRODUCTOS_NO_LOGEADO_JSP
						rs.forward(request, response);
					}

				}
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}
	}

}
