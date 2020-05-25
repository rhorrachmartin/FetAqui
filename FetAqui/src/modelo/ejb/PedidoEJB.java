package modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.PedidoDAO;
import modelo.pojo.Pedido;
import modelo.pojo.PedidoDetallado;
import modelo.pojo.Vendedor;

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
	
	public void borrarPedidoPorId(Integer id_pedido) {
		PedidoDAO pedidoDAO = new PedidoDAO();

		pedidoDAO.borrarPedidoPorId(id_pedido);
	}

	public int getNumeroProductos(Integer id_pedido) {
		PedidoDAO pedidoDAO = new PedidoDAO();

		return pedidoDAO.getNumeroProductos(id_pedido);
	}

	public ArrayList<PedidoDetallado> getPedidoDetalladoPorId(Integer id_pedido) {
		PedidoDAO pedidoDAO = new PedidoDAO();

		return pedidoDAO.getPedidoDetalladoPorId(id_pedido);
	}

	public ArrayList<PedidoDetallado> getPedidoDetalladoPorIdVendedorYpedido(Integer id_vendedor, Integer id_pedido) {
		PedidoDAO pedidoDAO = new PedidoDAO();

		return pedidoDAO.getPedidoDetalladoPorIdVendedorYpedido(id_vendedor, id_pedido);
	}

	public void updatePedidoAconfirmado(Integer id_pedido) {
		PedidoDAO pedidoDAO = new PedidoDAO();

		pedidoDAO.updatePedidoAconfirmado(id_pedido);
	}

	public void updatePedidoApendiente(Integer id_pedido) {
		PedidoDAO pedidoDAO = new PedidoDAO();

		pedidoDAO.updatePedidoApendiente(id_pedido);
	}
	
	public ArrayList<Vendedor> getVendedoresPorPedido(Integer id_pedido){
		PedidoDAO pedidoDAO = new PedidoDAO();

		return pedidoDAO.getVendedoresPorPedido(id_pedido);
	}
	
	public ArrayList<Pedido> getPedidosCliente(Integer id_cliente){
		PedidoDAO pedidoDAO = new PedidoDAO();

		return pedidoDAO.getPedidosCliente(id_cliente);
	}
	
	public ArrayList<Pedido> getPedidosVendedor(Integer id_vendedor){
		PedidoDAO pedidoDAO = new PedidoDAO();

		return pedidoDAO.getPedidosVendedor(id_vendedor);
	}

}
