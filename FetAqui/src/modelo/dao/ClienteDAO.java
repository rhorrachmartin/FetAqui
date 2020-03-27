package modelo.dao;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.ClientesMapper;
import modelo.pojo.Cliente;

/**
 * Clase DAO con los métodos necesarios para acceder a los mapper (BD)
 * necesarios de Estadisticas
 * 
 * @author ramon
 *
 */
public class ClienteDAO {

	/**
	 * Método para insertar un cliente en BD
	 * 
	 * @param c
	 */
	public void insertarCliente(Cliente c) {

		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			ClientesMapper clientesMapper = sqlSession.getMapper(ClientesMapper.class);
			// Insertamos el usuario
			clientesMapper.insertarCliente(c);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para obtner un cliente a través de su email
	 * 
	 * @param email
	 * @return
	 */
	public Cliente getClienteEmail(String email) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			ClientesMapper clientesMapper = sqlSession.getMapper(ClientesMapper.class);
			// Insertamos el usuario
			return clientesMapper.getClienteEmail(email);

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}
	
	/**
	 * Método para activar un cliente en BD
	 * @param id_cliente Recibe la id de un cliente
	 */
	public void activarCliente(Integer id_cliente) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			ClientesMapper clientesMapper = sqlSession.getMapper(ClientesMapper.class);
			// Insertamos el usuario
			clientesMapper.activarCliente(id_cliente);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

}
