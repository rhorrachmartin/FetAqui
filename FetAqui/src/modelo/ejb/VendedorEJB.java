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
	
	public boolean comprobarMailVendedor(String email) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		
		return vendedorDAO.comprobarMailVendedor(email);
	}
	
	
	public Vendedor getVendedorEmailPass(String email, String password) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		
		return vendedorDAO.getVendedorEmailPass(email, password);
	}
}
