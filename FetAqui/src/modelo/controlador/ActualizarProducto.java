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
 * Servlet implementation class AñadirProducto
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
	/**
	 * EJB para trabajar con los logger
	 */
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

		Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");

		if (session != null && vendedor.getNombre() != null) {
			ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
			ArrayList<Formato> formatos = formatoEJB.getFormatos();

			request.setAttribute("vendedor", vendedor);
			request.setAttribute("categorias", categorias);
			request.setAttribute("formatos", formatos);

			rs.forward(request, response);

		} else {
			response.sendRedirect("Principal");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PRODUCTOS_JSP);

		response.setContentType(CONTENT_TYPE);
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");
		Producto producto = new Producto();
		ArrayList<Producto> productos = null;
		try {
			if (session != null && vendedor.getNombre() != null) {

				ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
				ArrayList<Formato> formatos = formatoEJB.getFormatos();

				Integer id_producto = Integer.valueOf(request.getParameter("idProducto"));
				String nombre = request.getParameter("nombre");
				Integer categoria = Integer.valueOf(request.getParameter("categoria"));
				String descripcion = request.getParameter("descripcion");

				// Si en la descripción hay tabulaciones o
				// saltos de línea se reemplazan
				descripcion.replace("\n", "").replace("\t", "").replace("\r", "");

				Double precio = Double.valueOf(request.getParameter("precio"));
				Integer formato = Integer.valueOf(request.getParameter("formato"));
				Integer stock = Integer.valueOf(request.getParameter("stock"));

				producto.setId(id_producto);
				producto.setNombre(nombre);
				producto.setId_categoria(categoria);
				producto.setDescripcion(descripcion);
				producto.setPrecio(precio);
				producto.setId_formato(formato);
				producto.setStock(stock);

				productoEJB.actualizarProducto(producto);
				productos = productoEJB.getProductosVendedor(vendedor.getId_vendedor());
				
				request.setAttribute("productos", productos);
				request.setAttribute("vendedor", vendedor);
				request.setAttribute("categorias", categorias);
				request.setAttribute("formatos", formatos);

				rs.forward(request, response);

			} else {
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
