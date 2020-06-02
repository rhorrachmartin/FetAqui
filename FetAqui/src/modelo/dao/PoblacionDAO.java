package modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.PoblacionesMapper;
import modelo.pojo.Poblacion;

/**
 * Método DAO para manejar las Poblaciones
 * 
 * @author ramon
 *
 */
public class PoblacionDAO {

	/**
	 * Método para obtener todas las poblaciones
	 * 
	 * @return Devuelve un arraylist de Poblacion
	 */
	public ArrayList<Poblacion> getPoblaciones() {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			PoblacionesMapper poblacionesMapper = sqlSession.getMapper(PoblacionesMapper.class);
			// Devolvemos el arraylist de poblacion
			return poblacionesMapper.getPoblaciones();

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para obtener una poblacion por su ID
	 * 
	 * @param id_poblacion Recibe la id de la poblacion
	 * @return Devuelve un pojo Poblacion
	 */
	public Poblacion getPoblacionPorId(Integer id_poblacion) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			PoblacionesMapper poblacionesMapper = sqlSession.getMapper(PoblacionesMapper.class);
			// Devolvemos el arraylist de poblacion
			return poblacionesMapper.getPoblacionPorId(id_poblacion);

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

}
