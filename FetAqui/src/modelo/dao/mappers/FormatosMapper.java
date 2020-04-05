package modelo.dao.mappers;


import java.util.ArrayList;

import modelo.pojo.Formato;

/**
 * Interfaz mapper para manejar los mapper de Poblaciones
 * 
 * @author ramon
 *
 */
public interface FormatosMapper {

	/**
	 * Método para obtener los Formatos
	 * @return ArrayList<Formato> 
	 */
	public ArrayList<Formato> getFormatos();

}
