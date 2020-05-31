package modelo.dao;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.ValoracionesProductoMapper;
import modelo.pojo.ValoracionProducto;

/**
 * Clase DAO con los métodos necesarios para acceder a los mapper (BD)
 * necesarios de Estadisticas
 * 
 * @author ramon
 *
 */
public class ValoracionProductoDAO {

	
	/**
	 * Método para insertar una direccion en BD
	 * 
	 * @param direccion
	 */
	public void insertarValoracionProducto(ValoracionProducto valoracionProducto) {

		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			ValoracionesProductoMapper valoracionesProductoMapper = sqlSession.getMapper(ValoracionesProductoMapper.class);
			// Insertamos la direccion
			valoracionesProductoMapper.insertarValoracionProducto(valoracionProducto);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}
	
	public void borrarValoracionCliente(Integer id_cliente) {

		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			ValoracionesProductoMapper valoracionesProductoMapper = sqlSession.getMapper(ValoracionesProductoMapper.class);
			// Insertamos la direccion
			valoracionesProductoMapper.borrarValoracionCliente(id_cliente);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}


	

}
