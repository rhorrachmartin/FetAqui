package modelo.dao.mappers;


import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import modelo.pojo.Categoria;

/**
 * Interfaz mapper para manejar los mapper de Poblaciones
 * 
 * @author ramon
 *
 */
public interface CategoriasMapper {

	/**
	 * Método para obtener las categorias
	 * @return ArrayList<Categoria> 
	 */
	public ArrayList<Categoria> getCategorias();
	
	public Categoria getCategoriaPorId(@Param("id_categoria") Integer id_categoria);

}
