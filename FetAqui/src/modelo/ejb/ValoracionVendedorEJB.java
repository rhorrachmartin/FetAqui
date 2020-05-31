package modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.ValoracionVendedorDAO;
import modelo.pojo.ValoracionCv;

@Stateless
@LocalBean
public class ValoracionVendedorEJB {

	public void insertarValoracionVendedor(ValoracionCv valoracionCv) {
		ValoracionVendedorDAO valoracionVendedorDAO = new ValoracionVendedorDAO();

		valoracionVendedorDAO.insertarValoracionVendedor(valoracionCv);
	}
	
	public void borrarValoracionCliente(Integer id_cliente) {
		ValoracionVendedorDAO valoracionVendedorDAO = new ValoracionVendedorDAO();
		
		valoracionVendedorDAO.borrarValoracionCliente(id_cliente);
	}

}
