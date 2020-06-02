package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.CodigosActivacionClienteMapper;
import modelo.pojo.CodigoActivacionCliente;

/**
 * Método para manejar los códigos de activación de un usuario Cliente
 * 
 * @author ramon
 *
 */
public class CodigoClienteDAO {
	static Connection con;
	static PreparedStatement pst = null;
	static ResultSet rs = null;

	/**
	 * Método para insertar CodigoActivacionCliente
	 * 
	 * @param c REcibe CodigoActivacionCliente
	 */
	public void insertCodigo(CodigoActivacionCliente c) {

		// Abrimos sesion
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			CodigosActivacionClienteMapper codigoActivacionClienteMapper = sqlSession
					.getMapper(CodigosActivacionClienteMapper.class);
			// Insertamos el código en bd
			codigoActivacionClienteMapper.insertCodigo(c);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para borrar un codigo de activación en función del id de usuario
	 * 
	 * @param id_cliente
	 */
	public void borrarCodigo(int id_cliente) {
		// Abrimos sesion
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			CodigosActivacionClienteMapper codigoActivacionClienteMapper = sqlSession
					.getMapper(CodigosActivacionClienteMapper.class);
			// Borramos el código de BD
			codigoActivacionClienteMapper.borrarCodigo(id_cliente);
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
			CodigosActivacionClienteMapper codigosActivacionCliente = sqlSession
					.getMapper(CodigosActivacionClienteMapper.class);
			// Obtenemos true o false en función de si existe o no
			return codigosActivacionCliente.existeCodigo(codigo);
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para recoger el id de un usuario asociado al código
	 * 
	 * @param codigo
	 * @return Devuelve una id de cliente
	 */
	public int buscarClientePorCodigo(int codigo) {
		// Abrimos sesion
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			CodigosActivacionClienteMapper codigoActivacionClienteMapper = sqlSession
					.getMapper(CodigosActivacionClienteMapper.class);
			// Obtenemos el id del usuario
			return codigoActivacionClienteMapper.buscarClientePorCodigo(codigo);
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}
}
