package modelo.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.VendedoresMapper;
import modelo.pojo.Vendedor;

/**
 * Clase DAO con los métodos necesarios para acceder a los mapper (BD)
 * necesarios de Estadisticas
 * 
 * @author ramon
 *
 */
public class VendedorDAO {

	/**
	 * Método para insetar un usuario vendedor en BD
	 * 
	 * @param v recibe un pojo vendedor
	 * @throws SQLException
	 * @throws NamingException
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
	
	public void updateNif(String nif,Integer id_vendedor) {
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
