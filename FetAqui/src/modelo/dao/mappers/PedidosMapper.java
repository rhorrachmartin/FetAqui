package modelo.dao.mappers;


import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import modelo.pojo.Pedido;
import modelo.pojo.PedidoDetallado;
import modelo.pojo.Vendedor;

/**
 * Interfaz mapper para manejar los mapper de Poblaciones
 * 
 * @author ramon
 *
 */
public interface PedidosMapper {
	
	public void insertarPedido(Pedido pedido);
	
	public int getNumeroProductos(@Param("id_pedido") Integer id_pedido);
	
	public ArrayList<PedidoDetallado> getPedidoDetalladoPorId(@Param("id_pedido") Integer id_pedido);
	
	public ArrayList<PedidoDetallado> getPedidoDetalladoPorIdVendedorYpedido(@Param("id_vendedor") Integer id_vendedor, @Param("id_pedido") Integer id_pedido);
	
	public void borrarProductoCesta(@Param("id_detalle") Integer id_detalle);
	
	public void updatePedidoAconfirmado(@Param("id_pedido") Integer id_pedido);
	
	public void updatePedidoApendiente(@Param("id_pedido") Integer id_pedido);
	
	public ArrayList<Vendedor> getVendedoresPorPedido(@Param("id_pedido") Integer id_pedido);
	
	public ArrayList<Pedido> getPedidosCliente(@Param("id_cliente") Integer id_cliente);
	

}
