package modelo.controlador;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ejb.CodigoVendedorEJB;
import modelo.ejb.ImagenesEJB;
import modelo.ejb.LoggersEJB;
import modelo.ejb.SesionVendedorEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.CodigoActivacionVendedor;
import modelo.pojo.Vendedor;

/**
 * Servlet implementation class Registro
 */
@WebServlet("/Principal")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class Principal extends HttpServlet {
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
	static final String HOME_JSP = "/Home.jsp";

	/**
	 * Método doGet para mostrar el formulario de registro
	 */

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Creamos el RequestDispatcher por defecto hacia Registro.jsp
		RequestDispatcher rs = getServletContext().getRequestDispatcher(HOME_JSP);
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
//		ServletContext contexto = getServletContext();

		// Recogemos los parámetros necesarios
		String nombre = request.getParameter("nombre");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		
		try {
			
			String imagen = "FotoPorDefecto";
			int direccion = 1;
			String telefono = "TelfDef";
			int activado = 0;
			int venta_online = 0;
			// Recogemos la fecha actual
			Date hoy = new Date();
			Timestamp fecha = new Timestamp(hoy.getTime());
			// Creamos el pojo usuario
			Vendedor v = new Vendedor();
			v.setNombre(nombre);
			v.setEmail(email1);
			v.setPassword(password1);
			v.setFoto(imagen);
			v.setId_direccion(direccion);
			v.setTelefono(telefono);
			v.setActivado(activado);
			v.setFecha_alta(fecha);
			v.setVenta_online(venta_online);
			
			// Comprobamos si el correo ya existe en la BD
			// No vamos a permitir que haya dos usuarios con el mismo correo

			if (vendedorEJB.getVendedorEmail(v.getEmail()) != null) {
				// Si ya existe mostramos el error
				response.sendRedirect("Principal?error=Correo ya existente");

			} else {
				// Si se ha puesto bien el correo dos veces y la contraseña también
				if (email1.equals(email2) && password1.equals(password2)) {
					// Insertamos el usuario en BD
					vendedorEJB.insertarVendedor(v);
					// Recogemos el usuario insertado en BD
					Vendedor ve = vendedorEJB.getVendedorEmail(v.getEmail());
					
					// Generamos el código de activación
					int codigo = codigoVendedorEJB.generarCodigoVendedor();
					
					// Comprobamos si el código generado existe en BD
					boolean existe = codigoVendedorEJB.existeCodigo(codigo);
					// Siempre que exista el codigo sumamos uno al mismo
					while (existe) {
						codigo++;
						existe = codigoVendedorEJB.existeCodigo(codigo);
					}

					// Creamos el pojo código
					CodigoActivacionVendedor c = new CodigoActivacionVendedor(codigo, ve.getId_vendedor());
					
					System.out.println(c.getId() +", "+ c.getVendedor() );

					// Insertamos el código en BD
					codigoVendedorEJB.insertCodigo(c);
					// Reenviamos la información al servlet de enviar mail para enviar el codigo al
					// user
					response.sendRedirect("Mail?email=" + email2 + "&codigo=" + codigo + "");

				} else {
					// Si no coindicen el password o el mail mostramos el error
					response.sendRedirect("Principal?error=Usuario o Password no coinciden");
				}
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

}
