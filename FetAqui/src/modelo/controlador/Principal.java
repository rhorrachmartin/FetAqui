package modelo.controlador;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ejb.ClienteEJB;
import modelo.ejb.CodigoClienteEJB;
import modelo.ejb.CodigoVendedorEJB;
import modelo.ejb.LoggersEJB;
import modelo.ejb.PostEJB;
import modelo.ejb.ProductoEJB;
import modelo.ejb.SesionClienteEJB;
import modelo.ejb.SesionVendedorEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Cliente;
import modelo.pojo.CodigoActivacionCliente;
import modelo.pojo.CodigoActivacionVendedor;
import modelo.pojo.Post;
import modelo.pojo.Producto;
import modelo.pojo.Vendedor;

/**
 * Clase principal de la aplicación
 * 
 * @author ramon
 *
 */
@WebServlet("/Principal")
public class Principal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	VendedorEJB vendedorEJB;

	@EJB
	ProductoEJB productoEJB;

	@EJB
	PostEJB postEJB;

	@EJB
	ClienteEJB clienteEJB;

	@EJB
	SesionVendedorEJB sesionVendedorEJB;

	@EJB
	SesionClienteEJB sesionClienteEJB;

	@EJB
	CodigoVendedorEJB codigoVendedorEJB;

	@EJB
	CodigoClienteEJB codigoClienteEJB;

	@EJB
	LoggersEJB logger;

	static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	static final String HOME_JSP = "/Home.jsp";
	static final String HOME_LOGEADO_JSP = "/HomeLogeado.jsp";
	static final String HOME_LOGEADO_VENDEDOR_JSP = "/HomeLogeadoVendedor.jsp";

	/**
	 * Método doGet para mostrar la página principal en función del tipo de usuario
	 * logeado
	 */

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);

		// Creamos el RequestDispatcher por defecto hacia Registro.jsp
		RequestDispatcher rs = getServletContext().getRequestDispatcher(HOME_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {

			// Recogemos el parametro "error"
			String error = request.getParameter("error");

			// Lo metemos en la request, el JSP manejará si es nulo o no
			request.setAttribute("error", error);

			// Intentamos obtener el usuario de la sesión
			Vendedor v = sesionVendedorEJB.vendedorLogeado(session);
			Cliente c = sesionClienteEJB.clienteLogeado(session);

			// Si hay sesión
			if (v != null || c != null) {

				// Si es un usuario cliente
				if (c != null) {

					// Lo metemos en la request
					request.setAttribute("cliente", c);

					// RS hacia HOME_LOGEADO_JSP
					rs = getServletContext().getRequestDispatcher(HOME_LOGEADO_JSP);

					// Obtenemos todos los vendedores, productos y posts
					ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();
					ArrayList<Producto> productos = productoEJB.getProductos();
					ArrayList<Post> posts = postEJB.getPosts();

					// Lo metemos en la request
					request.setAttribute("vendedores", vendedores);
					request.setAttribute("productos", productos);
					request.setAttribute("posts", posts);

					// Redirigimos
					rs.forward(request, response);
				} else {

					// Si no es cliente es vendedor, lo metemos en la request
					request.setAttribute("vendedor", v);

					// CArgamos todos los vendedores, productos y posts
					ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();
					ArrayList<Producto> productos = productoEJB.getProductos();
					ArrayList<Post> posts = postEJB.getPosts();

					// Los metemos en la request
					request.setAttribute("vendedores", vendedores);
					request.setAttribute("productos", productos);
					request.setAttribute("posts", posts);

					// RS hacia HOME_LOGEADO_VENDEDOR_JSP
					rs = getServletContext().getRequestDispatcher(HOME_LOGEADO_VENDEDOR_JSP);
					rs.forward(request, response);
				}

			} else {
				// No está logeado, mostramos página principal sin logear HOME_JSP

				rs.forward(request, response);
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

	/**
	 * Método doPost encargado de registrar a los diferentes tipos de usuarios.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		try {

			// Si recibimos los parámetros para un usuario vendedor
			if (request.getParameter("nombrep") != null && request.getParameter("emailp") != null
					&& request.getParameter("passwordp") != null) {

				// Recogemos los parámetros necesarios
				String nombrep = request.getParameter("nombrep");
				String emailp = request.getParameter("emailp");
				String passwordp = request.getParameter("passwordp");

				// Nombre de la imagen por defecto de un usuario
				String imagen = "FotoPorDefecto";

				// Datos del usuario por defecto que no se piden en el formulario de registro
				String nif = " ";
				String telefono = " ";
				int activado = 0;
				int venta_online = 0;

				// Recogemos la fecha actual
				Date hoy = new Date();
				Timestamp fecha = new Timestamp(hoy.getTime());

				// Creamos el pojo usuario
				Vendedor v = new Vendedor();

				// Lo seteamos
				v.setNif(nif);
				v.setNombre(nombrep);
				v.setEmail(emailp);
				v.setPassword(passwordp);
				v.setFoto(imagen);
				v.setTelefono(telefono);
				v.setActivado(activado);
				v.setFecha_alta(fecha);
				v.setVenta_online(venta_online);
				// Comprobamos si el correo ya existe en la BD
				// No vamos a permitir que haya dos usuarios con el mismo correo

				if (vendedorEJB.getVendedorEmail(v.getEmail()) != null || clienteEJB.getClienteEmail(emailp) != null) {
					// Si ya existe mostramos el error

					response.sendRedirect("Principal?error=Correo ya existente");

				} else {
					// Si no existe
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

					// Insertamos el código en BD
					codigoVendedorEJB.insertCodigo(c);

					// Reenviamos la información al servlet de enviar mail para enviar el codigo al
					// usuario
					response.sendRedirect("Mail?email=" + emailp + "&codigo=" + codigo + "");
				}

			} else {

				// Si recibimos los parámetros de un usuario cliente recogemos los parámetros
				// necesarios
				String nombrec = request.getParameter("nombrec");
				String emailc = request.getParameter("emailc");
				String passwordc = request.getParameter("passwordc");

				// Datos de el usuario por defecto que no se piden en el formulario de registro
				String imagen = "FotoPorDefecto";
				String telefono = "TelfDef";

				// Creamos el pojo usuario
				Cliente c = new Cliente();

				// Lo setemoas
				c.setEmail(emailc);
				c.setNombre(nombrec);
				c.setPassword(passwordc);
				c.setApellido("-");
				c.setTelefono(telefono);
				c.setFoto(imagen);
				c.setActivado(0);

				// Comprobamos si el correo ya existe en la BD
				// No vamos a permitir que haya dos usuarios con el mismo correo

				if (clienteEJB.getClienteEmail(emailc) != null || vendedorEJB.getVendedorEmail(emailc) != null) {
					// Si ya existe mostramos el error

					response.sendRedirect("Principal?error=Correo ya existente!");

				} else {

					// Insertamos el usuario en BD
					clienteEJB.insertarCliente(c);
					// Recogemos el usuario insertado en BD
					Cliente cl = clienteEJB.getClienteEmail(emailc);

					// Generamos el código de activación
					int codigo = codigoClienteEJB.generarCodigoCliente();

					// Comprobamos si el código generado existe en BD
					boolean existe = codigoClienteEJB.existeCodigo(codigo);

					// Siempre que exista el codigo sumamos uno al mismo
					while (existe) {
						codigo++;
						existe = codigoClienteEJB.existeCodigo(codigo);
					}

					// Creamos el pojo código
					CodigoActivacionCliente cod = new CodigoActivacionCliente();
					cod.setId(codigo);
					cod.setCliente(cl.getId_cliente());

					// Insertamos el código en BD
					codigoClienteEJB.insertCodigo(cod);

					// Reenviamos la información al servlet de enviar mail para enviar el codigo al
					// usuario
					response.sendRedirect("Mail?email=" + emailc + "&codigo=" + codigo + "");
				}
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

}
