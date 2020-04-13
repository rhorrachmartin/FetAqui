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
	
	public void activarVentaOnline(Integer id_vendedor) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		
		vendedorDAO.activarVentaOnline(id_vendedor);
	}
	
	public void desactivarVentaOnline(Integer id_vendedor) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		
		vendedorDAO.desactivarVentaOnline(id_vendedor);
	}
	
	
	public Vendedor getVendedor(String email, String password) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		return vendedorDAO.getVendedor(email, password);
	}
	
	public Vendedor getVendedorPorId(Integer id_vendedor) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		return vendedorDAO.getVendedorPorId(id_vendedor);
	}
	
	public void updateNif(String nif, Integer id_vendedor) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		vendedorDAO.updateNif(nif, id_vendedor);
	}
	
	public void updateNombre(String nombre, Integer id_vendedor) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		vendedorDAO.updateNombre(nombre, id_vendedor);
	}
	
	
	public void updateTelefono(String telefono, Integer id_vendedor) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		vendedorDAO.updateTelefono(telefono, id_vendedor);
	}
	
	
	public void updatePassword(String password, Integer id_vendedor) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		vendedorDAO.updatePassword(password, id_vendedor);
	}
	
	public void updateFoto(String foto, Integer id_vendedor) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		vendedorDAO.updateFoto(foto, id_vendedor);
	}
	
	
	public void updateDireccion(Integer id_vendedor) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		vendedorDAO.updateDireccion(id_vendedor);
	}
	
	
	public void bajaVendedor(Integer id_vendedor) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		vendedorDAO.bajaVendedor(id_vendedor);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
