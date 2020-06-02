package modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.DireccionDAO;
import modelo.pojo.Direccion;

/**
 * Clase EJB para la llamada al DAO de Direccion
 * @author ramon
 *
 */

@Stateless
@LocalBean
public class DireccionEJB {

	/**
	 * Método para insertar una direccion en BD
	 * 
	 * @param direccion Recibe pojo Direccion
	 */
	public void insertarDireccion(Direccion direccion) {
		DireccionDAO direccionDAO = new DireccionDAO();
		direccionDAO.insertarDireccion(direccion);
	}

	
}
