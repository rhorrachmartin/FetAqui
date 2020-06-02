package modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.PoblacionDAO;
import modelo.pojo.Poblacion;

/**
 * Clase EJB para la llamada al DAO de Poblacion
 * @author ramon
 *
 */

@Stateless
@LocalBean
public class PoblacionEJB {
	
	/**
	 * Método para obtener todas las poblaciones
	 * 
	 * @return Devuelve un arraylist de Poblacion
	 */
	public ArrayList<Poblacion> getPoblaciones() {
		PoblacionDAO poblacionDAO = new PoblacionDAO();
		return poblacionDAO.getPoblaciones();
	}
	
	/**
	 * Método para obtener una poblacion por su ID
	 * 
	 * @param id_poblacion Recibe la id de la poblacion
	 * @return Devuelve un pojo Poblacion
	 */
	public Poblacion getPoblacionPorId(Integer id_poblacion) {
		PoblacionDAO poblacionDAO = new PoblacionDAO();
		return poblacionDAO.getPoblacionPorId(id_poblacion);
	}

}
