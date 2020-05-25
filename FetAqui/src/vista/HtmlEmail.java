package vista;

/**
 * 
 * @author ramon
 *
 */
public class HtmlEmail {
	
	/**
	 * Método que devuelve un string para generar el mensaje del correo de confirmación de usuario
	 * @param codigo
	 * @return string
	 */
	public String mailConfirmacion(int codigo) {
		return "<html>" + "<head>" + "<meta charset=\"UTF8\">" + "</head>" + "<body>"
				+ "<h1>Pulse sobre el link para confirmar su usuario!</h1>"
				+ "<h3>Recuerde que su email es el nombre de usuario de la aplicación</h3>"
				+ "<h2><a href='http://fetaquimallorca.com/ConfirmarUsuario?codigo=" + codigo +"'"
				+ ">Confirmar alta en la aplicación</a></h2>" + "</body>" + "</html>";
		
	}
	

}
