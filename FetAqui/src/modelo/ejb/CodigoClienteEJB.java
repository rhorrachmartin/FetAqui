package modelo.ejb;

import java.util.Random;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.CodigoClienteDAO;
import modelo.pojo.CodigoActivacionCliente;

@Stateless
@LocalBean
public class CodigoClienteEJB {
	
	public void insertCodigo(CodigoActivacionCliente c) {
		
		CodigoClienteDAO codigoClienteDAO = new CodigoClienteDAO();
		
		codigoClienteDAO.insertCodigo(c);
		
	}
	
	public void borrarCodigo(int id_cliente) {
		CodigoClienteDAO codigoClienteDAO = new CodigoClienteDAO();
		
		codigoClienteDAO.borrarCodigo(id_cliente);
	}
	
	public boolean existeCodigo(int codigo) {
		CodigoClienteDAO codigoClienteDAO = new CodigoClienteDAO();
		
		return codigoClienteDAO.existeCodigo(codigo);
	}
	
	public int buscarClientePorCodigo(int codigo) {
		CodigoClienteDAO codigoClienteDAO = new CodigoClienteDAO();
		
		return codigoClienteDAO.buscarClientePorCodigo(codigo);
	}
	
	/**
	 * Método EJB para generar un código, número entero hasta el 5000;
	 * 
	 * @return
	 */
	public int generarCodigoCliente() {
		Random r = new Random();
		return r.nextInt(5000);

	}

}
