package modelo.dao.mappers;


import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import modelo.pojo.Producto;

/**
 * Interfaz mapper para manejar los mapper de Productos
 * 
 * @author ramon
 *
 */
public interface ProductosMapper {

	/**
	 * Método para obtener las productos en función de una busqueda por el nombre de producto
	 * @return ArrayList<Producto> 
	 */
	public ArrayList<Producto> getProductosBusqueda(@Param("busqueda") String busqueda);

}
