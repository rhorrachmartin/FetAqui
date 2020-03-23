package modelo.dao.mappers;


import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import modelo.pojo.PedidoDetallado;
import modelo.pojo.Producto;
import modelo.pojo.Vendedor;

/**
 * Interfaz mapper para manejar los mapper de Direcciones
 * 
 * @author ramon
 *
 */
public interface VendedoresMapper {
	
	/**
	 * Método para obtener los detalles de un vendedor
	 * @param id_vendedor recibe la id del vendedor
	 * @return devuelve un pojo vendedor
	 */
	public Vendedor getVendedor(@Param("id_vendedor") Integer id_vendedor);

	/**
	 * Método para obtener las direcciones de una población
	 * @return ArrayList<Direccion> 
	 */
	public ArrayList<Vendedor> getVendedores();
	
	/**
	 * Método para obtener los productos de un vendedor
	 * @return ArrayList<Producto> 
	 */
	public ArrayList<Producto> getProductosVendedor(@Param("id_vendedor") Integer id_vendedor);
	
	/**
	 * Método para obtener un producto de un vendedor en concreto
	 * @param id_vendedor recibe id_vendedor
	 * @param id_producto recibe la id_producto
	 * @return devuelve un pojo Producto
	 */
	public Producto getProductoVendedor(@Param("id_vendedor") Integer id_vendedor, @Param("id_producto") Integer id_producto);
	
	/**
	 * Método para obtener todos los pedidos detallados de un vendedor
	 * @param id_vendedor recibe la id del vendedor
	 * @return devuelve un arraylist de PedidoDetallado
	 */
	public ArrayList<PedidoDetallado> getPedidosDetallados(@Param("id_vendedor") Integer id_vendedor);

}
