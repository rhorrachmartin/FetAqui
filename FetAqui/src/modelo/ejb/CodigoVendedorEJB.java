package modelo.ejb;

import java.util.Random;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.CodigoVendedorDAO;
import modelo.pojo.CodigoActivacionVendedor;

/**
 * Clase EJB para la llamada al DAO de codigo Vendedor
 * @author ramon
 *
 */

@Stateless
@LocalBean
public class CodigoVendedorEJB {
	
	/**
	 * Método para insertar un codigo de activación de vendedor en BD
	 * 
	 * @param c Recibe CodigoActivacionVendedor
	 */
	public void insertCodigo(CodigoActivacionVendedor c) {
		
		CodigoVendedorDAO codigoVendedorDAO = new CodigoVendedorDAO();
		
		codigoVendedorDAO.insertCodigo(c);
		
	}
	
	/**
	 * Método para borrar un codigo de activación en función del id de usuario
	 * 
	 * @param id_vendedor
	 */
	public void borrarCodigo(int id_vendedor) {
		CodigoVendedorDAO codigoVendedorDAO = new CodigoVendedorDAO();
		
		codigoVendedorDAO.borrarCodigo(id_vendedor);
	}
	
	/**
	 * Método para comprobar si un codigo de activación existe en BD
	 * 
	 * @param codigo
	 * @return Devuelve un boolean
	 */
	public boolean existeCodigo(int codigo) {
		CodigoVendedorDAO codigoVendedorDAO = new CodigoVendedorDAO();
		
		return codigoVendedorDAO.existeCodigo(codigo);
	}
	
	/**
	 * Método para recoger el id de un usuario asociado al código
	 * 
	 * @param codigo
	 * @return Devuelve la id del vendedor
	 */
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
