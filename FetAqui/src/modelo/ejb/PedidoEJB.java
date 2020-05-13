package modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.PedidoDAO;
import modelo.pojo.Pedido;
import modelo.pojo.PedidoDetallado;

@Stateless
@LocalBean
public class PedidoEJB {

	public void insertarPedido(Pedido pedido) {
		PedidoDAO pedidoDAO = new PedidoDAO();

		pedidoDAO.insertarPedido(pedido);
	}
	
	public void borrarProductoCesta(Integer id_detalle) {
		PedidoDAO pedidoDAO = new PedidoDAO();
		
		pedidoDAO.borrarProductoCesta(id_detalle);
	}

	public int getNumeroProductos(Integer id_pedido) {
		PedidoDAO pedidoDAO = new PedidoDAO();

		return pedidoDAO.getNumeroProductos(id_pedido);
	}

	public ArrayList<PedidoDetallado> getPedidoDetalladoPorId(Integer id_pedido) {
		PedidoDAO pedidoDAO = new PedidoDAO();

		return pedidoDAO.getPedidoDetalladoPorId(id_pedido);
	}

}
