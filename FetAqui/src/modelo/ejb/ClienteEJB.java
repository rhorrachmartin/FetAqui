package modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.ClienteDAO;
import modelo.pojo.Cliente;

@Stateless
@LocalBean
public class ClienteEJB {

	/**
	 * Método para insertar un cliente en BD
	 * @param c
	 */
	public void insertarCliente(Cliente c) {

		ClienteDAO clienteDAO = new ClienteDAO();

		clienteDAO.insertarCliente(c);
	};

	/**
	 * Método para obtner un cliente a través de su email
	 * @param email
	 * @return
	 */
	public Cliente getClienteEmail(String email) {
		ClienteDAO clienteDAO = new ClienteDAO();
		return clienteDAO.getClienteEmail(email);
	}
	
	/**
	 * Método para obtener un cliente a través de su email y su password
	 * @param email
	 * @param password
	 * @return Devuelve un pojo Cliente
	 */
	public Cliente getClienteEmailPass(String email, String password) {
		ClienteDAO clienteDAO = new ClienteDAO();
		return clienteDAO.getClienteEmailPass(email, password);
	}
	
	/**
	 * Método para activar un cliente en BD
	 * @param id_cliente
	 */
	public void activarCliente(Integer id_cliente) {
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.activarCliente(id_cliente);
	}
}
