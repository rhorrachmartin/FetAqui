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
import modelo.ejb.DireccionEJB;
import modelo.ejb.LoggersEJB;
import modelo.ejb.PoblacionEJB;
import modelo.pojo.Cliente;
import modelo.pojo.Direccion;
import modelo.pojo.Poblacion;

/**
 * Controlador encargado de actualizar los datos de del perfil de un usuario
 * Cliente
 * 
 * @author ramon
 *
 */
@WebServlet("/ActualizarPerfilCliente")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ActualizarPerfilCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	ClienteEJB clienteEJB;

	@EJB
	DireccionEJB direccionEJB;

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

			// Recogemos el usario Cliente de la sesión
			Cliente cliente = (Cliente) session.getAttribute("cliente");

			// Si existe un usuario cliente en la sesión
			if (cliente.getNombre() != null) {

				// Recogemos todos los parámetros posibles a actualizar
				String apellido = request.getParameter("apellido");
				String nombre = request.getParameter("nombre");
				String telefono = request.getParameter("telefono");
				String passAntiguo = request.getParameter("passAntiguo");
				String direccion = request.getParameter("direccion");
				Integer id_poblacion = Integer.valueOf(request.getParameter("poblacion"));
				Direccion dir = new Direccion();
				ArrayList<Poblacion> poblaciones = null;

				// Si coincide el password introducido con el del usuario
				if (cliente.getPassword().equals(passAntiguo)) {

					// Actualizamos los datos del mismo
					clienteEJB.updateTelf(telefono, cliente.getId_cliente());
					clienteEJB.updateNombre(nombre, cliente.getId_cliente());
					clienteEJB.updateApellido(apellido, cliente.getId_cliente());

					// Si la dirección del cliente es null o si no coinciden ni la dirección ni la
					// población
					// recibidas en la request actualizamos dirección y población. Así prevenimos
					// direcciones duplicadas innecesariamente
					// en un mismo cliente.
					if (cliente.getDireccion() == null || !cliente.getDireccion().equals(direccion)
							|| cliente.getIdPoblacion() != id_poblacion) {

						// Seteamos la nueva Direccion
						dir.setDireccion(direccion);
						dir.setId_poblacion(id_poblacion);

						// Insertamos la misma en BD
						direccionEJB.insertarDireccion(dir);

						// Actualizamos la dirección
						clienteEJB.updateDireccion(cliente.getId_cliente());

						// El atriuto error de la sesión se refiere a si existe algún error relacionado
						// con la intención de realizar pedido
						// sin que el cliente tenga una dirección en su perfil, al actualizar la
						// dirección removemos este atributo de la sesión
						if (session.getAttribute("error") != null) {
							session.removeAttribute("error");
						}
					}

					// Recogemos el usuario actualizado
					Cliente clienteActualizado = clienteEJB.getCliente(cliente.getEmail(), cliente.getPassword());

					// Lo sustituimos en la sesión
					request.getSession().setAttribute("cliente", clienteActualizado);

					// REcogemos las poblaciones
					poblaciones = poblacionEJB.getPoblaciones();

					// Lo pasamos todo a la request
					request.setAttribute("poblaciones", poblaciones);
					request.setAttribute("cliente", clienteActualizado);

					// Redireccionamos a PERFIL_CLIENTE_JSP
					rs.forward(request, response);
				} else {
					// Si las contraseñas no coinciden mostramos un mensaje error
					String error = "Contraseña incorrecta";

					// Recogemos el usuario cliente de la sesión
					Cliente cliente2 = (Cliente) session.getAttribute("cliente");

					// REcogemos las poblaciones
					poblaciones = poblacionEJB.getPoblaciones();

					// Lo pasamos todo a la request
					request.setAttribute("poblaciones", poblaciones);
					request.setAttribute("cliente", cliente2);
					request.setAttribute("error", error);

					// Redireccionamos a PERFIL_CLIENTE_JSP
					rs.forward(request, response);
				}

			} else {
				
				//Si no hay usuario cliente en la sesión redirigimos a Principal
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

}
