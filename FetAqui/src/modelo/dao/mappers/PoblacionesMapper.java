package modelo.dao.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import modelo.pojo.Poblacion;

/**
 * Interfaz mapper para manejar los mapper de Poblaciones
 * 
 * @author ramon
 *
 */
public interface PoblacionesMapper {

	/**
	 * Método para obtener todas las poblaciones
	 * 
	 * @return Devuelve un arraylist de Poblacion
	 */
	public ArrayList<Poblacion> getPoblaciones();

	/**
	 * Método para obtener una poblacion por su ID
	 * 
	 * @param id_poblacion Recibe la id de la poblacion
	 * @return Devuelve un pojo Poblacion
	 */
	public Poblacion getPoblacionPorId(@Param("id_poblacion") Integer id_poblacion);

}
