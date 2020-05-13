package modelo.dao.mappers;


import org.apache.ibatis.annotations.Param;

import modelo.pojo.Pedido;
import modelo.pojo.PedidoDetallado;

/**
 * Interfaz mapper para manejar los mapper de Poblaciones
 * 
 * @author ramon
 *
 */
public interface PedidosMapper {
	
	public void insertarPedido(Pedido pedido);
	
	public int getNumeroProductos(@Param("id_pedido") Integer id_pedido);
	
	public PedidoDetallado getPedidoDetalladoPorId(@Param("id_pedido") Integer id_pedido);

}
