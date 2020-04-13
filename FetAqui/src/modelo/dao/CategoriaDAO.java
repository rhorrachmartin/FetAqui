package modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.CategoriasMapper;
import modelo.pojo.Categoria;

/**
 * Clase DAO con los métodos necesarios para acceder a los mapper (BD)
 * necesarios de Estadisticas
 * 
 * @author ramon
 *
 */
public class CategoriaDAO {

	
	public ArrayList<Categoria> getCategorias() {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		
		try {
			// Obtenemos el mapper
			CategoriasMapper categoriasMapper = sqlSession.getMapper(CategoriasMapper.class);
			//Devolvemos el arraylist de poblacion
			return categoriasMapper.getCategorias();

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}
	
	public Categoria getCategoriaPorId(Integer id_categoria) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		
		try {
			// Obtenemos el mapper
			CategoriasMapper categoriasMapper = sqlSession.getMapper(CategoriasMapper.class);
			//Devolvemos el arraylist de poblacion
			return categoriasMapper.getCategoriaPorId(id_categoria);

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	

}
