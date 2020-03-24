package modelo.dao;

import java.sql.SQLException;

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

	public boolean comprobarMailVendedor(String email) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			Vendedor v = null;
			boolean existe;
			// Obtenemos el mapper
			VendedoresMapper vendedoresMapper = sqlSession.getMapper(VendedoresMapper.class);
			// Insertamos el usuario
			v = vendedoresMapper.comprobarMailVendedor(email);

			if (v != null) {
				existe = false;
			} else {
				existe = true;
			}
			return existe;

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

}
