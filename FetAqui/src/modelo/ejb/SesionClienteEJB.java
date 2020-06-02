package modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;

import modelo.pojo.Cliente;
/**
 * Clase EJB encargada de manejar las sesiones de usuarios Cliente
 * @author ramon
 *
 */
@Stateless
@LocalBean
public class SesionClienteEJB {
	
	/**
	 * Método para introducir un pojo Cliente en la sesión
	 * @param session Recibe la sesion
	 * @param v Devuelve un pojo Vendedor
	 */
	public void crearSesion(HttpSession session, Cliente c) {

		if (session != null) {
			session.setAttribute("cliente", c);
		}
	}
	
	/**
	 * Método que devuelve un usuario de la sesión
	 * @param session REcibe la sesion
	 * @return devuelve un pojo Cliente
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
	 * @param session Recibe la sesión
	 */
	public void cerrarSesion(HttpSession session) {

		if (session != null) {
			session.invalidate();
		}

	}

}
