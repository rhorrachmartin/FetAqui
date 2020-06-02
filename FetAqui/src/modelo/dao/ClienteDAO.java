package modelo.dao;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.ClientesMapper;
import modelo.pojo.Cliente;

/**
 * Clase DAO para manejar los datos de los usuarios Cliente
 * 
 * @author ramon
 *
 */
public class ClienteDAO {

	/**
	 * Método para insertar un cliente en BD
	 * 
	 * @param c REcibe un pojo Cliente
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
	 * @param email Recibe el email del Cliente
	 * @return devuelve un pojo Cliente
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
	 * Método para obtener un cliente por su id
	 * 
	 * @param id_cliente Recibe la id de un cliente
	 * @return Devuelve un pojo Cliente
	 */
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
	 * @param email    Recibe su email y password
	 * @param password
	 * @return Devuelve un pojo Cliente
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

	/**
	 * Método para obtener un cliente a través de su email y su password
	 * 
	 * @param email    Recibe su email y password
	 * @param password
	 * @return Devuelve un pojo Cliente
	 */
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

	/**
	 * Método para actualizar el nombre de un cliente
	 * 
	 * @param nombre     Recibe su nombre
	 * @param id_cliente Recibe su id
	 */
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

	/**
	 * Método para actualizar el apellido de un cliente
	 * 
	 * @param apellido   Recibe su apellido
	 * @param id_cliente Recibe su id
	 */
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

	/**
	 * Método para actualizar el teléfono de un cliente
	 * 
	 * @param telefono   Recibe el num de teléfono
	 * @param id_cliente Recibe la id del cliente
	 */
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

	/**
	 * Método para actualizar el password de un cliente
	 * 
	 * @param password   Recibe el password
	 * @param id_cliente REcibe la id del cliente
	 */
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

	/**
	 * Método para actualizar la foto del cliente
	 * 
	 * @param foto       Recibe la foto
	 * @param id_cliente REcibe la id del cliente
	 */
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

	/**
	 * Método para actualizar la dirección del cliente
	 * 
	 * @param id_cliente Recibe la id del cliente
	 */
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

	/**
	 * Método para borrar un cliente de la BD
	 * 
	 * @param id_cliente REcibe la id del cliente
	 */
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
