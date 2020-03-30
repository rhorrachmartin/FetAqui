package modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;

import modelo.pojo.Cliente;
/**
 * Clase EJB encargada de manejar las sesiones
 * @author ramon
 *
 */
@Stateless
@LocalBean
public class SesionClienteEJB {
	/**
	 * Método introducir un pojo Vendedor en la sesión
	 * @param session
	 * @param v
	 */
	public void crearSesion(HttpSession session, Cliente c) {

		if (session != null) {
			session.setAttribute("cliente", c);
		}
	}
	/**
	 * Método que devuelve un usuario de la sesión
	 * @param session
	 * @return
	 */
	public Cliente clienteLogeado(HttpSession session) {
		Cliente cliente = null;

		if (session != null) {
			cliente = (Cliente) session.getAttribute("cliente");
		}

		return cliente;
	}
	/**
	 * Método para cerrar una sesión
	 * @param session
	 */
	public void cerrarSesion(HttpSession session) {

		if (session != null) {
			session.invalidate();
		}

	}

}
