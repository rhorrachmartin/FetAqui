package modelo.controlador;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

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

import modelo.ejb.CodigoVendedorEJB;
import modelo.ejb.CodigosEJB;
import modelo.ejb.ImagenesEJB;
import modelo.ejb.LoggersEJB;
import modelo.ejb.SesionVendedorEJB;
import modelo.ejb.SesionesEJB;
import modelo.ejb.UsuariosEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Codigo;
import modelo.pojo.Usuario;
import modelo.pojo.Vendedor;

/**
 * Servlet implementation class Registro
 */
@WebServlet("/Registro")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * EJB para trabajar con Usuarios
	 */
	@EJB
	VendedorEJB vendedorEJB;
	/**
	 * EJB para trabajar con sesiones
	 */
	@EJB
	SesionVendedorEJB sesionVendedorEJB;
	/**
	 * EJB para tratar las imágenes
	 */
	@EJB
	ImagenesEJB imagenesEJB;
	/**
	 * EJB para tratar los códigos de activación
	 */
	@EJB
	CodigoVendedorEJB codigoVendedorEJB;

	/**
	 * EJB para trabajar con los logger
	 */
	@EJB
	LoggersEJB logger;

	static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	static final String REGISTRO_JSP = "/Registro.jsp";
	static final String REGISTRO_JSP2 = "/Registro2.jsp";

	/**
	 * Método doGet para mostrar el formulario de registro
	 */

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Creamos el RequestDispatcher por defecto hacia Registro.jsp
		RequestDispatcher rs = getServletContext().getRequestDispatcher(REGISTRO_JSP);
		response.setContentType(CONTENT_TYPE);
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);
		// Recogemos el parametro "error"
		String error = request.getParameter("error");
		request.setAttribute("error", error);
		// Intentamos obtener el usuario de la sesión
		Vendedor v = sesionVendedorEJB.vendedorLogeado(session);
		request.setAttribute("v", v);
		// Si hay sesión
		if (v != null) {
			// Ya está logeado, lo redirigimos a la principal
			try {
				response.sendRedirect("Principal");
			} catch (Exception e) {
				logger.setErrorLogger(e.getMessage());
			}
		} else {
			// No está logeado, mostramos página de login

			String vista = request.getParameter("vista");

			if (vista != null && vista.equals("n")) {
				rs = getServletContext().getRequestDispatcher(REGISTRO_JSP2);

			}

			rs.forward(request, response);
		}

	}

	/**
	 * Método doPost para registrar usuario en BD
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);

		// Recogemos el contexto
		ServletContext contexto = getServletContext();

		// Recogemos los parámetros necesarios
		String nombre = request.getParameter("nombre");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		
		try {
			
			String imagen = "FotoPorDefecto";
			int direccion = 1;
			String telefono = "TelfDefecto";
			int activado = 0;
			int venta_online = 0;
			// Recogemos la fecha actual
			Date hoy = new Date();
			Timestamp fecha = new Timestamp(hoy.getTime());
			// Creamos el pojo usuario
			Vendedor v = new Vendedor(nombre,email1, password1, imagen , direccion,telefono,activado, fecha,venta_online);

			// Comprobamos si el correo ya existe en la BD
			// No vamos a permitir que haya dos usuarios con el mismo correo

			if (vendedorEJB.comprobarMailVendedor(v.getEmail())) {

				// Si ya existe mostramos el error
				response.sendRedirect("Registro?error=Correo ya existente");

			} else {
				// Si se ha puesto bien el correo dos veces y la contraseña también
				if (email1.equals(email2) && password1.equals(password2)) {
					// Insertamos el usuario en BD
					vendedorEJB.insertarVendedor(v);
					// Recogemos el usuario insertado en BD
					Vendedor vendedor = vendedorEJB.getVendedorEmailPass(email2, password2);
					// Generamos el código de activación
					int codigo = codigosEJB.generarCodigo();
					// Comprobamos si el código generado existe en BD
					boolean existe = codigosEJB.existeCodigo(codigo);
					// Siempre que exista el codigo sumamos uno al mismo
					while (existe) {
						codigo++;
						existe = codigosEJB.existeCodigo(codigo);
					}

					// Creamos el pojo código
					Codigo c = new Codigo(codigo, usuario.getId());

					// Insertamos el código en BD
					codigosEJB.insertCodigo(c);
					// Reenviamos la información al servlet de enviar mail para enviar el codigo al
					// user
					response.sendRedirect("Mail?email=" + email2 + "&codigo=" + codigo + "&vista=" + vista + "");

				} else {
					// Si no coindicen el password o el mail mostramos el error
					response.sendRedirect("Registro?error=Usuario o Password no coinciden&vista=" + vista);
				}
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

}
