package modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.FormatosMapper;
import modelo.pojo.Formato;

/**
 * Clase DAO con los métodos necesarios para acceder a los mapper (BD)
 * necesarios de Estadisticas
 * 
 * @author ramon
 *
 */
public class FormatoDAO {

	
	public ArrayList<Formato> getFormatos() {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		
		try {
			// Obtenemos el mapper
			FormatosMapper formatossMapper = sqlSession.getMapper(FormatosMapper.class);
			//Devolvemos el arraylist de poblacion
			return formatossMapper.getFormatos();

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	

}
