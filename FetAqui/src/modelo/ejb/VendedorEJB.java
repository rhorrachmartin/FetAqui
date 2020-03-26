package modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.VendedorDAO;
import modelo.pojo.Vendedor;

@Stateless
@LocalBean
public class VendedorEJB {

	public void insertarVendedor(Vendedor v) {

		VendedorDAO vendedorDAO = new VendedorDAO();

		vendedorDAO.insertVendedor(v);
	};

	public Vendedor getVendedorEmail(String email) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		return vendedorDAO.getVendedorEmail(email);
	}

	public Vendedor getVendedorEmailPass(String email, String password) {
		VendedorDAO vendedorDAO = new VendedorDAO();

		return vendedorDAO.getVendedorEmailPass(email, password);
	}
	
	/**
	 * Método para activar un vendedor
	 * @param v recibe un pojo vendedor
	 */
	public void activarVendedor(Integer id_vendedor) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		
		vendedorDAO.activarVendedor(id_vendedor);
	}
}
