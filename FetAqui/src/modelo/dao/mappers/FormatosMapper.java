package modelo.dao.mappers;

import java.util.ArrayList;

import modelo.pojo.Formato;

/**
 * Interfaz mapper para manejar los mapper de Formato
 * 
 * @author ramon
 *
 */
public interface FormatosMapper {

	/**
	 * Método para obtener todos los formatos de la BD
	 * 
	 * @return ArrayList de Formato
	 */
	public ArrayList<Formato> getFormatos();

}
