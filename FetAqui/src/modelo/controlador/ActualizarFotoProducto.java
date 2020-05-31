package modelo.controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ejb.CategoriaEJB;
import modelo.ejb.FormatoEJB;
import modelo.ejb.ImagenesEJB;
import modelo.ejb.LoggersEJB;
import modelo.ejb.ProductoEJB;
import modelo.pojo.Categoria;
import modelo.pojo.Formato;
import modelo.pojo.Producto;
import modelo.pojo.Vendedor;

/**
 * Clase controladora encargada de actualizar la foto de un producto
 * 
 * @author ramon
 *
 */
@WebServlet("/ActualizarFotoProducto")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ActualizarFotoProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	CategoriaEJB categoriaEJB;

	@EJB
	FormatoEJB formatoEJB;

	@EJB
	ProductoEJB productoEJB;

	@EJB
	LoggersEJB logger;

	@EJB
	ImagenesEJB imagenesEJB;

	static final String PRODUCTOS_JSP = "/Productos.jsp";
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PRODUCTOS_JSP);

		response.setContentType(CONTENT_TYPE);
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {

			// Recogemos el usuario vendedor de la sesión
			Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");

			// Si hay sesión y si encontramos vendedor
			if (session != null && vendedor.getNombre() != null) {

				// Recogemos tanto categorias como formatos
				ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
				ArrayList<Formato> formatos = formatoEJB.getFormatos();

				// Lo introducimos en la request
				request.setAttribute("vendedor", vendedor);
				request.setAttribute("categorias", categorias);
				request.setAttribute("formatos", formatos);

				// Redirigimos a PRODUCTOS_JSP
				rs.forward(request, response);

			} else {

				// Si no hay sesión redigirimos a Principal
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Recogemos el contexto
		ServletContext contexto = getServletContext();

		// Creamos el RequestDispatcher por defecto hacia Registro.jsp
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PRODUCTOS_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			
			//Recogemos la id del producto a actualizar
			Integer id_producto = Integer.valueOf(request.getParameter("idProducto"));
			
			//Recogemos al vendedor de la sesión
			Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");
			
			//ArrayList de producto a rellenar
			ArrayList<Producto> productos = null;
			
			//Si existe vendedor en la sesión
			if (vendedor.getNombre() != null) {
				
				//Comprobamos que exista un producto con esa id
				if (productoEJB.getProductoPorId(id_producto) != null) {
					
					Producto producto = new Producto();
					
					//Recogemos las categorías y los formatos
					ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
					ArrayList<Formato> formatos = formatoEJB.getFormatos();
					
					//Recogemos el nombre de la foto y la guardamos en disco
					String foto = imagenesEJB.guardarImagen(request, contexto);
					
					//Setemos el producto
					producto.setId(id_producto);
					producto.setFoto(foto);
					
					//Actualizamos el mismo
					productoEJB.actualizarImagenProducto(producto);
					
					//Recogemos todos los productos del vendedor
					productos = productoEJB.getProductosVendedor(vendedor.getId_vendedor());
					
					//Lo pasamos todo a la request
					request.setAttribute("productos", productos);
					request.setAttribute("vendedor", vendedor);
					request.setAttribute("categorias", categorias);
					request.setAttribute("formatos", formatos);
					
					//Redirigimos a PRODUCTOS_JSP
					rs.forward(request, response);
					
					//Si no existe producto con esa id
				} else {
					
					//Recogemos todos los productos del vendedor junto con las categorías y 
					//formatos
					productos = productoEJB.getProductosVendedor(vendedor.getId_vendedor());
					ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
					ArrayList<Formato> formatos = formatoEJB.getFormatos();
					
					//Lo pasamos todo a la request
					request.setAttribute("productos", productos);
					request.setAttribute("vendedor", vendedor);
					request.setAttribute("categorias", categorias);
					request.setAttribute("formatos", formatos);
					
					//Redirigimos a PRODUCTOS_JSP
					rs.forward(request, response);
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
