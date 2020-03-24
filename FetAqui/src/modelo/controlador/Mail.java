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
	static final String MAIL_JSP = "/Mail.jsp";
	static final String MAIL_JSP2 = "/Mail2.jsp";
	/**
	 * Método doGet para enviar el correo de confirmación de registro
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		//Creamos el RequestDispatcher por defecto a Mail.jsp
		RequestDispatcher rs = getServletContext().getRequestDispatcher(MAIL_JSP);
		
		
		// Instancia de la clase pertinente para mostrar la vista del correo que recibe el usuario
		HtmlEmail mail = new HtmlEmail();
		
		try {
			//Recogemos los atributos necesarios
			String vista = request.getParameter("vista");
			String para = request.getParameter("email");
			String remitente = "rhorrach@gmail.com";
			String asunto = "Email de confirmación de registro en Calcula tu IMC";
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			
			//Generamos el mensaje
			String mensaje = mail.mailConfirmacion(codigo);
			
			//Si los parámetros necesarios no están vacíos
			if ((para != null)  && (mensaje != null)) {
				//Enviamos el email
				mailOfficeEJB.sendMail(para, remitente, asunto, mensaje,
						this.getServletContext().getRealPath("/imc.png"));
			}
			//Si el parámetro vista es igual a "n" redirigimos a Mail2.jsp
			if(vista.equals("n")) {
				rs = getServletContext().getRequestDispatcher(MAIL_JSP2);
			}
			//Introducimos en la request el parámetro "para" que es un el correo del usuario registrado 
			request.setAttribute("para", para);
			rs.forward(request, response);
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}
	}

}
