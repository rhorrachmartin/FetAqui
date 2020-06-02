package modelo.dao;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.ValoracionesProductoMapper;
import modelo.pojo.ValoracionProducto;

/**
 * Clase DAO para manejar las valoraciones de los Productos
 * 
 * @author ramon
 *
 */
public class ValoracionProductoDAO {

	/**
	 * Método para insertar la valoracion de un producto
	 * 
	 * @param valoracionProducto Recibe un pojo ValoracionProducto
	 */
	public void insertarValoracionProducto(ValoracionProducto valoracionProducto) {

		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			ValoracionesProductoMapper valoracionesProductoMapper = sqlSession
					.getMapper(ValoracionesProductoMapper.class);
			// Insertamos la direccion
			valoracionesProductoMapper.insertarValoracionProducto(valoracionProducto);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para borrar la valoracion de un producto hecha por un usuario Cliente
	 * 
	 * @param id_cliente Recibe la id del cliente
	 */
	public void borrarValoracionCliente(Integer id_cliente) {

		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			ValoracionesProductoMapper valoracionesProductoMapper = sqlSession
					.getMapper(ValoracionesProductoMapper.class);
			// Insertamos la direccion
			valoracionesProductoMapper.borrarValoracionCliente(id_cliente);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

}
