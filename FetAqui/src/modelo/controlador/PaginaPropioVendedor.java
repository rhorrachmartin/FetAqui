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
import modelo.ejb.PostEJB;
import modelo.ejb.ProductoEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Categoria;
import modelo.pojo.Formato;
import modelo.pojo.Post;
import modelo.pojo.Producto;
import modelo.pojo.Vendedor;

/**
 * Clase encargada de cargar la página propia de un vendedor
 * 
 * @author ramon
 *
 */
@WebServlet("/PaginaPropioVendedor")
public class PaginaPropioVendedor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	VendedorEJB vendedorEJB;

	@EJB
	CategoriaEJB categoriaEJB;

	@EJB
	ProductoEJB productoEJB;

	@EJB
	FormatoEJB formatoEJB;

	@EJB
	PostEJB postEJB;

	@EJB
	LoggersEJB logger;

	static final String PAGINA_PROPIA_VENDEDOR = "/PaginaPropiaVendedor.jsp";
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	/**
	 * 	Método doGret encargado de cargar la propia página de un usuario vendedor
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		
		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PAGINA_PROPIA_VENDEDOR);
		
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			
			//Intentamos recoger al usuario vendedor de a sesión
			Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");
			
			//Si hay sesión y usuario vendedor en la misma
			if (session != null && vendedor != null) {
				
				//Miramos si el parámetro selectCategorias es nulo
				if (request.getParameter("selectCategorias") == null) {
					
					//Si es nulo cargamos todos los posts, productos, formatos y categorias
					ArrayList<Post> posts = postEJB.getPostsVendedor(vendedor.getId_vendedor());
					ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
					ArrayList<Formato> formatos = formatoEJB.getFormatos();
					ArrayList<Producto> productos = productoEJB.getProductosVendedor(vendedor.getId_vendedor());
					
					//Lo metemos en la request
					request.setAttribute("posts", posts);
					request.setAttribute("vendedor", vendedor);
					request.setAttribute("formatos", formatos);
					request.setAttribute("productos", productos);
					request.setAttribute("categorias", categorias);
					
					//Redirigimos a PAGINA_PROPIA_VENDEDOR
					rs.forward(request, response);
				} else {
					
					//Si selectCategorias no es nulo comprobamos si no es igual a "todos"
					if (!request.getParameter("selectCategorias").equals("todos")) {
						
						//Recogemos la id de la categoria
						Integer id_categoria = Integer.valueOf(request.getParameter("selectCategorias"));
						
						//Cargamos todos los posts, las categorias y los productos de la categoria
						ArrayList<Post> posts = postEJB.getPostsVendedor(vendedor.getId_vendedor());
						ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
						ArrayList<Producto> productos = productoEJB
								.getProductosVendedorCategoria(vendedor.getId_vendedor(), id_categoria);
						Categoria categoria = categoriaEJB.getCategoriaPorId(id_categoria);
						
						//Si el arraylist de productos está vacío avisamos con un mensaje
						if (productos.isEmpty()) {
							String error = "No hay productos en esta categoría";
							request.setAttribute("error", error);
						}
						
						//Lo metemos todo en la request
						request.setAttribute("posts", posts);
						request.setAttribute("categoria", categoria);
						request.setAttribute("vendedor", vendedor);
						request.setAttribute("productos", productos);
						request.setAttribute("categorias", categorias);
						
						//Redirigimos a PAGINA_PROPIA_VENDEDOR
						rs.forward(request, response);
					} else {
						
						//Si selectCategorias equivale a "todos" cargamos todos los productos del vendedor. junto con los posts y categorias
						ArrayList<Post> posts = postEJB.getPostsVendedor(vendedor.getId_vendedor());
						ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
						ArrayList<Producto> productos = productoEJB.getProductosVendedor(vendedor.getId_vendedor());
						
						//Lo metemos todo en la request
						request.setAttribute("posts", posts);
						request.setAttribute("vendedor", vendedor);
						request.setAttribute("productos", productos);
						request.setAttribute("categorias", categorias);
						
						//Redirigimos a PAGINA_PROPIA_VENDEDOR
						rs.forward(request, response);
					}
				}
			} else {	
				
				//Si no hay sesión redirigimos a Principal
				response.sendRedirect("Principal");

			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

}
