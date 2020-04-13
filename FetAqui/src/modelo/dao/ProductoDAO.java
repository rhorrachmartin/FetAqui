package modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.ProductosMapper;
import modelo.pojo.Producto;

/**
 * Clase DAO con los métodos necesarios para acceder a los mapper (BD)
 * necesarios de Estadisticas
 * 
 * @author ramon
 *
 */
public class ProductoDAO {

	public ArrayList<Producto> getProductosBusqueda(String busqueda) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
			// Devolvemos el arraylist de poblacion
			return productosMapper.getProductosBusqueda(busqueda);

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}
	
	public ArrayList<Producto> getProductosCategoria(Integer id_categoria) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
			// Devolvemos el arraylist de poblacion
			return productosMapper.getProductosCategoria(id_categoria);

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	public Integer insertarProducto(Producto producto) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		Integer id_producto = 0;
		try {
			// Obtenemos el mapper
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);

			id_producto = productosMapper.insertarProducto(producto);

			sqlSession.commit();
			
			return id_producto;

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	public ArrayList<Producto> getProductosVendedor(Integer id_vendedor) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
			// Devolvemos el arraylist de poblacion
			return productosMapper.getProductosVendedor(id_vendedor);

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}

	}

	public Producto getProductoPorId(Integer id_producto) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
			// Devolvemos el arraylist de poblacion
			return productosMapper.getProductoPorId(id_producto);

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}

	}

	public void activarVentaOnline(Integer id_producto) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);

			productosMapper.activarVentaOnline(id_producto);

			sqlSession.commit();

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	public void desactivarVentaOnline(Integer id_producto) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);

			productosMapper.desactivarVentaOnline(id_producto);

			sqlSession.commit();

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	public void borrarProducto(Integer id_producto) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);

			productosMapper.borrarProducto(id_producto);

			sqlSession.commit();

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	public void actualizarProducto(Producto producto) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);

			productosMapper.actualizarProducto(producto);

			sqlSession.commit();

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	public void actualizarImagenProducto(Producto producto) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);

			productosMapper.actualizarImagenProducto(producto);

			sqlSession.commit();

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	public ArrayList<Producto> getProductos() {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
			// Devolvemos el arraylist de poblacion
			return productosMapper.getProductos();

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

}
