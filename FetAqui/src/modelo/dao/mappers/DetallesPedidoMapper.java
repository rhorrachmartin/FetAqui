package modelo.dao.mappers;

import modelo.pojo.DetallePedido;

/**
 * Interfaz mapper para manejar los mapper de pedidos detallados
 * 
 * @author ramon
 *
 */
public interface DetallesPedidoMapper {

	/**
	 * Método para insertar los detalles de un pedido
	 * 
	 * @param detallePedido Recibe pojo DetallePedido
	 */
	public void insertarDetallePedido(DetallePedido detallePedido);

}
