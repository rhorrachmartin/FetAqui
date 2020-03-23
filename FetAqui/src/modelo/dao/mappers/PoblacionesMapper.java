package modelo.dao.mappers;


import java.util.ArrayList;

import modelo.pojo.Poblacion;

/**
 * Interfaz mapper para manejar los mapper de Poblaciones
 * 
 * @author ramon
 *
 */
public interface PoblacionesMapper {

	/**
	 * Método para obtener las poblaciones
	 * @return ArrayList<Poblacion> 
	 */
	public ArrayList<Poblacion> getPoblaciones();

}
