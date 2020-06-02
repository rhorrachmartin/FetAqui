package modelo.ejb;

import java.util.Random;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.CodigoClienteDAO;
import modelo.pojo.CodigoActivacionCliente;

/**
 * Clase EJB para la llamada al DAO de codido Cliente
 * 
 * @author ramon
 *
 */

@Stateless
@LocalBean
public class CodigoClienteEJB {

	/**
	 * Método para insertar CodigoActivacionCliente
	 * 
	 * @param c REcibe CodigoActivacionCliente
	 */
	public void insertCodigo(CodigoActivacionCliente c) {

		CodigoClienteDAO codigoClienteDAO = new CodigoClienteDAO();

		codigoClienteDAO.insertCodigo(c);

	}

	/**
	 * Método para borrar un codigo de activación en función del id de usuario
	 * 
	 * @param id_cliente
	 */
	public void borrarCodigo(int id_cliente) {
		CodigoClienteDAO codigoClienteDAO = new CodigoClienteDAO();

		codigoClienteDAO.borrarCodigo(id_cliente);
	}

	/**
	 * Método para comprobar si un codigo de activación existe en BD
	 * 
	 * @param codigo
	 * @return Devuelve un boolean
	 */
	public boolean existeCodigo(int codigo) {
		CodigoClienteDAO codigoClienteDAO = new CodigoClienteDAO();

		return codigoClienteDAO.existeCodigo(codigo);
	}

	/**
	 * Método para recoger el id de un usuario asociado al código
	 * 
	 * @param codigo
	 * @return Devuelve una id de cliente
	 */
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
