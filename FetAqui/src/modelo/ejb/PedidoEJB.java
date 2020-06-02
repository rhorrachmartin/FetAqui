package modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.PedidoDAO;
import modelo.pojo.Pedido;
import modelo.pojo.PedidoDetallado;
import modelo.pojo.Vendedor;

/**
 * Clase EJB para la llamada al DAO de Pedido
 * @author ramon
 *
 */

@Stateless
@LocalBean
public class PedidoEJB {
	
	/**
	 * Método para insertar un pedido en BD
	 * 
	 * @param pedido Recibe pojo Pedido
	 */
	public void insertarPedido(Pedido pedido) {
		PedidoDAO pedidoDAO = new PedidoDAO();

		pedidoDAO.insertarPedido(pedido);
	}
	
	/**
	 * Método para borrar un pedido de la BD
	 * 
	 * @param id_pedido Recibe la id del pedido
	 */
	public void borrarProductoCesta(Integer id_detalle) {
		PedidoDAO pedidoDAO = new PedidoDAO();

		pedidoDAO.borrarProductoCesta(id_detalle);
	}
	
	/**
	 * Método para borrar un producto de la cesta (Pedido)
	 * 
	 * @param id_detalle Recibe la id del detalle del pedido
	 */
	public void borrarPedidoPorId(Integer id_pedido) {
		PedidoDAO pedidoDAO = new PedidoDAO();

		pedidoDAO.borrarPedidoPorId(id_pedido);
	}
	
	/**
	 * Método para obtener la cantidad de productos de un pedido
	 * 
	 * @param id_pedido Recibe la id del pedido
	 * @return Devuelve la cantidad de productos
	 */
	public int getNumeroProductos(Integer id_pedido) {
		PedidoDAO pedidoDAO = new PedidoDAO();

		return pedidoDAO.getNumeroProductos(id_pedido);
	}
	
	/**
	 * Método para obtener un pedido detallado en función de su ID
	 * 
	 * @param id_pedido
	 * @return Devuelve un arraylist de PedidoDetallado
	 */
	public ArrayList<PedidoDetallado> getPedidoDetalladoPorId(Integer id_pedido) {
		PedidoDAO pedidoDAO = new PedidoDAO();

		return pedidoDAO.getPedidoDetalladoPorId(id_pedido);
	}
	
	/**
	 * Método para obtener un pedido detallado de un pedido y vendedor concretos
	 * 
	 * @param id_vendedor Recibe la id del vendedor
	 * @param id_pedido   Recibe la id del pedido
	 * @return Devuelve un araylist de PedidoDetallado
	 */
	public ArrayList<PedidoDetallado> getPedidoDetalladoPorIdVendedorYpedido(Integer id_vendedor, Integer id_pedido) {
		PedidoDAO pedidoDAO = new PedidoDAO();

		return pedidoDAO.getPedidoDetalladoPorIdVendedorYpedido(id_vendedor, id_pedido);
	}
	
	/**
	 * Método para pasar un pedido a confirmado
	 * 
	 * @param id_pedido Recibe la id del pedido
	 */
	public void updatePedidoAconfirmado(Integer id_pedido) {
		PedidoDAO pedidoDAO = new PedidoDAO();

		pedidoDAO.updatePedidoAconfirmado(id_pedido);
	}
	
	/**
	 * Método para pasar un pedido a pendiente
	 * 
	 * @param id_pedido Recibe la id del pedido
	 */
	public void updatePedidoApendiente(Integer id_pedido) {
		PedidoDAO pedidoDAO = new PedidoDAO();

		pedidoDAO.updatePedidoApendiente(id_pedido);
	}
	
	/**
	 * Método para obtener los diferentes vendedores que hay en un mismo pedido
	 * 
	 * @param id_pedido Recibe la id del pedido
	 * @return Devuelve un arraylist de Vendedor
	 */
	public ArrayList<Vendedor> getVendedoresPorPedido(Integer id_pedido){
		PedidoDAO pedidoDAO = new PedidoDAO();

		return pedidoDAO.getVendedoresPorPedido(id_pedido);
	}
	
	/**
	 * Método para obtener todos los pedidos de un cliente
	 * 
	 * @param id_cliente Recibe la id del cliente
	 * @return Devuelve un arraylist de Pedido
	 */
	public ArrayList<Pedido> getPedidosCliente(Integer id_cliente){
		PedidoDAO pedidoDAO = new PedidoDAO();

		return pedidoDAO.getPedidosCliente(id_cliente);
	}
	
	/**
	 * Método para obtener todos los pedidos que ha recibido un vendedor
	 * 
	 * @param id_vendedor Recibe la id del vendedor
	 * @return Devuelve un arraylist de Pedido
	 */
	public ArrayList<Pedido> getPedidosVendedor(Integer id_vendedor){
		PedidoDAO pedidoDAO = new PedidoDAO();

		return pedidoDAO.getPedidosVendedor(id_vendedor);
	}

}
