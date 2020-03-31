package modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.ClientesMapper;
import modelo.dao.mappers.DireccionesMapper;
import modelo.dao.mappers.PoblacionesMapper;
import modelo.pojo.Cliente;
import modelo.pojo.Direccion;
import modelo.pojo.Poblacion;

/**
 * Clase DAO con los métodos necesarios para acceder a los mapper (BD)
 * necesarios de Estadisticas
 * 
 * @author ramon
 *
 */
public class DireccionDAO {

	
	/**
	 * Método para insertar una direccion en BD
	 * 
	 * @param direccion
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
