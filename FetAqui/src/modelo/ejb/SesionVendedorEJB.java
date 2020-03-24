package modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;

import modelo.pojo.Vendedor;
/**
 * Clase EJB encargada de manejar las sesiones
 * @author ramon
 *
 */
@Stateless
@LocalBean
public class SesionVendedorEJB {
	/**
	 * Método introducir un pojo Vendedor en la sesión
	 * @param session
	 * @param v
	 */
	public void crearSesion(HttpSession session, Vendedor v) {

		if (session != null) {
			session.setAttribute("vendedor", v);
		}
	}
	/**
	 * Método que devuelve un usuario de la sesión
	 * @param session
	 * @return
	 */
	public Vendedor vendedorLogeado(HttpSession session) {
		Vendedor vendedor = null;

		if (session != null) {
			vendedor = (Vendedor) session.getAttribute("vendedor");
		}

		return vendedor;
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
