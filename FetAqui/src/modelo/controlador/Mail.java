package modelo.controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.ejb.LoggersEJB;
import modelo.ejb.MailOfficeEJB;
import vista.HtmlEmail;
/**
 * Clase encarga de enviar el mail de confirmación al usuario que se registre
 * @author ramon
 *
 */
@WebServlet("/Mail")
public class Mail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * EJB para poder enviar emails
	 */
	@EJB
	MailOfficeEJB mailOfficeEJB;
	
	/**
	 * EJB para trabajar con los logger
	 */
	@EJB
	LoggersEJB logger;
	
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	static final String HOME_JSP = "/Home.jsp";
	/**
	 * Método doGet para enviar el correo de confirmación de registro
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		//Creamos el RequestDispatcher por defecto a Mail.jsp
		RequestDispatcher rs = getServletContext().getRequestDispatcher(HOME_JSP);
		
		
		// Instancia de la clase pertinente para mostrar la vista del correo que recibe el usuario
		HtmlEmail mail = new HtmlEmail();
		
		try {
			//Recogemos los atributos necesarios
			String para = request.getParameter("email");
			String remitente = "rhorrach@gmail.com";
			String asunto = "Email de confirmación de registro en FET AQUI";
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			
			//Generamos el mensaje
			String mensaje = mail.mailConfirmacion(codigo);
			
			//Si los parámetros necesarios no están vacíos
			if ((para != null)  && (mensaje != null)) {
				//Enviamos el email
				mailOfficeEJB.sendMail2(para, remitente, asunto, mensaje);
			}
			request.setAttribute("email", para);
			rs.forward(request, response);
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}
	}

}
