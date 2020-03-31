package modelo.dao.mappers;


import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import modelo.pojo.Direccion;

/**
 * Interfaz mapper para manejar los mapper de Direcciones
 * 
 * @author ramon
 *
 */
public interface DireccionesMapper {

	/**
	 * Método para obtener las direcciones de una población
	 * @return ArrayList<Direccion> 
	 */
	public ArrayList<Direccion> getDireccionesPorPoblacion(@Param("id_poblacion") Integer id_poblacion);
	
	public void insertarDireccion(Direccion direccion);

}
