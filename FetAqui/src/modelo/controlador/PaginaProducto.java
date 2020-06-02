package modelo.controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ejb.LoggersEJB;
import modelo.ejb.ProductoEJB;
import modelo.ejb.SesionClienteEJB;
import modelo.ejb.SesionVendedorEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Cliente;
import modelo.pojo.Producto;
import modelo.pojo.Vendedor;

/**
 * Clase controlador encargado de obtener la ficha de un producto
 * 
 * @author ramon
 *
 */
@WebServlet("/PaginaProducto")
public class PaginaProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	VendedorEJB vendedorEJB;

	@EJB
	ProductoEJB productoEJB;

	@EJB
	SesionVendedorEJB sesionVendedorEJB;

	@EJB
	SesionClienteEJB sesionClienteEJB;

	@EJB
	LoggersEJB logger;

	static final String PRODUCTO_NO_LOGEADO_JSP = "/ProductoNoLogeado.jsp";
	static final String PRODUCTO_LOGEADO_VENDEDOR_JSP = "/ProductoLogeadoVendedor.jsp";
	static final String PRODUCTO_LOGEADO_CLIENTE_JSP = "/ProductoLogeadoCliente.jsp";
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	
	/**
	 * Método doGet encargado de cargar la página de un producto en concreto en
	 * función de que tipo de usuario lo visualiza
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PRODUCTO_NO_LOGEADO_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			// Intentamos obtener el usuario de la sesión
			Vendedor v = sesionVendedorEJB.vendedorLogeado(session);
			Cliente c = sesionClienteEJB.clienteLogeado(session);

			// Si existe algún usuario en la sesión
			if (v != null || c != null) {

				// Si es un usuario cliente
				if (c != null) {

					// Lo metemos en la request
					request.setAttribute("c", c);

					// RS hacia PRODUCTO_LOGEADO_CLIENTE_JSP
					rs = getServletContext().getRequestDispatcher(PRODUCTO_LOGEADO_CLIENTE_JSP);

					// Recogemos los parámetros necesarios
					Integer id_producto = Integer.valueOf(request.getParameter("id_producto"));
					Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));

					// Recogemos el producto y al vendedor del mismo
					Producto producto = productoEJB.getProductoPorId(id_producto);
					Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);

					// Los metemos en la request
					request.setAttribute("vendedor", vendedor);
					request.setAttribute("producto", producto);

					// Redirigimos a PRODUCTO_NO_LOGEADO_JSP
					rs.forward(request, response);

				} else {
					// Si no es cliente es vendedor, lo metemos en la request
					request.setAttribute("v", v);

					// RS hacia PRODUCTO_LOGEADO_VENDEDOR_JSP
					rs = getServletContext().getRequestDispatcher(PRODUCTO_LOGEADO_VENDEDOR_JSP);

					// Recogemos los parámetros necesarios
					Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));
					Integer id_producto = Integer.valueOf(request.getParameter("id_producto"));

					// Recogemos el producto y al vendedor del mismo
					Producto producto = productoEJB.getProductoPorId(id_producto);
					Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);

					// LO metemos todo en la request
					request.setAttribute("vendedor", vendedor);
					request.setAttribute("producto", producto);

					// RS hacia PRODUCTO_LOGEADO_VENDEDOR_JSP
					rs.forward(request, response);
				}

			} else {
				// Si no hay sesión ni usuario RS hacia PRODUCTO_NO_LOGEADO_JSP
				rs = getServletContext().getRequestDispatcher(PRODUCTO_NO_LOGEADO_JSP);

				// Recogemos los parámetros necesarios
				Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));
				Integer id_producto = Integer.valueOf(request.getParameter("id_producto"));

				// Recogemos el producto y al vendedor del mismo
				Producto producto = productoEJB.getProductoPorId(id_producto);
				Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);

				// Lo metemos todo en la request
				request.setAttribute("vendedor", vendedor);
				request.setAttribute("producto", producto);

				// REdirigimos hacia PRODUCTO_NO_LOGEADO_JSP
				rs.forward(request, response);
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}
	}
	
	/**
	 * Método doPost encargado de cargar la página de un producto en concreto en
	 * función de que tipo de usuario lo visualiza
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PRODUCTO_NO_LOGEADO_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			// Intentamos obtener el usuario de la sesión
			Vendedor v = sesionVendedorEJB.vendedorLogeado(session);
			Cliente c = sesionClienteEJB.clienteLogeado(session);

			// Si existe algún usuario en la sesión
			if (v != null || c != null) {

				// Si es un usuario cliente
				if (c != null) {

					// Lo metemos en la request
					request.setAttribute("c", c);

					// RS hacia PRODUCTO_LOGEADO_CLIENTE_JSP
					rs = getServletContext().getRequestDispatcher(PRODUCTO_LOGEADO_CLIENTE_JSP);

					// Recogemos los parámetros necesarios
					Integer id_producto = Integer.valueOf(request.getParameter("id_producto"));
					Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));

					// Recogemos el producto y al vendedor del mismo
					Producto producto = productoEJB.getProductoPorId(id_producto);
					Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);

					// Los metemos en la request
					request.setAttribute("vendedor", vendedor);
					request.setAttribute("producto", producto);

					// Redirigimos a PRODUCTO_NO_LOGEADO_JSP
					rs.forward(request, response);

				} else {
					// Si no es cliente es vendedor, lo metemos en la request
					request.setAttribute("v", v);

					// RS hacia PRODUCTO_LOGEADO_VENDEDOR_JSP
					rs = getServletContext().getRequestDispatcher(PRODUCTO_LOGEADO_VENDEDOR_JSP);

					// Recogemos los parámetros necesarios
					Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));
					Integer id_producto = Integer.valueOf(request.getParameter("id_producto"));

					// Recogemos el producto y al vendedor del mismo
					Producto producto = productoEJB.getProductoPorId(id_producto);
					Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);

					// LO metemos todo en la request
					request.setAttribute("vendedor", vendedor);
					request.setAttribute("producto", producto);

					// RS hacia PRODUCTO_LOGEADO_VENDEDOR_JSP
					rs.forward(request, response);
				}

			} else {
				// Si no hay sesión ni usuario RS hacia PRODUCTO_NO_LOGEADO_JSP
				rs = getServletContext().getRequestDispatcher(PRODUCTO_NO_LOGEADO_JSP);

				// Recogemos los parámetros necesarios
				Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));
				Integer id_producto = Integer.valueOf(request.getParameter("id_producto"));

				// Recogemos el producto y al vendedor del mismo
				Producto producto = productoEJB.getProductoPorId(id_producto);
				Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);

				// Lo metemos todo en la request
				request.setAttribute("vendedor", vendedor);
				request.setAttribute("producto", producto);

				// REdirigimos hacia PRODUCTO_NO_LOGEADO_JSP
				rs.forward(request, response);
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}
	}

}
