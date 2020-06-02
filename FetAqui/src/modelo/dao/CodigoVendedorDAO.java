package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.CodigosActivacionVendedorMapper;
import modelo.pojo.CodigoActivacionVendedor;

/**
 * Clase para manejar los códigos de activación de un usuario Vendedor
 * 
 * @author ramon
 *
 */
public class CodigoVendedorDAO {
	static Connection con;
	static PreparedStatement pst = null;
	static ResultSet rs = null;

	/**
	 * Método para insertar un codigo de activación de vendedor en BD
	 * 
	 * @param c Recibe CodigoActivacionVendedor
	 */
	public void insertCodigo(CodigoActivacionVendedor c) {

		// Abrimos sesion
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			CodigosActivacionVendedorMapper codigoActivacionVendedorMapper = sqlSession
					.getMapper(CodigosActivacionVendedorMapper.class);
			// Insertamos el código en bd
			codigoActivacionVendedorMapper.insertCodigo(c);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para borrar un codigo de activación en función del id de usuario
	 * 
	 * @param id_vendedor
	 */
	public void borrarCodigo(int id_vendedor) {
		// Abrimos sesion
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			CodigosActivacionVendedorMapper codigosActivacionVendedor = sqlSession
					.getMapper(CodigosActivacionVendedorMapper.class);
			// Borramos el código de BD
			codigosActivacionVendedor.borrarCodigo(id_vendedor);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para comprobar si un codigo de activación existe en BD
	 * 
	 * @param codigo
	 * @return Devuelve un boolean
	 */
	public boolean existeCodigo(int codigo) {
		// Abrimos sesion
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			CodigosActivacionVendedorMapper codigosActivacionVendedor = sqlSession
					.getMapper(CodigosActivacionVendedorMapper.class);
			// Obtenemos true o false en función de si existe o no
			return codigosActivacionVendedor.existeCodigo(codigo);
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para recoger el id de un usuario asociado al código
	 * 
	 * @param codigo
	 * @return Devuelve la id del vendedor
	 */
	public int buscarVendedorPorCodigo(int codigo) {
		// Abrimos sesion
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			CodigosActivacionVendedorMapper codigosActivacionVendedor = sqlSession
					.getMapper(CodigosActivacionVendedorMapper.class);
			// Obtenemos el id del usuario
			return codigosActivacionVendedor.buscarVendedorPorCodigo(codigo);
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}
}
