package modelo.controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ejb.CategoriaEJB;
import modelo.ejb.FormatoEJB;
import modelo.ejb.LoggersEJB;
import modelo.ejb.ProductoEJB;
import modelo.pojo.Categoria;
import modelo.pojo.Formato;
import modelo.pojo.Producto;
import modelo.pojo.Vendedor;

/**
 * Controlador encargado de actualizar los detalles de un Producto
 * 
 * @author ramon
 *
 */
@WebServlet("/ActualizarProducto")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ActualizarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	CategoriaEJB categoriaEJB;

	@EJB
	FormatoEJB formatoEJB;

	@EJB
	ProductoEJB productoEJB;

	@EJB
	LoggersEJB logger;

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
			
			//Recogemos el usuario vendedor de la sesión
			Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");
			
			//Comprobamos que exista
			if (session != null && vendedor.getNombre() != null) {
				
				//Recogemos los parámretros necesarios
				ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
				ArrayList<Formato> formatos = formatoEJB.getFormatos();
				
				//Lo pasamos todo a la request
				request.setAttribute("vendedor", vendedor);
				request.setAttribute("categorias", categorias);
				request.setAttribute("formatos", formatos);
				
				//Redirigimos a PRODUCTOS_JSP
				rs.forward(request, response);

			} else {
				//Si no hay sesión redirigimos a Principal
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

	/**
	 * Método doPost encargado de actualizar un Producto
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PRODUCTOS_JSP);
		
		response.setContentType(CONTENT_TYPE);
		
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			
			//Recogemos el usuario Vendedor de la sesión
			Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");
			
			//Comprobamos que haya sesión y que exista el usuario Vendedor
			if (session != null && vendedor.getNombre() != null) {
				
				//Creamos la instancia del pojo Producto
				Producto producto = new Producto();
				
				//ArrayList a rellenar de Producto
				ArrayList<Producto> productos = null;
				
				//Recogemos todas las categorias y formatos
				ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
				ArrayList<Formato> formatos = formatoEJB.getFormatos();
				
				//Recogemos todos los parámetros necesarios
				Integer id_producto = Integer.valueOf(request.getParameter("idProducto"));
				String nombre = request.getParameter("nombre");
				Integer categoria = Integer.valueOf(request.getParameter("categoria"));
				String descripcion = request.getParameter("descripcion");
				Double precio = Double.valueOf(request.getParameter("precio"));
				Integer formato = Integer.valueOf(request.getParameter("formato"));
				Integer stock = Integer.valueOf(request.getParameter("stock"));

				// Si en la descripción hay tabulaciones o
				// saltos de línea se reemplazan
				descripcion.replace("\n", "").replace("\t", "").replace("\r", "");
				
				//Seteamos el pojo Producto
				producto.setId(id_producto);
				producto.setNombre(nombre);
				producto.setId_categoria(categoria);
				producto.setDescripcion(descripcion);
				producto.setPrecio(precio);
				producto.setId_formato(formato);
				producto.setStock(stock);
				
				//Actualizamos el producto
				productoEJB.actualizarProducto(producto);
				
				//Recogemos todos los productos del vendedor
				productos = productoEJB.getProductosVendedor(vendedor.getId_vendedor());
				
				//Lo pasamos todo a la request
				request.setAttribute("productos", productos);
				request.setAttribute("vendedor", vendedor);
				request.setAttribute("categorias", categorias);
				request.setAttribute("formatos", formatos);
				
				//Redirigmos a PRODUCTOS_JSP
				rs.forward(request, response);

			} else {
				
				//Si no hay sesión redirigimos a Principal
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}
	}

}
