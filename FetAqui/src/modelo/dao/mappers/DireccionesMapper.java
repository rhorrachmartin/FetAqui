package modelo.dao.mappers;

import modelo.pojo.Direccion;

/**
 * Interfaz mapper para manejar los mapper de Direcciones
 * 
 * @author ramon
 *
 */
public interface DireccionesMapper {

	/**
	 * Método para insertar una direccion en BD
	 * 
	 * @param direccion Recibe pojo Direccion
	 */

	public void insertarDireccion(Direccion direccion);

}
