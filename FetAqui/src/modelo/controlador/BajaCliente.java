package modelo.controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ejb.ClienteEJB;
import modelo.ejb.LoggersEJB;
import modelo.ejb.SesionClienteEJB;
import modelo.ejb.ValoracionPostEJB;
import modelo.ejb.ValoracionProductoEJB;
import modelo.ejb.ValoracionVendedorEJB;
import modelo.pojo.Cliente;

/**
 * Controlador encargado de dar de baja un usuario Cliente
 * 
 * @author ramon
 *
 */
@WebServlet("/BajaCliente")
public class BajaCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	ValoracionPostEJB valoracionPostEJB;

	@EJB
	ValoracionProductoEJB valoracionProductoEJB;

	@EJB
	ValoracionVendedorEJB valoracionVendedorEJB;

	@EJB
	ClienteEJB clienteEJB;

	@EJB
	SesionClienteEJB sesionClienteEJB;

	@EJB
	LoggersEJB logger;
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	/**
	 * Método doGet encargado de dar de baja un cliente
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			
			//REcogemos al usuario cliente de la sesión
			Cliente cliente = (Cliente) session.getAttribute("cliente");
			
			//Comprobamos que la sesión existe y el usuario de la misma también
			if (session != null && cliente.getEmail() != null) {
				
				//Recogemos el id del cliente
				Integer id_cliente = cliente.getId_cliente();
				
				//Borramos las valoraciones asociadas al usuario en cuestión
				valoracionPostEJB.borrarValoracionCliente(id_cliente);
				valoracionProductoEJB.borrarValoracionCliente(id_cliente);
				valoracionVendedorEJB.borrarValoracionCliente(id_cliente);
				
				//Borramos al usuario de la BD
				clienteEJB.bajaCliente(id_cliente);
				
				//Cerramos la sesión asociada al usuario
				sesionClienteEJB.cerrarSesion(session);
				
				//Redirigimos a Principal
				response.sendRedirect("Principal");
			} else {
				
				//Si no hay sesión redirigimos a Principal
				response.sendRedirect("Principal");
			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}

	}

}
