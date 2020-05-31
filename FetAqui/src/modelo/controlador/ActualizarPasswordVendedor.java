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

import modelo.ejb.LoggersEJB;
import modelo.ejb.PoblacionEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Poblacion;
import modelo.pojo.Vendedor;

/**
 * Clase controlador encargado de actualizar el password de un Vendedor
 * 
 * @author ramon
 *
 */
@WebServlet("/ActualizarPasswordVendedor")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ActualizarPasswordVendedor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	VendedorEJB vendedorEJB;

	@EJB
	PoblacionEJB poblacionEJB;

	@EJB
	LoggersEJB logger;

	static final String PERFIL_VENDEDOR_JSP = "/PerfilVendedor.jsp";
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Creamos el RequestDispatcher por defecto hacia Registro.jsp
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PERFIL_VENDEDOR_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {

			// Recogemos el usuario Vendedor de la sesión
			Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");

			// Recogemos el password antiguo y dos veces el nuevo
			String passAntiguo = request.getParameter("passAntiguo");
			String passNuevo1 = request.getParameter("passNuevo1");
			String passNuevo2 = request.getParameter("passNuevo2");

			// Si existe un usuario Vendedor en la sesión
			if (vendedor.getNombre() != null) {

				ArrayList<Poblacion> poblaciones = null;

				// Si el password del Vendedor coincide con el introducido y los dos passwords
				// nuevos son iguales
				if (vendedor.getPassword().equals(passAntiguo) && passNuevo1.equals(passNuevo2)) {

					// Actualizamos el password del vendedor
					vendedorEJB.updatePassword(passNuevo2, vendedor.getId_vendedor());

					// Recogemos el vendedor actualizado
					Vendedor vendedorActualizado = vendedorEJB.getVendedor(vendedor.getEmail(), passNuevo2);

					// Sustituimos el usuario vendedor de la sesión
					request.getSession().setAttribute("vendedor", vendedorActualizado);

					// Recogemos las poblaciones
					poblaciones = poblacionEJB.getPoblaciones();

					// Lo pasamos todo a la request
					request.setAttribute("poblaciones", poblaciones);
					request.setAttribute("vendedor", vendedorActualizado);

					// Redirigimos a PERFIL_VENDEDOR_JSP
					rs.forward(request, response);
				} else {
					// Si no coinciden las contraseñas mostramos un mensaje de error
					String error = "Las contraseñas no coinciden";

					// Recogemos las poblaciones
					poblaciones = poblacionEJB.getPoblaciones();

					// Lo pasamos todo a la request
					request.setAttribute("poblaciones", poblaciones);
					request.setAttribute("vendedor", vendedor);
					request.setAttribute("error", error);

					// Redirigimos a PERFIL_VENDEDOR_JSP
					rs.forward(request, response);
				}

			} else {

				// Si no hay usuario en la sesión redirigimos a Principal
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

}
