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
@WebServlet("/PaginaProducto")
public class PaginaProducto extends HttpServlet {
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

	static final String PRODUCTO_NO_LOGEADO_JSP = "/ProductoNoLogeado.jsp";
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PRODUCTO_NO_LOGEADO_JSP);

		response.setContentType(CONTENT_TYPE);

		try {
			Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));
			Integer id_producto = Integer.valueOf(request.getParameter("id_producto"));
			
			Producto producto = productoEJB.getProductoPorId(id_producto);
			Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);
			
			request.setAttribute("vendedor", vendedor);
			request.setAttribute("producto", producto);
			
			rs.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
