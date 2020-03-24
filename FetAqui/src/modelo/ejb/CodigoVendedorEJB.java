package modelo.ejb;

import java.util.Random;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.CodigoVendedorDAO;
import modelo.pojo.CodigoActivacionVendedor;

@Stateless
@LocalBean
public class CodigoVendedorEJB {
	
	public void insertCodigo(CodigoActivacionVendedor c) {
		
		CodigoVendedorDAO codigoVendedorDAO = new CodigoVendedorDAO();
		
		codigoVendedorDAO.insertCodigo(c);
		
	}
	
	public void borrarCodigo(int id_vendedor) {
		CodigoVendedorDAO codigoVendedorDAO = new CodigoVendedorDAO();
		
		codigoVendedorDAO.borrarCodigo(id_vendedor);
	}
	
	public boolean existeCodigo(int codigo) {
		CodigoVendedorDAO codigoVendedorDAO = new CodigoVendedorDAO();
		
		return codigoVendedorDAO.existeCodigo(codigo);
	}
	
	public int buscarVendedorPorCodigo(int codigo) {
		CodigoVendedorDAO codigoVendedorDAO = new CodigoVendedorDAO();
		
		return codigoVendedorDAO.buscarVendedorPorCodigo(codigo);
	}
	
	/**
	 * Método EJB para generar un código, número entero hasta el 5000;
	 * 
	 * @return
	 */
	public int generarCodigoVendedor() {
		Random r = new Random();
		return r.nextInt(5000);

	}

}
