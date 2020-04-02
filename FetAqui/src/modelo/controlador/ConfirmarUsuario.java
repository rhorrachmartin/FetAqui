package modelo.controlador;

import java.io.IOException;

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
import modelo.ejb.SesionVendedorEJB;
import modelo.ejb.VendedorEJB;

/**
 * Clase encarga de confirmar el usuario una vez se ha registrado y ha confirmado su activación por corre electrónico
 * @author ramon
 *
 */
@WebServlet("/ConfirmarUsuario")
public class ConfirmarUsuario extends HttpServlet {
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
	
	/**
	 * EJB para trabajar con los logger
	 */
	@EJB
	LoggersEJB logger;
	
	/**
	 * Método doGet que confirma la activación de un usuario
	 */
	
	static final String HOME_JSP = "/Home.jsp";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		//Creamos el por defecto hacia  ConfirmarUsuario.jsp
		RequestDispatcher rs = getServletContext().getRequestDispatcher(HOME_JSP);
		
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);
		
		
		//Si no hay sesión iniciada
		try {
			boolean activado = false;
			
			if (session == null) {
				
				//Recogemos el código de activación que viene "incrustado" en el link del email recibido por
				//el usuario
				int codigo = Integer.parseInt(request.getParameter("codigo"));
				
				int usuario = 0;
				String error = null;
				
				//Comprobamos que existe el código en la tabla de vendedores
				if (codigoVendedorEJB.existeCodigo(codigo)) {
					//Si existe el código buscamos al usuario al cual pertenece
					usuario = codigoVendedorEJB.buscarVendedorPorCodigo(codigo);
					//Activamos al usuario
					vendedorEJB.activarVendedor(usuario);
					//Borramos el código almacenado en BD relacionado con el usuario
					codigoVendedorEJB.borrarCodigo(usuario);
					//Metemos el mensaje de activación en la request
					activado = true;
					request.setAttribute("activado", activado);
					rs.forward(request, response);
				
					//Si no existe en la tabla de vendedores buscamos en la de clientes
				}
				
				if(codigoClienteEJB.existeCodigo(codigo)) {
					//Si existe el código buscamos al usuario al cual pertenece
					usuario = codigoClienteEJB.buscarClientePorCodigo(codigo);
					//Activamos al usuario
					clienteEJB.activarCliente(usuario);
					//Borramos el código almacenado en BD relacionado con el usuario
					codigoClienteEJB.borrarCodigo(usuario);
					//Metemos el mensaje de activación en la request					
					activado = true;					
					request.setAttribute("activado", activado);					
					rs.forward(request, response);
					
				} else {
					//Si no existe el código en BD mostramos un error.
					error = "Este código no existe, vuelva a registrarse";
					//Metemos el mensaje de error en la request
					request.setAttribute("error", error);
					rs.forward(request, response);
				}

			} else {
				//Si hay sesión iniciada redirige a principal
				response.sendRedirect("Principal");
				
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}
	}

}
