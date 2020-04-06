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
import modelo.ejb.ImagenesEJB2;
import modelo.ejb.LoggersEJB;
import modelo.ejb.PoblacionEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Cliente;
import modelo.pojo.Poblacion;

/**
 * Servlet implementation class ActualizarPerfilCliente
 */
@WebServlet("/ActualizarFotoPerfil")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ActualizarFotoPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * EJB para trabajar con Usuarios
	 */
	@EJB
	VendedorEJB vendedorEJB;

	@EJB
	ClienteEJB clienteEJB;

	@EJB
	PoblacionEJB poblacionEJB;

	/**
	 * EJB para trabajar con los logger
	 */
	@EJB
	LoggersEJB logger;

	@EJB
	ImagenesEJB2 imagenesEJB;

	static final String PERFIL_CLIENTE_JSP = "/PerfilCliente.jsp";
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			if (session == null) {
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Recogemos el contexto
		ServletContext contexto = getServletContext();

		// Creamos el RequestDispatcher por defecto hacia Registro.jsp
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PERFIL_CLIENTE_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		Cliente cliente = (Cliente) session.getAttribute("cliente");
		Cliente clienteExiste = null;
		String password = request.getParameter("password");
		ArrayList<Poblacion> poblaciones = null;

		if (cliente.getNombre() != null) {
			clienteExiste = clienteEJB.getCliente(cliente.getEmail(), cliente.getPassword());

			if (clienteExiste.getPassword().equals(password)) {

				try {
					String foto = imagenesEJB.guardarImagen(request, contexto);
					clienteEJB.updateFoto(foto, clienteExiste.getId_cliente());

					Cliente clienteActualizado = clienteEJB.getCliente(cliente.getEmail(), cliente.getPassword());
					request.getSession().setAttribute("cliente", clienteActualizado);
					poblaciones = poblacionEJB.getPoblaciones();
					request.setAttribute("poblaciones", poblaciones);
					request.setAttribute("cliente", clienteActualizado);
					rs.forward(request, response);
				} catch (Exception e) {
					e.getMessage();
				}

			} else {
				try {
					String error = "Contraseña incorrecta";
					Cliente cliente3 = (Cliente) session.getAttribute("cliente");
					poblaciones = poblacionEJB.getPoblaciones();
					request.setAttribute("poblaciones", poblaciones);
					request.setAttribute("cliente", cliente3);
					request.setAttribute("error", error);
					rs.forward(request, response);
				} catch (Exception e) {
					e.getMessage();
				}
			}

		} else {
			try {
				response.sendRedirect("Principal");
			} catch (Exception e) {
				e.getMessage();
			}
		}

	}

}
