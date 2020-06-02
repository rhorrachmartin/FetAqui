package modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.DetallePedidoDAO;
import modelo.pojo.DetallePedido;

/**
 * Clase EJB para la llamada al DAO de detalle pedido
 * 
 * @author ramon
 *
 */

@Stateless
@LocalBean
public class DetallesPedidoEJB {

	/**
	 * Método para insertar los detalles de un pedido
	 * 
	 * @param detallePedido Recibe pojo DetallePedido
	 */
	public void insertarDetallePedido(DetallePedido detallePedido) {
		DetallePedidoDAO detallePedidoDAO = new DetallePedidoDAO();

		detallePedidoDAO.insertarDetallePedido(detallePedido);
	}

}
