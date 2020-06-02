package modelo.dao;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.ValoracionesVendedoresMapper;
import modelo.pojo.ValoracionCv;

/**
 * Método para manejar las valoraciones de los vendedores
 * 
 * @author ramon
 *
 */
public class ValoracionVendedorDAO {

	/**
	 * Método para insertar la valoracion de un vendedor
	 * 
	 * @param valoracionCv Recibe pojo ValoracionCv
	 */
	public void insertarValoracionVendedor(ValoracionCv valoracionCv) {

		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			ValoracionesVendedoresMapper valoracionesVendedoresMapper = sqlSession
					.getMapper(ValoracionesVendedoresMapper.class);
			// Insertamos la direccion
			valoracionesVendedoresMapper.insertarValoracionVendedor(valoracionCv);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para borrar la valoracion de un vendedor hecha por un usuario cliente
	 * 
	 * @param id_cliente Recibe la id del cliente
	 */
	public void borrarValoracionCliente(Integer id_cliente) {

		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			ValoracionesVendedoresMapper valoracionesVendedoresMapper = sqlSession
					.getMapper(ValoracionesVendedoresMapper.class);
			// Insertamos la direccion
			valoracionesVendedoresMapper.borrarValoracionCliente(id_cliente);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

}
