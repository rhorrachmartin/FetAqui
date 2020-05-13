package modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.PedidoDAO;
import modelo.pojo.Pedido;

@Stateless
@LocalBean
public class PedidoEJB {

	public void insertarPedido(Pedido pedido) {
		PedidoDAO pedidoDAO = new PedidoDAO();

		pedidoDAO.insertarPedido(pedido);
	}

	public int getNumeroProductos(Integer id_pedido) {
		PedidoDAO pedidoDAO = new PedidoDAO();

		return pedidoDAO.getNumeroProductos(id_pedido);
	}

}
