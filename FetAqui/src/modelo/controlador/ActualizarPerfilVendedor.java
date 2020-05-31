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

import modelo.ejb.DireccionEJB;
import modelo.ejb.LoggersEJB;
import modelo.ejb.PoblacionEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Direccion;
import modelo.pojo.Poblacion;
import modelo.pojo.Vendedor;

/**
 * Controlador encargado de actualizar el perfil de un Vendedor
 * 
 * @author ramon
 *
 */
@WebServlet("/ActualizarPerfilVendedor")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ActualizarPerfilVendedor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	VendedorEJB vendedorEJB;

	@EJB
	DireccionEJB direccionEJB;

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
			// Recogemos el usario Vendedor de la sesión
			Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");

			// Si existe un usuario Vendedor en la sesión
			if (vendedor.getNombre() != null) {

				// Recogemos todos los posbiles parámetros a actualizar
				String nif = request.getParameter("nif");
				String nombre = request.getParameter("nombre");
				String telefono = request.getParameter("telefono");
				String passAntiguo = request.getParameter("passAntiguo");
				String direccion = request.getParameter("direccion");
				Integer id_poblacion = Integer.valueOf(request.getParameter("poblacion"));

				Direccion dir = new Direccion();
				ArrayList<Poblacion> poblaciones = null;

				// Si la contraseña del usuario coincide con la introducida
				if (vendedor.getPassword().equals(passAntiguo)) {

					// Actualizamos los parámetros
					vendedorEJB.updateTelefono(telefono, vendedor.getId_vendedor());
					vendedorEJB.updateNombre(nombre, vendedor.getId_vendedor());
					vendedorEJB.updateNif(nif, vendedor.getId_vendedor());

					// Si la dirección del vendedor es null o si no coinciden ni la dirección ni la
					// población
					// recibidas en la request actualizamos dirección y población. Así prevenimos
					// direcciones duplicadas innecesariamente
					// en un mismo vendedor.
					if (vendedor.getDireccion() == null || vendedor.getPoblacion() == null
							|| !vendedor.getDireccion().equals(direccion)
							|| vendedor.getIdPoblacion() != id_poblacion) {

						// Seteamos la nueva Direccion
						dir.setDireccion(direccion);
						dir.setId_poblacion(id_poblacion);

						// Insertamos la misma en BD
						direccionEJB.insertarDireccion(dir);

						// Actualizamos la dirección
						vendedorEJB.updateDireccion(vendedor.getId_vendedor());
					}

					// Recogemos el vendedor actualizado
					Vendedor vendedorActualizado = vendedorEJB.getVendedor(vendedor.getEmail(), vendedor.getPassword());

					// Lo sustituimos en la sesión
					request.getSession().setAttribute("vendedor", vendedorActualizado);

					// REcogemos las poblaciones
					poblaciones = poblacionEJB.getPoblaciones();

					// Lo pasamos todo a la request
					request.setAttribute("poblaciones", poblaciones);
					request.setAttribute("vendedor", vendedorActualizado);

					// REdirigimos a PERFIL_VENDEDOR_JSP
					rs.forward(request, response);

				} else {
					// Si las contraseñas no coinciden
					String error = "Contraseña incorrecta";

					// REcogemos el usuario vendedor de la sesión
					Vendedor vendedor2 = (Vendedor) session.getAttribute("vendedor");

					// Recogemos las poblaciones
					poblaciones = poblacionEJB.getPoblaciones();

					// Lo pasamos todo a la request
					request.setAttribute("poblaciones", poblaciones);
					request.setAttribute("vendedor", vendedor2);
					request.setAttribute("error", error);

					// Redireccionamos a PERFIL_VENDEDOR_JSP
					rs.forward(request, response);
				}

			} else {
				// Si no hay usuario vendedor en la sesión redirigimos a Principal
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

}
