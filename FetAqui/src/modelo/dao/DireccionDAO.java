package modelo.dao;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.DireccionesMapper;
import modelo.pojo.Direccion;

/**
 * Clase DAO encargada de manejar las direcciones de los usuarios
 * 
 * @author ramon
 *
 */
public class DireccionDAO {

	/**
	 * Método para insertar una direccion en BD
	 * 
	 * @param direccion Recibe pojo Direccion
	 */
	public void insertarDireccion(Direccion direccion) {

		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			DireccionesMapper direccionesMapper = sqlSession.getMapper(DireccionesMapper.class);
			// Insertamos la direccion
			direccionesMapper.insertarDireccion(direccion);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

}
