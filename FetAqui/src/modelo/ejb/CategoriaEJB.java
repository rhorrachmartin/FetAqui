package modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.CategoriaDAO;
import modelo.pojo.Categoria;

/**
 * Clase EJB para la llamada al DAO DE Categorias
 * 
 * @author ramon
 *
 */
@Stateless
@LocalBean
public class CategoriaEJB {

	/**
	 * Método para obtener todas las categorías de productos en BD
	 * 
	 * @return Devuelve un arraylist de Categoría
	 */
	public ArrayList<Categoria> getCategorias() {
		CategoriaDAO categoriaDAO = new CategoriaDAO();

		return categoriaDAO.getCategorias();
	}

	/**
	 * Método para obtener una categoría en función de su id
	 * 
	 * @param id_categoria Recibe la id de la categoria
	 * @return devuelve un pojo Categoria
	 */
	public Categoria getCategoriaPorId(Integer id_categoria) {
		CategoriaDAO categoriaDAO = new CategoriaDAO();

		return categoriaDAO.getCategoriaPorId(id_categoria);
	}

}
