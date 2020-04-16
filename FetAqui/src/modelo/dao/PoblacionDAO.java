package modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.PoblacionesMapper;
import modelo.pojo.Poblacion;

/**
 * Clase DAO con los métodos necesarios para acceder a los mapper (BD)
 * necesarios de Estadisticas
 * 
 * @author ramon
 *
 */
public class PoblacionDAO {

	
	public ArrayList<Poblacion> getPoblaciones() {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		
		try {
			// Obtenemos el mapper
			PoblacionesMapper poblacionesMapper = sqlSession.getMapper(PoblacionesMapper.class);
			//Devolvemos el arraylist de poblacion
			return poblacionesMapper.getPoblaciones();

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}
	
	public Poblacion getPoblacionPorId(Integer id_poblacion) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		
		try {
			// Obtenemos el mapper
			PoblacionesMapper poblacionesMapper = sqlSession.getMapper(PoblacionesMapper.class);
			//Devolvemos el arraylist de poblacion
			return poblacionesMapper.getPoblacionPorId(id_poblacion);

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	

}
