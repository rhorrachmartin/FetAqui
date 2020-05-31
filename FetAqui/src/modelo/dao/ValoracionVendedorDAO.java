package modelo.dao;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.ValoracionesVendedoresMapper;
import modelo.pojo.ValoracionCv;

public class ValoracionVendedorDAO {

	/**
	 * Método para insertar una direccion en BD
	 * 
	 * @param direccion
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
