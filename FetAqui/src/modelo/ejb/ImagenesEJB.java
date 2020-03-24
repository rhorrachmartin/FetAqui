package modelo.ejb;

import java.io.File;
import java.io.IOException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
/**
 * Clase EJB encargada de guardar las imágenes de los usuarios
 * @author ramon
 *
 */
@Stateless
@LocalBean
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ImagenesEJB {
	/**
	 * Directorio donde guardar las imágenes
	 */
	private static final String UPLOAD_DIRECTORY = "Imagenes";
	
	/**
	 * 	Método para guardar la imagen
	 * @param request
	 * @param contexto
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String guardarImagen(HttpServletRequest request, ServletContext contexto)
			throws IOException, ServletException {
		//Ruta donde guargar la imagen
		String uploadPath = contexto.getRealPath("") + File.separator + UPLOAD_DIRECTORY;
		File uploadDir = new File(uploadPath);
		
		//Si el directorio no existe lo creamos
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		//Obtenemos el nombre de la imagen
		String fileName = null;
		for (Part part : request.getParts()) {
			fileName = getFileName(part);
			//Guardamos el archivo en disco
			part.write(uploadPath + File.separator + fileName);
		}
		//Devolvemos el nombre de la imagen
		return fileName;
	}
	/**
	 * Método para obtener el nombre de la imagen.
	 * @param part
	 * @return
	 */
	private  String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename"))
				return content.substring(content.indexOf('=') + 2, content.length() - 1);
		}
		return "desconocido.txt";
	}

}
