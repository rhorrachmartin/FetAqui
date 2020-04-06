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
import modelo.ejb.ImagenesEJB2;
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
	ImagenesEJB2 imagenesEJB;

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



}
