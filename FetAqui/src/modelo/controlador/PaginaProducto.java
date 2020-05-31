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

			request.setAttribute("v", v);
			request.setAttribute("c", c);

			if (v != null || c != null) {

				if (c != null) {
					rs = getServletContext().getRequestDispatcher(PRODUCTO_LOGEADO_CLIENTE_JSP);

					try {

						Integer id_producto = Integer.valueOf(request.getParameter("id_producto"));
						Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));

						Producto producto = productoEJB.getProductoPorId(id_producto);
						Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);

						request.setAttribute("vendedor", vendedor);
						request.setAttribute("producto", producto);

						rs.forward(request, response);

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					rs = getServletContext().getRequestDispatcher(PRODUCTO_LOGEADO_VENDEDOR_JSP);
					try {
						Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));
						Integer id_producto = Integer.valueOf(request.getParameter("id_producto"));

						Producto producto = productoEJB.getProductoPorId(id_producto);
						Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);

						request.setAttribute("vendedor", vendedor);
						request.setAttribute("producto", producto);

						rs.forward(request, response);

					} catch (Exception e) {
						logger.setErrorLogger(e.getMessage());
					}
				}

			} else {
				rs = getServletContext().getRequestDispatcher(PRODUCTO_NO_LOGEADO_JSP);

				try {
					Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));
					Integer id_producto = Integer.valueOf(request.getParameter("id_producto"));

					Producto producto = productoEJB.getProductoPorId(id_producto);
					Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);

					request.setAttribute("vendedor", vendedor);
					request.setAttribute("producto", producto);

					rs.forward(request, response);

				} catch (Exception e) {
					logger.setErrorLogger(e.getMessage());
				}
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}
	}

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

			request.setAttribute("v", v);
			request.setAttribute("c", c);

			if (v != null || c != null) {

				if (c != null) {
					rs = getServletContext().getRequestDispatcher(PRODUCTO_LOGEADO_CLIENTE_JSP);

					try {
						Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));
						Integer id_producto = Integer.valueOf(request.getParameter("id_producto"));

						Producto producto = productoEJB.getProductoPorId(id_producto);
						Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);

						request.setAttribute("vendedor", vendedor);
						request.setAttribute("producto", producto);

						rs.forward(request, response);

					} catch (Exception e) {
						logger.setErrorLogger(e.getMessage());
					}

				} else {
					rs = getServletContext().getRequestDispatcher(PRODUCTO_LOGEADO_VENDEDOR_JSP);
					try {
						Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));
						Integer id_producto = Integer.valueOf(request.getParameter("id_producto"));

						Producto producto = productoEJB.getProductoPorId(id_producto);
						Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);

						request.setAttribute("vendedor", vendedor);
						request.setAttribute("producto", producto);

						rs.forward(request, response);

					} catch (Exception e) {
						logger.setErrorLogger(e.getMessage());
					}
				}

			} else {
				rs = getServletContext().getRequestDispatcher(PRODUCTO_NO_LOGEADO_JSP);

				try {
					Integer id_vendedor = Integer.valueOf(request.getParameter("id_vendedor"));
					Integer id_producto = Integer.valueOf(request.getParameter("id_producto"));

					Producto producto = productoEJB.getProductoPorId(id_producto);
					Vendedor vendedor = vendedorEJB.getVendedorPorId(id_vendedor);

					request.setAttribute("vendedor", vendedor);
					request.setAttribute("producto", producto);

					rs.forward(request, response);

				} catch (Exception e) {
					logger.setErrorLogger(e.getMessage());
				}
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}
	}

}
