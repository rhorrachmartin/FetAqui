package modelo.dao;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.ValoracionesPostsMapper;
import modelo.pojo.ValoracionPost;

/**
 * Clase DAO para manejar las valoraciones de los Post
 * 
 * @author ramon
 *
 */
public class ValoracionPostDAO {

	/**
	 * Método para insertar una valoracion de un Post
	 * 
	 * @param valoracionPost recibe un pojo ValoracionPost
	 */
	public void insertarValoracionPostPorDefecto(ValoracionPost valoracionPost) {

		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			ValoracionesPostsMapper valoracionesPostsMapper = sqlSession.getMapper(ValoracionesPostsMapper.class);
			// Insertamos la direccion
			valoracionesPostsMapper.insertarValoracionPostPorDefecto(valoracionPost);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para borrar la valoracion de un post hecha por un cliente
	 * 
	 * @param id_cliente Recibe la id del cliente
	 */
	public void borrarValoracionCliente(Integer id_cliente) {

		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			ValoracionesPostsMapper valoracionesPostsMapper = sqlSession.getMapper(ValoracionesPostsMapper.class);
			// Insertamos la direccion
			valoracionesPostsMapper.borrarValoracionCliente(id_cliente);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

}
