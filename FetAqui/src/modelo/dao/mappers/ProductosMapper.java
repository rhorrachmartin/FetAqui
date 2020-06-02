package modelo.dao.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import modelo.pojo.Producto;

/**
 * Interfaz mapper para manejar los mapper de Productos
 * 
 * @author ramon
 *
 */
public interface ProductosMapper {

	/**
	 * Método para insertar un Producto en BD
	 * 
	 * @param producto Recibe un pojo Producto
	 * @return Devuelve la id del producto insertado
	 */
	public Integer insertarProducto(Producto producto);

	/**
	 * Método para obtener todos los productos de un vendedor
	 * 
	 * @param id_vendedor Recibe el id del vendedor
	 * @return Devuelve arraylist de Producto
	 */
	public ArrayList<Producto> getProductosVendedor(Integer id_vendedor);

	/**
	 * Método para obtener todos los productos de un vendedor filtrados por
	 * categoria
	 * 
	 * @param id_vendedor  Recibe la id del vendedor
	 * @param id_categoria Recibe la id de la categoria
	 * @return Devuelve arraylist de producto
	 */
	public ArrayList<Producto> getProductosVendedorCategoria(@Param("id_vendedor") Integer id_vendedor,
			@Param("id_categoria") Integer id_categoria);

	/**
	 * Método para obtener todos los productos de una categoria
	 * 
	 * @param id_categoria Recibe la id de la categoria
	 * @return Devuelve arraylist de Producto
	 */
	public ArrayList<Producto> getProductosCategoria(@Param("id_categoria") Integer id_categoria);

	/**
	 * Método para activar la venta online de un producto
	 * 
	 * @param id_producto Recibe la id del producto
	 */
	public void activarVentaOnline(Integer id_producto);

	/**
	 * Método par desactivar la venta online de un producto
	 * 
	 * @param id_producto Recibe la id del producto
	 */
	public void desactivarVentaOnline(Integer id_producto);

	/**
	 * Método para obtener un producto a través de su id
	 * 
	 * @param id_producto Recibe la id del producto
	 * @return Devuelve un pojo Producto
	 */
	public Producto getProductoPorId(Integer id_producto);

	/**
	 * Método para borrar un producto de la BD
	 * 
	 * @param id_producto Recibe la id del producto
	 */
	public void borrarProducto(Integer id_producto);

	/**
	 * Método para actualizar un producto
	 * 
	 * @param producto Recibe un pojo Producto
	 */
	public void actualizarProducto(Producto producto);

	/**
	 * Método para actualizar la imagen de un producto
	 * 
	 * @param producto Recibe un pojo Producto
	 */
	public void actualizarImagenProducto(Producto producto);

	/**
	 * Método para obtener todos los productos
	 * 
	 * @return Devuelve un arraylist de Producto
	 */
	public ArrayList<Producto> getProductos();

}
