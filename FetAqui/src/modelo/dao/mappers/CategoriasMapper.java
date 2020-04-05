package modelo.dao.mappers;


import java.util.ArrayList;

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

}
