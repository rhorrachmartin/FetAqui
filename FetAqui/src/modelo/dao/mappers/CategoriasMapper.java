package modelo.dao.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import modelo.pojo.Categoria;

/**
 * Interfaz mapper para manejar los mapper de Categorias
 * 
 * @author ramon
 *
 */
public interface CategoriasMapper {

	/**
	 * Método para obtener las categorias
	 * 
	 * @return ArrayList<Categoria>
	 */
	public ArrayList<Categoria> getCategorias();

	/**
	 * Método para obtener una categoria por su id
	 * 
	 * @param id_categoria
	 * @return
	 */
	public Categoria getCategoriaPorId(@Param("id_categoria") Integer id_categoria);

}
