package modelo.dao;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.ValoracionesPostsMapper;
import modelo.pojo.ValoracionPost;

public class ValoracionPostDAO {

	
	/**
	 * Método para insertar una direccion en BD
	 * 
	 * @param direccion
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


	

}
