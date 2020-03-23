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
	 * Método para obtener las productos de un vendedor
	 * @return ArrayList<Producto> 
	 */
	public ArrayList<Producto> getProductosVendedor(@Param("id_vendedor") Integer id_vendedor);

}
