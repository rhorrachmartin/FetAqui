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

	/**
	 * Método doPost encargado de activar y desactivar las ventas online de todos
	 * los productos
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Creamos el RequestDispatcher por defecto hacia ADMINISTRAR_PAGINA_VENDEDOR_JSP
		RequestDispatcher rs = getServletContext().getRequestDispatcher(ADMINISTRAR_PAGINA_VENDEDOR_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			// Intentamos recoger al usuario vendedor de la sesión
			Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");

			// Si existe el usuario vendedor
			if (vendedor.getNombre() != null) {

				// Pojo vendedor donde recogeremos los datos del vendedor actualizado
				Vendedor vendedorActualizado = null;

				// arraylist a rellenar de poblaciones
				ArrayList<Poblacion> poblaciones = null;

				// Si la venta online del vendedor está desactivada
				if (vendedor.getVenta_online() == 0) {

					// La activamos
					vendedorEJB.activarVentaOnline(vendedor.getId_vendedor());

					// Recogemos el vendedor actualizado
					vendedorActualizado = vendedorEJB.getVendedor(vendedor.getEmail(), vendedor.getPassword());

					// Lo sustituimos en la sesión
					request.getSession().setAttribute("vendedor", vendedorActualizado);

					// Recogemos las poblaciones
					poblaciones = poblacionEJB.getPoblaciones();

					// Las metemos en la request
					request.setAttribute("poblaciones", poblaciones);

					// Redirigimos a ADMINISTRAR_PAGINA_VENDEDOR_JSP
					rs.forward(request, response);

				} else {

					// Si está activada la desactivamos
					vendedorEJB.desactivarVentaOnline(vendedor.getId_vendedor());

					// Recogemos el vendedor actualizado
					vendedorActualizado = vendedorEJB.getVendedor(vendedor.getEmail(), vendedor.getPassword());

					// Recogemos las poblaciones
					poblaciones = poblacionEJB.getPoblaciones();

					// Las metemos en la request
					request.setAttribute("poblaciones", poblaciones);

					// Sustituimos al usuario vendedor de la sesión
					request.getSession().setAttribute("vendedor", vendedorActualizado);

					// Redirigimos a ADMINISTRAR_PAGINA_VENDEDOR_JSP
					rs.forward(request, response);
				}

			} else {
				// Si no hay sesión redirigimos a Principal
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

}
