package modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.FormatoDAO;
import modelo.pojo.Formato;

/**
 * Clase EJB para la llamada al DAO de formato
 * @author ramon
 *
 */

@Stateless
@LocalBean
public class FormatoEJB {
	
	/**
	 * Método para obtener todos los formatos de la BD
	 * 
	 * @return ArrayList de Formato
	 */
	public ArrayList<Formato> getFormatos() {
		FormatoDAO formatoDAO = new FormatoDAO();

		return formatoDAO.getFormatos();
	}

}
