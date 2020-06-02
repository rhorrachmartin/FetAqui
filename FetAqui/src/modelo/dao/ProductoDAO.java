package modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.ProductosMapper;
import modelo.pojo.Producto;

/**
 * Clase DAO para manejar los Productos
 * 
 * @author ramon
 *
 */
public class ProductoDAO {

	/**
	 * Método para obtener todos los productos de una categoria
	 * 
	 * @param id_categoria Recibe la id de la categoria
	 * @return Devuelve arraylist de Producto
	 */
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

	/**
	 * Método para insertar un Producto en BD
	 * 
	 * @param producto Recibe un pojo Producto
	 * @return Devuelve la id del producto insertado
	 */
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

	/**
	 * Método para obtener todos los productos de un vendedor
	 * 
	 * @param id_vendedor Recibe el id del vendedor
	 * @return Devuelve arraylist de Producto
	 */
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

	/**
	 * Método para obtener todos los productos de un vendedor filtrados por
	 * categoria
	 * 
	 * @param id_vendedor  Recibe la id del vendedor
	 * @param id_categoria Recibe la id de la categoria
	 * @return Devuelve arraylist de producto
	 */
	public ArrayList<Producto> getProductosVendedorCategoria(Integer id_vendedor, Integer id_categoria) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			ProductosMapper productosMapper = sqlSession.getMapper(ProductosMapper.class);
			// Devolvemos el arraylist de poblacion
			return productosMapper.getProductosVendedorCategoria(id_vendedor, id_categoria);

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}

	}

	/**
	 * Método para obtener un producto a través de su id
	 * 
	 * @param id_producto Recibe la id del producto
	 * @return Devuelve un pojo Producto
	 */
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

	/**
	 * Método para activar la venta online de un producto
	 * 
	 * @param id_producto Recibe la id del producto
	 */
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

	/**
	 * Método par desactivar la venta online de un producto
	 * 
	 * @param id_producto Recibe la id del producto
	 */
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

	/**
	 * Método para borrar un producto de la BD
	 * 
	 * @param id_producto Recibe la id del producto
	 */
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

	/**
	 * Método para actualizar un producto
	 * 
	 * @param producto Recibe un pojo Producto
	 */
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

	/**
	 * Método para actualizar la imagen de un producto
	 * 
	 * @param producto Recibe un pojo Producto
	 */
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

	/**
	 * Método para obtener todos los productos
	 * 
	 * @return Devuelve un arraylist de Producto
	 */
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
