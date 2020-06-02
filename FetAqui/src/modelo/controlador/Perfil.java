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
import modelo.pojo.Cliente;
import modelo.pojo.Poblacion;
import modelo.pojo.Vendedor;

/**
 * Clase controlador encargado de cargar el perfil de un usuario
 * 
 * @author ramon
 *
 */
@WebServlet("/Perfil")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class Perfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	PoblacionEJB poblacionEJB;

	@EJB
	LoggersEJB logger;

	static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	static final String PERFIL_CLIENTE_JSP = "/PerfilCliente.jsp";
	static final String PERFIL_VENDEDOR_JSP = "/PerfilVendedor.jsp";

	/**
	 * Método doGet encargado de cargar el perfil de los distintos usuarios
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Creamos el RequestDispatcher por defecto hacia Registro.jsp
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PERFIL_CLIENTE_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			//Si no hay sesión redirigimos a Principal
			if (session == null) {
				
				response.sendRedirect("Principal");
				
			} else {
				
				//Si hay sesión creamos un arraylist para cargar las poblaciones y comprobamos que tipo de usuario hay en sesión				
				ArrayList<Poblacion> poblaciones = null;
				
				//Si es un usuario cliente
				if (session.getAttribute("cliente") != null) {
					
					//Lo recogemos de la sesión
					Cliente cliente = (Cliente) session.getAttribute("cliente");
					
					//Lo metemos en la request
					request.setAttribute("cliente", cliente);
					
					//Obtenemos las poblaciones
					poblaciones = poblacionEJB.getPoblaciones();
					
					//Las metemos en la request
					request.setAttribute("poblaciones", poblaciones);
					
					//RS hacia PERFIL_CLIENTE_JSP
					rs.forward(request, response);
					
				} else {
					
					//Si no es cliente es vendedor, lo recogemos de la sesión
					Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");
					
					//Lo metemos en la request
					request.setAttribute("vendedor", vendedor);
					
					//Cargamos las poblaciones
					poblaciones = poblacionEJB.getPoblaciones();
					
					//Las metemos en la request
					request.setAttribute("poblaciones", poblaciones);
					
					//RS hacia PERFIL_VENDEDOR_JSP
					rs = getServletContext().getRequestDispatcher(PERFIL_VENDEDOR_JSP);
					rs.forward(request, response);
				}
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

}
