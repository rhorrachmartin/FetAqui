package modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.VendedoresMapper;
import modelo.pojo.Vendedor;

/**
 * Método para manejar a los Vendedores
 * 
 * @author ramon
 *
 */
public class VendedorDAO {

	/**
	 * Método para insertar un usuario Vendedor en la BD
	 * 
	 * @param v Recibe un pojo Vendedor
	 */
	public void insertVendedor(Vendedor v) {

		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			VendedoresMapper vendedoresMapper = sqlSession.getMapper(VendedoresMapper.class);
			// Insertamos el usuario
			vendedoresMapper.insertarVendedor(v);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para activar un vendedor
	 * 
	 * @param id_vendedor recibe el id del vendedor
	 */
	public void activarVendedor(Integer id_vendedor) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			VendedoresMapper vendedoresMapper = sqlSession.getMapper(VendedoresMapper.class);
			// Insertamos el usuario
			vendedoresMapper.activarVendedor(id_vendedor);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para activar la venta online de un vendedor
	 * 
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void activarVentaOnline(Integer id_vendedor) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			VendedoresMapper vendedoresMapper = sqlSession.getMapper(VendedoresMapper.class);
			// Insertamos el usuario
			vendedoresMapper.activarVentaOnline(id_vendedor);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para desactivar la venta online de un vendedor
	 * 
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void desactivarVentaOnline(Integer id_vendedor) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			VendedoresMapper vendedoresMapper = sqlSession.getMapper(VendedoresMapper.class);
			// Insertamos el usuario
			vendedoresMapper.desactivarVentaOnline(id_vendedor);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para obtener un vendedor a través de su email
	 * 
	 * @param email Recibe el email del vendedor
	 * @return Devuelve un pojo Vendedor
	 */
	public Vendedor getVendedorEmail(String email) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			VendedoresMapper vendedoresMapper = sqlSession.getMapper(VendedoresMapper.class);
			// Insertamos el usuario
			return vendedoresMapper.getVendedorEmail(email);

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para obtener un vendedor a través de su id
	 * 
	 * @param id_vendedor Recibe la id del vendedor
	 * @return Devuelve un pojo Vendedor
	 */
	public Vendedor getVendedorPorId(Integer id_vendedor) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			VendedoresMapper vendedoresMapper = sqlSession.getMapper(VendedoresMapper.class);
			// Insertamos el usuario
			return vendedoresMapper.getVendedorPorId(id_vendedor);

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para obtener todos los vendedores de una Poblacion
	 * 
	 * @param id_poblacion REcibe la id de la poblacion
	 * @return Devuelve un arraylist de poblacion
	 */
	public ArrayList<Vendedor> getVendedoresPoblacion(Integer id_poblacion) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			VendedoresMapper vendedoresMapper = sqlSession.getMapper(VendedoresMapper.class);
			// Insertamos el usuario
			return vendedoresMapper.getVendedoresPoblacion(id_poblacion);

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para obtener todos los vendedores
	 * 
	 * @return Devuelve un arraylist de Vendedor
	 */
	public ArrayList<Vendedor> getVendedores() {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			VendedoresMapper vendedoresMapper = sqlSession.getMapper(VendedoresMapper.class);
			// Insertamos el usuario
			return vendedoresMapper.getVendedores();

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para obtener un vendedor a través de su email y password
	 * 
	 * @param email
	 * @param password
	 * @return Devuelve un pojo Vendedor
	 */
	public Vendedor getVendedorEmailPass(String email, String password) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			VendedoresMapper vendedoresMapper = sqlSession.getMapper(VendedoresMapper.class);
			// Insertamos el usuario
			return vendedoresMapper.getVendedorEmailPass(email, password);

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para obtener un vendedor a través de su email y password
	 * 
	 * @param email
	 * @param password
	 * @return Devuelve un pojo Vendedor
	 */
	public Vendedor getVendedor(String email, String password) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			VendedoresMapper vendedoresMapper = sqlSession.getMapper(VendedoresMapper.class);
			// Insertamos el usuario
			return vendedoresMapper.getVendedor(email, password);

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para actualizar el nombre de un vendedor
	 * 
	 * @param nombre      Recibe el nombre
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void updateNombre(String nombre, Integer id_vendedor) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			VendedoresMapper vendedoresMapper = sqlSession.getMapper(VendedoresMapper.class);
			// Insertamos el usuario
			vendedoresMapper.updateNombre(nombre, id_vendedor);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para actualizar el teléfono de un vendedor
	 * 
	 * @param telefono    Recibe el teléfono
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void updateTelefono(String telefono, Integer id_vendedor) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			VendedoresMapper vendedoresMapper = sqlSession.getMapper(VendedoresMapper.class);
			// Insertamos el usuario
			vendedoresMapper.updateTelefono(telefono, id_vendedor);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para actualizar el password de un vendedor
	 * 
	 * @param password    Recibe el password
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void updatePassword(String password, Integer id_vendedor) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			VendedoresMapper vendedoresMapper = sqlSession.getMapper(VendedoresMapper.class);
			// Insertamos el usuario
			vendedoresMapper.updatePassword(password, id_vendedor);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para actualizar la foto de un vendedor
	 * 
	 * @param foto        Recibe la foto
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void updateFoto(String foto, Integer id_vendedor) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			VendedoresMapper vendedoresMapper = sqlSession.getMapper(VendedoresMapper.class);
			// Insertamos el usuario
			vendedoresMapper.updateFoto(foto, id_vendedor);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para actualizar la dirección de un vendedor
	 * 
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void updateDireccion(Integer id_vendedor) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			VendedoresMapper vendedoresMapper = sqlSession.getMapper(VendedoresMapper.class);
			// Insertamos el usuario
			vendedoresMapper.updateDireccion(id_vendedor);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para actualizar el nif del vendedor
	 * 
	 * @param nif         Recibe el NIF
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void updateNif(String nif, Integer id_vendedor) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			VendedoresMapper vendedoresMapper = sqlSession.getMapper(VendedoresMapper.class);
			// Insertamos el usuario
			vendedoresMapper.updateNif(nif, id_vendedor);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para borrar un vendedor de la BD
	 * 
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void bajaVendedor(Integer id_vendedor) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			VendedoresMapper vendedoresMapper = sqlSession.getMapper(VendedoresMapper.class);
			// Insertamos el usuario
			vendedoresMapper.bajaVendedor(id_vendedor);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

}
