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
import modelo.ejb.ValoracionProductoEJB;
import modelo.pojo.Categoria;
import modelo.pojo.Formato;
import modelo.pojo.Producto;
import modelo.pojo.ValoracionProducto;
import modelo.pojo.Vendedor;

/**
 * Controlador encargado de añadir un producto de un Vendedor
 * 
 * @author ramon
 *
 */
@WebServlet("/AñadirProducto")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class AñadirProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	CategoriaEJB categoriaEJB;

	@EJB
	FormatoEJB formatoEJB;

	@EJB
	ProductoEJB productoEJB;

	@EJB
	ValoracionProductoEJB valoracionProductoEJB;

	@EJB
	LoggersEJB logger;

	@EJB
	ImagenesEJB imagenesEJB;

	static final String AÑADIR_PRODUCTOS_JSP = "/AñadirProductos.jsp";
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(AÑADIR_PRODUCTOS_JSP);

		response.setContentType(CONTENT_TYPE);
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			
			//Recogemos el usuario vendedor de la sesión
			Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");
			
			//Si existe la sesión y el vendedor
			if (session != null && vendedor.getNombre() != null) {
				
				//Recogemos todas las categorías y formatos
				ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
				ArrayList<Formato> formatos = formatoEJB.getFormatos();
				
				//Lo pasamos todo a la request
				request.setAttribute("vendedor", vendedor);
				request.setAttribute("categorias", categorias);
				request.setAttribute("formatos", formatos);
				
				//Redirigimos a AÑADIR_PRODUCTOS_JSP
				rs.forward(request, response);

			} else {
				//Si no hay sesión redirigimos a Principal
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(AÑADIR_PRODUCTOS_JSP);

		// Recogemos el contexto
		ServletContext contexto = getServletContext();

		response.setContentType(CONTENT_TYPE);
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			
			//Recogemos el usuario vendedor de la sesión
			Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");
			
			//Si la sesión existe y el usuario vendedor existe
			if (session != null && vendedor.getNombre() != null) {
				
				//Creamos instancias de Producto y valoracionProducto
				Producto producto = new Producto();
				ValoracionProducto valoracionProducto = new ValoracionProducto();
				
				//Recogemos todas las categorias y formatos
				ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
				ArrayList<Formato> formatos = formatoEJB.getFormatos();
				
				//Recogemos todos los parámetros necesarios
				String nombre = request.getParameter("nombre");
				Integer categoria = Integer.valueOf(request.getParameter("categoria"));
				String descripcion = request.getParameter("descripcion");
				Double precio = Double.valueOf(request.getParameter("precio"));
				Integer formato = Integer.valueOf(request.getParameter("formato"));
				Integer stock = Integer.valueOf(request.getParameter("stock"));
				String foto = imagenesEJB.guardarImagen(request, contexto);

				// Si en la descripción hay tabulaciones o
				// saltos de línea se reemplazan
				descripcion.replace("\n", "").replace("\t", "").replace("\r", "");

				
				//Comprobamos si el parámetro ventaOnline es "on", si es ON = TRUE si no es FALSE
				boolean estadoVentaOnline = "on".equals(request.getParameter("ventaOnline")) ? true : false;
				
				//El atributo ventaOnline por defecto a 1
				Integer ventaOnline = 1;
				
				//REcogemos el id del vendedor
				Integer vendedorProducto = vendedor.getId_vendedor();
				
				//Si estadoVentaOnline es false ventaOnline pasa a 0
				if (!estadoVentaOnline) {
					ventaOnline = 0;
				}
				
				//Seteamos el pojo producto
				producto.setNombre(nombre);
				producto.setId_categoria(categoria);
				producto.setDescripcion(descripcion);
				producto.setPrecio(precio);
				producto.setId_formato(formato);
				producto.setStock(stock);
				producto.setFoto(foto);
				producto.setVenta_online(ventaOnline);
				producto.setId_vendedor(vendedorProducto);
				
				//Insertamos el producto
				productoEJB.insertarProducto(producto);
				
				//Cualquier producto por defecto tiene una valoración de 5
				Integer valoracion = 5;
				
				//Las valoraciones iniciales de un producto están asociadas al cliente que crea por defecto la BD
				//cuyo id es el 1, procedemos a setear la valoración inicial de un producto
				valoracionProducto.setCliente(1);
				valoracionProducto.setProducto(producto.getId());
				valoracionProducto.setValoracion(valoracion);
				
				//Insertamos la valoracion del producto
				valoracionProductoEJB.insertarValoracionProducto(valoracionProducto);
				
				//Lo pasamos todo a la request
				request.setAttribute("vendedor", vendedor);
				request.setAttribute("categorias", categorias);
				request.setAttribute("formatos", formatos);
				
				//Redirigimos a AÑADIR_PRODUCTOS_JSP
				rs.forward(request, response);

			} else {
				
				//Redirigimos a Principal
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}
	}

}
