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

import modelo.ejb.ClienteEJB;
import modelo.ejb.LoggersEJB;
import modelo.ejb.PoblacionEJB;
import modelo.pojo.Cliente;
import modelo.pojo.Poblacion;

/**
 * Clase controlador encargado de actualizar el password de un usuario Cliente
 * 
 * @author ramon
 *
 */
@WebServlet("/ActualizarPassword")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ActualizarPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	ClienteEJB clienteEJB;

	@EJB
	PoblacionEJB poblacionEJB;

	@EJB
	LoggersEJB logger;

	static final String PERFIL_CLIENTE_JSP = "/PerfilCliente.jsp";
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Creamos el RequestDispatcher por defecto hacia Registro.jsp
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PERFIL_CLIENTE_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {

			// Recogemos el usuario cliente de la sesión
			Cliente cliente = (Cliente) session.getAttribute("cliente");

			// Recogemos el password antiguo y dos veces el nuevo
			String passAntiguo = request.getParameter("passAntiguo");
			String passNuevo1 = request.getParameter("passNuevo1");
			String passNuevo2 = request.getParameter("passNuevo2");

			// Si existe un usuario Cliente en la sesión
			if (cliente.getNombre() != null) {

				ArrayList<Poblacion> poblaciones = null;

				// Si el password del cliente coincide con el introducido y los dos passwords
				// nuevos son iguales
				if (cliente.getPassword().equals(passAntiguo) && passNuevo1.equals(passNuevo2)) {

					// Actualizamos el password del cliente
					clienteEJB.updatePassword(passNuevo2, cliente.getId_cliente());

					// Recogemos el cliente acutalizado
					Cliente clienteActualizado = clienteEJB.getCliente(cliente.getEmail(), passNuevo2);

					// Sustituimos el usuario cliente de la sesión
					request.getSession().setAttribute("cliente", clienteActualizado);

					// Rellenamos el ArrayList de Poblacion
					poblaciones = poblacionEJB.getPoblaciones();

					// Lo pasamos todo a a request
					request.setAttribute("poblaciones", poblaciones);
					request.setAttribute("cliente", clienteActualizado);

					// Redirigimos a PERFIL_CLIENTE_JSP
					rs.forward(request, response);
				} else {

					// Si no coinciden mostramos un mensaje de error
					String error = "Las contraseñas no coinciden";

					// Recogemos las poblaciones
					poblaciones = poblacionEJB.getPoblaciones();

					// Lo pasamos todo a la request
					request.setAttribute("poblaciones", poblaciones);
					request.setAttribute("cliente", cliente);
					request.setAttribute("error", error);

					// Redirigimos a PERFIL_CLIENTE_JSP
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
