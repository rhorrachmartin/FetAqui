package modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.ValoracionProductoDAO;
import modelo.pojo.ValoracionProducto;

@Stateless
@LocalBean
public class ValoracionProductoEJB {

	public void insertarValoracionProducto(ValoracionProducto valoracionProducto) {
		
		ValoracionProductoDAO valoracionProductoDAO = new ValoracionProductoDAO();
		
		valoracionProductoDAO.insertarValoracionProducto(valoracionProducto);
		
	}
	
	public void borrarValoracionCliente(Integer id_cliente) {
		
		ValoracionProductoDAO valoracionProductoDAO = new ValoracionProductoDAO();
		
		valoracionProductoDAO.borrarValoracionCliente(id_cliente);
		
	}

}
