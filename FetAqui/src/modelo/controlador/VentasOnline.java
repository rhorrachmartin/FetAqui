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

import modelo.ejb.LoggersEJB;
import modelo.ejb.PoblacionEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Poblacion;
import modelo.pojo.Vendedor;

/**
 * Clase encargada de activar y desactivar las ventas online de todos los
 * productos de un Vendedor
 * 
 * @author ramon
 *
 */
@WebServlet("/VentasOnline")
public class VentasOnline extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	VendedorEJB vendedorEJB;

	@EJB
	PoblacionEJB poblacionEJB;

	@EJB
	LoggersEJB logger;

	static final String ADMINISTRAR_PAGINA_VENDEDOR_JSP = "/AdministrarPaginaVendedor.jsp";
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			if (session == null && session.getAttribute("vendedor") == null
					&& session.getAttribute("cliente") == null) {
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Creamos el RequestDispatcher por defecto hacia Registro.jsp
		RequestDispatcher rs = getServletContext().getRequestDispatcher(ADMINISTRAR_PAGINA_VENDEDOR_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {

			Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");

			Vendedor vendedorActualizado = null;

			ArrayList<Poblacion> poblaciones = null;

			if (vendedor.getNombre() != null) {

				if (vendedor.getVenta_online() == 0) {

					vendedorEJB.activarVentaOnline(vendedor.getId_vendedor());

					vendedorActualizado = vendedorEJB.getVendedor(vendedor.getEmail(), vendedor.getPassword());

					request.getSession().setAttribute("vendedor", vendedorActualizado);

					poblaciones = poblacionEJB.getPoblaciones();

					request.setAttribute("poblaciones", poblaciones);

					request.getSession().setAttribute("vendedor", vendedorActualizado);

					rs.forward(request, response);

				} else {
					vendedorEJB.desactivarVentaOnline(vendedor.getId_vendedor());

					vendedorActualizado = vendedorEJB.getVendedor(vendedor.getEmail(), vendedor.getPassword());

					poblaciones = poblacionEJB.getPoblaciones();

					request.setAttribute("poblaciones", poblaciones);

					request.getSession().setAttribute("vendedor", vendedorActualizado);

					rs.forward(request, response);
				}

			} else {
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

}
