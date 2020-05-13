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
 * Servlet implementation class Registro
 */
@WebServlet("/Principal")
public class Principal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * EJB para trabajar con Vendedores
	 */
	@EJB
	VendedorEJB vendedorEJB;
	
	@EJB
	ProductoEJB productoEJB;
	
	@EJB
	PostEJB postEJB;

	/**
	 * EJB para trabajar con Clientes
	 */
	@EJB
	ClienteEJB clienteEJB;

	/**
	 * EJB para trabajar con sesiones de vendedor
	 */
	@EJB
	SesionVendedorEJB sesionVendedorEJB;

	/**
	 * EJB para trabajar con sesiones de cliente
	 */
	@EJB
	SesionClienteEJB sesionClienteEJB;
	/**
	 * EJB para tratar los códigos de activación
	 */
	@EJB
	CodigoVendedorEJB codigoVendedorEJB;

	/**
	 * EJB para tratar los códigos de activación
	 */
	@EJB
	CodigoClienteEJB codigoClienteEJB;

	/**
	 * EJB para trabajar con los logger
	 */
	@EJB
	LoggersEJB logger;

	static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	static final String HOME_JSP = "/Home.jsp";
	static final String HOME_LOGEADO_JSP = "/HomeLogeado.jsp";
	static final String HOME_LOGEADO_VENDEDOR_JSP = "/HomeLogeadoVendedor.jsp";

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
		Cliente c = sesionClienteEJB.clienteLogeado(session);

		request.setAttribute("vendedor", v);
		request.setAttribute("cliente", c);
		// Si hay sesión
		if (v != null || c != null) {
			// Ya está logeado, lo redirigimos a la principal
			try {

				if (c != null) {
					rs = getServletContext().getRequestDispatcher(HOME_LOGEADO_JSP);
					rs.forward(request, response);
				} else {
					
					ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();
					ArrayList<Producto> productos = productoEJB.getProductos();
					ArrayList<Post> posts = postEJB.getPosts();
					
					request.setAttribute("vendedores", vendedores);
					request.setAttribute("productos", productos);
					request.setAttribute("posts", posts);
					
					rs = getServletContext().getRequestDispatcher(HOME_LOGEADO_VENDEDOR_JSP);
					rs.forward(request, response);
				}
			} catch (Exception e) {
				logger.setErrorLogger(e.getMessage());
			}
		} else {
			// No está logeado, mostramos página principal

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

		try {

			if (request.getParameter("nombrep") != null && request.getParameter("emailp") != null
					&& request.getParameter("passwordp") != null) {
				// Recogemos los parámetros necesarios
				String nombrep = request.getParameter("nombrep");
				String emailp = request.getParameter("emailp");
				String passwordp = request.getParameter("passwordp");

				String imagen = "FotoPorDefecto";
				String nif = "NIF";
				int direccion = 1;
				String telefono = "Telf";
				int activado = 0;
				int venta_online = 0;
				// Recogemos la fecha actual
				Date hoy = new Date();
				Timestamp fecha = new Timestamp(hoy.getTime());
				// Creamos el pojo usuario
				Vendedor v = new Vendedor();
				v.setNif(nif);
				v.setNombre(nombrep);
				v.setEmail(emailp);
				v.setPassword(passwordp);
				v.setFoto(imagen);
				v.setIdDireccion(direccion);
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
					// user
					response.sendRedirect("Mail?email=" + emailp + "&codigo=" + codigo + "");
				}

			} else {
				// Recogemos los parámetros necesarios
				String nombrec = request.getParameter("nombrec");
				String emailc = request.getParameter("emailc");
				String passwordc = request.getParameter("passwordc");

				String imagen = "FotoPorDefecto";
				int direccion = 1;
				String telefono = "TelfDef";
				// Creamos el pojo usuario
				Cliente c = new Cliente();
				c.setEmail(emailc);
				c.setNombre(nombrec);
				c.setPassword(passwordc);
				c.setApellido("-");
				c.setTelefono(telefono);
				c.setIdDireccion(direccion);
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
					// user
					response.sendRedirect("Mail?email=" + emailc + "&codigo=" + codigo + "");
				}
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

}
