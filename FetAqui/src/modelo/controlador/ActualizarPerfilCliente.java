package modelo.controlador;

import java.io.IOException;

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

import modelo.ejb.ClienteEJB;
import modelo.ejb.CodigoClienteEJB;
import modelo.ejb.CodigoVendedorEJB;
import modelo.ejb.DireccionEJB;
import modelo.ejb.ImagenesEJB;
import modelo.ejb.LoggersEJB;
import modelo.ejb.SesionVendedorEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Cliente;
import modelo.pojo.Direccion;

/**
 * Servlet implementation class ActualizarPerfilCliente
 */
@WebServlet("/ActualizarPerfilCliente")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ActualizarPerfilCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * EJB para trabajar con Usuarios
	 */
	@EJB
	VendedorEJB vendedorEJB;

	@EJB
	ClienteEJB clienteEJB;
	/**
	 * EJB para trabajar con sesiones
	 */
	@EJB
	SesionVendedorEJB sesionVendedorEJB;

	/**
	 * EJB para trabajar con los codigos de activación de vendedores
	 */
	@EJB
	CodigoVendedorEJB codigoVendedorEJB;

	/**
	 * EJB para trabajar con los codigos de activacion de clientes
	 */
	@EJB
	CodigoClienteEJB codigoClienteEJB;
	
	@EJB
	DireccionEJB direccionEJB;

	/**
	 * EJB para trabajar con los logger
	 */
	@EJB
	LoggersEJB logger;

	@EJB
	ImagenesEJB imagenesEJB;

	static final String PERFIL_CLIENTE_JSP = "/PerfilCliente.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		if (session == null) {
			response.sendRedirect("Principal");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		// Creamos el RequestDispatcher por defecto hacia Registro.jsp
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PERFIL_CLIENTE_JSP);

		// Recogemos el contexto
		ServletContext contexto = getServletContext();

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		Cliente cliente = (Cliente) session.getAttribute("cliente");
		System.out.println(cliente.toString());
		Cliente clienteExiste = null;
		String apellido = request.getParameter("apellido");
		System.out.println(apellido);
		String nombre = request.getParameter("nombre");
		System.out.println(nombre);
		String telefono = request.getParameter("telefono");
		System.out.println(telefono);
		String passAntiguo = request.getParameter("passAntiguo");
		String passNuevo1 = request.getParameter("passNuevo1");
		String passNuevo2 = request.getParameter("passNuevo2");
		String direccion = request.getParameter("direccion");
		System.out.println("Direccion: " + direccion);
		Integer id_poblacion = Integer.valueOf(request.getParameter("poblacion"));
		Direccion dir = new Direccion();
		try {
			String foto = imagenesEJB.guardarImagen(request, contexto);
			if (foto == null) {
				foto = "FotoPorDefecto";
			}
			System.out.println(foto);

			if (cliente.getNombre() != null) {
				clienteExiste = clienteEJB.getCliente(cliente.getEmail(), cliente.getPassword());
				System.out.println("entra1");

				if (clienteExiste.getPassword().equals(passAntiguo) && passNuevo1.equals(passNuevo2)) {
					System.out.println("entra2");
					clienteEJB.updateTelf(telefono, clienteExiste.getId_cliente());
					clienteEJB.updateNombre(nombre, clienteExiste.getId_cliente());
					clienteEJB.updateApellido(apellido, clienteExiste.getId_cliente());
					clienteEJB.updateFoto(foto, clienteExiste.getId_cliente());
					clienteEJB.updatePassword(passNuevo2, clienteExiste.getId_cliente());
					System.out.println(clienteExiste.getDireccion());
					System.out.println(direccion);
					if(!clienteExiste.getDireccion().equals(direccion)) {
						System.out.println("Entra 3");
						dir.setDireccion(direccion);
						dir.setId_poblacion(id_poblacion);
						direccionEJB.insertarDireccion(dir);
						clienteEJB.updateDireccion(clienteExiste.getId_cliente());
					}
					
					Cliente clienteActualizado = clienteEJB.getCliente(cliente.getEmail(), cliente.getPassword());
					request.setAttribute("cliente", clienteActualizado);
					rs.forward(request, response);
				} else {
					String error = "Las contraseñas no coinciden";
					Cliente cliente3 = (Cliente)session.getAttribute("cliente");
					request.setAttribute("cliente", cliente3);
					request.setAttribute("error", error);
					rs.forward(request, response);
				}

			} else {
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			e.getMessage();
		}

	}

}
