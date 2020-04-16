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
	 * Método para obtener las poblaciones
	 * @return ArrayList<Poblacion> 
	 */
	public ArrayList<Poblacion> getPoblaciones();
	
	public Poblacion getPoblacionPorId(@Param("id_poblacion") Integer id_poblacion);

}
