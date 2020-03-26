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
	/**
	 * EJB para trabajar con sesiones
	 */
	@EJB
	SesionVendedorEJB sesionVendedorEJB;
	/**
	 * EJB para trabajar con resultados obtenidos del calculo IMC
	 */
	@EJB
	CodigoVendedorEJB codigoVendedorEJB;
	
	/**
	 * EJB para trabajar con los logger
	 */
	@EJB
	LoggersEJB logger;
	
	/**
	 * Método doGet que confirma la activación de un usuario
	 */
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Creamos el por defecto hacia  ConfirmarUsuario.jsp
		RequestDispatcher rs = getServletContext().getRequestDispatcher("/Home.jsp");
		
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("entro");
		//Si no hay sesión iniciada
		try {
			/*INICIA SESIÓN EN ALGÚN SITIO, MIRAR!!!!!%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%*/
			if (session != null) {
				
				//Recogemos el código de activación que viene "incrustado" en el link del email recibido por
				//el usuario
				int codigo = Integer.parseInt(request.getParameter("codigo"));
				System.out.println(codigo);
				int usuario = 0;
				String error = null;
				boolean activado;
				//Comprobamos que existe el código en BD
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