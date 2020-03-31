package modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.ibatis.annotations.Param;

import modelo.dao.ClienteDAO;
import modelo.dao.DireccionDAO;
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
	
	public Cliente getCliente(String email, String password) {
		ClienteDAO clienteDAO = new ClienteDAO();
		return clienteDAO.getCliente(email, password);
	}
	
	public Cliente getClientePorId(Integer id_cliente) {
		ClienteDAO clienteDAO = new ClienteDAO();
		return clienteDAO.getClientePorId(id_cliente);
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
	public void updateNombre(String nombre, Integer id_cliente) {
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.updateNombre(nombre, id_cliente);
	}
	
	public void updateApellido(String apellido, Integer id_cliente) {
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.updateApellido(apellido, id_cliente);
	}
	
	public void updateTelf(String telefono, Integer id_cliente) {
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.updateTelf(telefono, id_cliente);
	}
	
	public void updatePassword(String password, Integer id_cliente) {
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.updatePassword(password, id_cliente);
	}
	
	public void updateFoto(String foto, Integer id_cliente) {
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.updateFoto(foto, id_cliente);
	}

	public void updateDireccion(Integer id_cliente) {
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.updateDireccion(id_cliente);
	}
	
	public void bajaCliente(Integer id_cliente) {
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.bajaCliente(id_cliente);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
