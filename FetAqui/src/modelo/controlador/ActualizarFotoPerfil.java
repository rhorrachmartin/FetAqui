package modelo.controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ejb.ClienteEJB;
import modelo.ejb.ImagenesEJB;
import modelo.ejb.LoggersEJB;
import modelo.ejb.PoblacionEJB;
import modelo.pojo.Cliente;
import modelo.pojo.Poblacion;

/**
 * Clase controlador encargado de actualizar la foto de perfil de un usuario
 * Cliente
 * 
 * @author ramon
 *
 */
@WebServlet("/ActualizarFotoPerfil")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ActualizarFotoPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	ClienteEJB clienteEJB;

	@EJB
	PoblacionEJB poblacionEJB;

	@EJB
	LoggersEJB logger;

	@EJB
	ImagenesEJB imagenesEJB;

	static final String PERFIL_CLIENTE_JSP = "/PerfilCliente.jsp";
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	/**
	 * Método doPost encargado de actualizar la foto de perfil de un usuario Cliente
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Recogemos el contexto
		ServletContext contexto = getServletContext();

		// Creamos el RequestDispatcher por defecto hacia Registro.jsp
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PERFIL_CLIENTE_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			//Recogemos al usuario Cliente de la sesión
			Cliente cliente = (Cliente) session.getAttribute("cliente");
			
			//Recogemos el password introducido por el usuario
			String password = request.getParameter("password");
			ArrayList<Poblacion> poblaciones = null;
			
			//Si hemos encontrado usuario en la sesión
			if (cliente.getNombre() != null) {
				
				//Comparamos el password del usuario en sesión con el password introducido				
				if (cliente.getPassword().equals(password)) {
					
					//Recogemos el nombre de la foto y la guardamos en disco
					String foto = imagenesEJB.guardarImagen(request, contexto);
					
					//actualizamos en BD
					clienteEJB.updateFoto(foto, cliente.getId_cliente());
					
					//Recogemos el cliente actualizado
					Cliente clienteActualizado = clienteEJB.getCliente(cliente.getEmail(), cliente.getPassword());
					
					//Actualizamos la sesión del usuario Cliente
					request.getSession().setAttribute("cliente", clienteActualizado);
					
					//Recogemos las poblaciones de BD
					poblaciones = poblacionEJB.getPoblaciones();
					
					//Las pasamos a la request
					request.setAttribute("poblaciones", poblaciones);
					
					//Pasamos el cliente actualizado a la request
					request.setAttribute("cliente", clienteActualizado);
					
					//Redirigimos a PERFIL_CLIENTE_JSP
					rs.forward(request, response);

				} else {
					//Si no coinciden las contraseñas mostramos un mensaje de error
					String error = "Contraseña incorrecta";
					
					//Recogemos las poblaciones de BD
					poblaciones = poblacionEJB.getPoblaciones();
					
					//Las pasamos a la request
					request.setAttribute("poblaciones", poblaciones);
					
					//Pasamos el cliente a la request junto con el mensaje de error
					request.setAttribute("cliente", cliente);
					request.setAttribute("error", error);
					//Redirigimos a PERFIL_CLIENTE_JSP
					rs.forward(request, response);
				}

			} else {
				//Si no encontramos sesión redirigimos a Principal
				response.sendRedirect("Principal");
			}

		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}
	}

}
