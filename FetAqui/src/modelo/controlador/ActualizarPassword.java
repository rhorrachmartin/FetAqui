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
 * Servlet implementation class ActualizarPerfilCliente
 */
@WebServlet("/ActualizarPassword")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ActualizarPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * EJB para trabajar con Usuarios
	 */

	@EJB
	ClienteEJB clienteEJB;

	@EJB
	PoblacionEJB poblacionEJB;

	/**
	 * EJB para trabajar con los logger
	 */
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

		Cliente cliente = (Cliente) session.getAttribute("cliente");
		Cliente clienteExiste = null;
		String passAntiguo = request.getParameter("passAntiguo");
		String passNuevo1 = request.getParameter("passNuevo1");
		String passNuevo2 = request.getParameter("passNuevo2");
		ArrayList<Poblacion> poblaciones = null;
		
		try {

			if (cliente.getNombre() != null) {
				
				clienteExiste = clienteEJB.getCliente(cliente.getEmail(), cliente.getPassword());

				if (clienteExiste.getPassword().equals(passAntiguo) && passNuevo1.equals(passNuevo2)) {

					clienteEJB.updatePassword(passNuevo2, clienteExiste.getId_cliente());

					Cliente clienteActualizado = clienteEJB.getCliente(cliente.getEmail(), passNuevo2);

					request.getSession().setAttribute("cliente", clienteActualizado);

					poblaciones = poblacionEJB.getPoblaciones();

					request.setAttribute("poblaciones", poblaciones);

					request.setAttribute("cliente", clienteActualizado);

					rs.forward(request, response);
				} else {
					String error = "Las contraseñas no coinciden";
					Cliente cliente3 = (Cliente) session.getAttribute("cliente");
					poblaciones = poblacionEJB.getPoblaciones();
					request.setAttribute("poblaciones", poblaciones);
					request.setAttribute("cliente", cliente3);
					request.setAttribute("error", error);
					rs.forward(request, response);
				}

			} else {
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			e.getMessage();
		}

	}

}
