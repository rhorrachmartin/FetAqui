package modelo.controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ejb.CategoriaEJB;
import modelo.ejb.FormatoEJB;
import modelo.ejb.ImagenesEJB;
import modelo.ejb.LoggersEJB;
import modelo.ejb.PoblacionEJB;
import modelo.ejb.ProductoEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Categoria;
import modelo.pojo.Formato;
import modelo.pojo.Producto;
import modelo.pojo.Vendedor;

/**
 * Servlet implementation class AñadirProducto
 */
@WebServlet("/ObtenerProductosVendedor")
public class ObtenerProductosVendedor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * EJB para trabajar con Usuarios
	 */
	@EJB
	VendedorEJB vendedorEJB;

	@EJB
	PoblacionEJB poblacionEJB;

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

		Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");

		if (session != null && vendedor.getNombre() != null) {
			ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
			ArrayList<Formato> formatos = formatoEJB.getFormatos();
			ArrayList<Producto> productos = productoEJB.getProductosVendedor(vendedor.getId_vendedor());
			
			request.setAttribute("productos", productos);
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

		// Recogemos el contexto
		ServletContext contexto = getServletContext();

		response.setContentType(CONTENT_TYPE);
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");
		Producto producto = new Producto();
		if (session != null && vendedor.getNombre() != null) {

			ArrayList<Categoria> categorias = categoriaEJB.getCategorias();
			ArrayList<Formato> formatos = formatoEJB.getFormatos();

			String nombre = request.getParameter("nombre");
			Integer categoria = Integer.valueOf(request.getParameter("categoria"));
			String descripcion = request.getParameter("descripcion");
			Double precio = Double.valueOf(request.getParameter("precio"));
			Integer formato = Integer.valueOf(request.getParameter("formato"));
			System.out.println(formato);
			Integer stock = Integer.valueOf(request.getParameter("stock"));
			String foto = imagenesEJB.guardarImagen(request, contexto);
			
			if(foto == null) {
				foto = "desconocido.txt";
			}
			
			boolean estadoVentaOnline = "on".equals(request.getParameter("ventaOnline")) ? true : false;
			System.out.println(estadoVentaOnline);
			Integer ventaOnline = 1;
			Integer vendedorProducto = vendedor.getId_vendedor();
			
			if(!estadoVentaOnline) {
				ventaOnline = 0;
			}
			
			producto.setNombre(nombre);
			producto.setId_categoria(categoria);
			producto.setDescripcion(descripcion);
			producto.setPrecio(precio);
			producto.setId_formato(formato);
			producto.setStock(stock);
			producto.setFoto(foto);
			producto.setVenta_online(ventaOnline);
			producto.setId_vendedor(vendedorProducto);
			
			productoEJB.insertarProducto(producto);
			
			request.setAttribute("vendedor", vendedor);
			request.setAttribute("categorias", categorias);
			request.setAttribute("formatos", formatos);

			rs.forward(request, response);

		} else {
			response.sendRedirect("Principal");
		}
	}

}
