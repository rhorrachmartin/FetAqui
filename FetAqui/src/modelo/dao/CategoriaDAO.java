package modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.CategoriasMapper;
import modelo.pojo.Categoria;

/**
 * Clase DAO para manejar las categorías de los productos
 * 
 * @author ramon
 *
 */
public class CategoriaDAO {

	/**
	 * Método para obtener todas las categorías de productos en BD
	 * 
	 * @return Devuelve un arraylist de Categoría
	 */
	public ArrayList<Categoria> getCategorias() {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			CategoriasMapper categoriasMapper = sqlSession.getMapper(CategoriasMapper.class);
			// Devolvemos el arraylist de poblacion
			return categoriasMapper.getCategorias();

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para obtener una categoría en función de su id
	 * 
	 * @param id_categoria Recibe la id de la categoria
	 * @return devuelve un pojo Categoria
	 */
	public Categoria getCategoriaPorId(Integer id_categoria) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			CategoriasMapper categoriasMapper = sqlSession.getMapper(CategoriasMapper.class);
			// Devolvemos el arraylist de poblacion
			return categoriasMapper.getCategoriaPorId(id_categoria);

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

}
