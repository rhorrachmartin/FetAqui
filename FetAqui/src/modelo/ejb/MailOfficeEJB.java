package modelo.ejb;

import java.io.File;

import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Clase EJB encargada de enviar los emails a los usuarios
 * @author ramon
 *
 */
@Stateless
@LocalBean
public class MailOfficeEJB {
	/**
	 * EJB para trabajar con loggers
	 */
	@EJB
	LoggersEJB logger;
	
	 private static final String SMTP = "smtp.gmail.com";
	
	/**
	 * Método para enviar un correo con imagen
	 * @param para
	 * @param remitente
	 * @param asunto
	 * @param mensaje
	 * @param archivo
	 */
	public void sendMail(String para, String remitente, String asunto, String mensaje, String archivo) {
		Properties prop = new Properties();
		// Creamos un objeto de oficina de correos
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", SMTP);
		prop.put("mail.smtp.port", 587);
		prop.put("mail.smtp.ssl.trust", SMTP);

		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("rhorrach@gmail.com", "86Mesias!86");
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(remitente));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(para));
			message.setSubject(asunto);

			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(mensaje, "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);

			MimeBodyPart attachmentBodyPart = new MimeBodyPart();
			attachmentBodyPart.attachFile(new File(archivo));
			multipart.addBodyPart(attachmentBodyPart);

			message.setContent(multipart);

			Transport.send(message);

		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}
	}
	/**
	 * Método para enviar un correo sin imagen
	 * @param para
	 * @param remitente
	 * @param asunto
	 * @param mensaje
	 */
	public void sendMail2(String para, String remitente, String asunto, String mensaje) {

		Properties prop = new Properties();
		// Creamos un objeto de oficina de correos
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 587);
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("rhorrach@gmail.com", "86Mesias!86");
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(remitente));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(para));
			message.setSubject(asunto);

			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(mensaje, "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);

			message.setContent(multipart);

			Transport.send(message);

		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}
	}

}