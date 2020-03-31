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
	
	public Cliente getClientePorId(Integer id_cliente) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			ClientesMapper clientesMapper = sqlSession.getMapper(ClientesMapper.class);
			// Insertamos el usuario
			return clientesMapper.getClientePorId(id_cliente);

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para obtener un cliente a través de su email y su password
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	public Cliente getClienteEmailPass(String email, String password) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			ClientesMapper clientesMapper = sqlSession.getMapper(ClientesMapper.class);
			// Insertamos el usuario
			return clientesMapper.getClienteEmailPass(email, password);

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}
	
	public Cliente getCliente(String email, String password) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			ClientesMapper clientesMapper = sqlSession.getMapper(ClientesMapper.class);
			// Insertamos el usuario
			return clientesMapper.getCliente(email, password);

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para activar un cliente en BD
	 * 
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
	
	public void updateNombre(String nombre, Integer id_cliente) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			ClientesMapper clientesMapper = sqlSession.getMapper(ClientesMapper.class);
			// Insertamos el usuario
			clientesMapper.updateNombre(nombre, id_cliente);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	public void updateApellido(String apellido, Integer id_cliente) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			ClientesMapper clientesMapper = sqlSession.getMapper(ClientesMapper.class);
			// Insertamos el usuario
			clientesMapper.updateApellido(apellido, id_cliente);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	public void updateTelf(String telefono, Integer id_cliente) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			ClientesMapper clientesMapper = sqlSession.getMapper(ClientesMapper.class);
			// Insertamos el usuario
			clientesMapper.updateTelf(telefono, id_cliente);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	public void updatePassword(String password, Integer id_cliente) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			ClientesMapper clientesMapper = sqlSession.getMapper(ClientesMapper.class);
			// Insertamos el usuario
			clientesMapper.updatePassword(password, id_cliente);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	public void updateFoto(String foto, Integer id_cliente) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			ClientesMapper clientesMapper = sqlSession.getMapper(ClientesMapper.class);
			// Insertamos el usuario
			clientesMapper.updateFoto(foto, id_cliente);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}
	
	public void updateDireccion(Integer id_cliente) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			ClientesMapper clientesMapper = sqlSession.getMapper(ClientesMapper.class);
			// Insertamos el usuario
			clientesMapper.updateDireccion(id_cliente);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	public void bajaCliente(Integer id_cliente) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			ClientesMapper clientesMapper = sqlSession.getMapper(ClientesMapper.class);
			// Insertamos el usuario
			clientesMapper.bajaCliente(id_cliente);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

}
