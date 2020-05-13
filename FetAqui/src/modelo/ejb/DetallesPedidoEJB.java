package modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.DetallePedidoDAO;
import modelo.pojo.DetallePedido;

@Stateless
@LocalBean
public class DetallesPedidoEJB {

	public void insertarDetallePedido(DetallePedido detallePedido) {
		DetallePedidoDAO detallePedidoDAO = new DetallePedidoDAO();

		detallePedidoDAO.insertarDetallePedido(detallePedido);
	}

}
