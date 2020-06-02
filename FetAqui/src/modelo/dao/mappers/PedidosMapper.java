package modelo.dao.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import modelo.pojo.Pedido;
import modelo.pojo.PedidoDetallado;
import modelo.pojo.Vendedor;

/**
 * Interfaz mapper para manejar los mapper de Pedido
 * 
 * @author ramon
 *
 */
public interface PedidosMapper {

	/**
	 * Método para insertar un pedido en BD
	 * 
	 * @param pedido Recibe pojo Pedido
	 */
	public void insertarPedido(Pedido pedido);

	/**
	 * Método para obtener la cantidad de productos de un pedido
	 * 
	 * @param id_pedido Recibe la id del pedido
	 * @return Devuelve la cantidad de productos
	 */
	public int getNumeroProductos(@Param("id_pedido") Integer id_pedido);

	/**
	 * Método para obtener un pedido detallado en función de su ID
	 * 
	 * @param id_pedido
	 * @return Devuelve un arraylist de PedidoDetallado
	 */
	public ArrayList<PedidoDetallado> getPedidoDetalladoPorId(@Param("id_pedido") Integer id_pedido);

	/**
	 * Método para obtener un pedido detallado de un pedido y vendedor concretos
	 * 
	 * @param id_vendedor Recibe la id del vendedor
	 * @param id_pedido   Recibe la id del pedido
	 * @return Devuelve un araylist de PedidoDetallado
	 */
	public ArrayList<PedidoDetallado> getPedidoDetalladoPorIdVendedorYpedido(@Param("id_vendedor") Integer id_vendedor,
			@Param("id_pedido") Integer id_pedido);

	/**
	 * Método para borrar un producto de la cesta (Pedido)
	 * 
	 * @param id_detalle Recibe la id del detalle del pedido
	 */
	public void borrarProductoCesta(@Param("id_detalle") Integer id_detalle);

	/**
	 * Método para borrar un pedido de la BD
	 * 
	 * @param id_pedido Recibe la id del pedido
	 */
	public void borrarPedidoPorId(@Param("id_pedido") Integer id_pedido);

	/**
	 * Método para pasar un pedido a confirmado
	 * 
	 * @param id_pedido Recibe la id del pedido
	 */
	public void updatePedidoAconfirmado(@Param("id_pedido") Integer id_pedido);

	/**
	 * Método para pasar un pedido a pendiente
	 * 
	 * @param id_pedido Recibe la id del pedido
	 */
	public void updatePedidoApendiente(@Param("id_pedido") Integer id_pedido);

	/**
	 * Método para obtener los diferentes vendedores que hay en un mismo pedido
	 * 
	 * @param id_pedido Recibe la id del pedido
	 * @return Devuelve un arraylist de Vendedor
	 */
	public ArrayList<Vendedor> getVendedoresPorPedido(@Param("id_pedido") Integer id_pedido);

	/**
	 * Método para obtener todos los pedidos de un cliente
	 * 
	 * @param id_cliente Recibe la id del cliente
	 * @return Devuelve un arraylist de Pedido
	 */
	public ArrayList<Pedido> getPedidosCliente(@Param("id_cliente") Integer id_cliente);

	/**
	 * Método para obtener todos los pedidos que ha recibido un vendedor
	 * 
	 * @param id_vendedor Recibe la id del vendedor
	 * @return Devuelve un arraylist de Pedido
	 */
	public ArrayList<Pedido> getPedidosVendedor(@Param("id_vendedor") Integer id_vendedor);

}
