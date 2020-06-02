package modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.ClienteDAO;
import modelo.pojo.Cliente;
/**
 * Clase EJB para la llamada al DAO de Cliente
 * @author ramon
 *
 */

@Stateless
@LocalBean
public class ClienteEJB {

	/**
	 * Método para insertar un cliente en BD
	 * 
	 * @param c REcibe un pojo Cliente
	 */
	public void insertarCliente(Cliente c) {

		ClienteDAO clienteDAO = new ClienteDAO();

		clienteDAO.insertarCliente(c);
	};

	/**
	 * Método para obtner un cliente a través de su email
	 * 
	 * @param email Recibe el email del Cliente
	 * @return devuelve un pojo Cliente
	 */
	public Cliente getClienteEmail(String email) {
		ClienteDAO clienteDAO = new ClienteDAO();
		return clienteDAO.getClienteEmail(email);
	}
	
	/**
	 * Método para obtener un cliente a través de su email y su password
	 * 
	 * @param email    Recibe su email y password
	 * @param password
	 * @return Devuelve un pojo Cliente
	 */
	public Cliente getCliente(String email, String password) {
		ClienteDAO clienteDAO = new ClienteDAO();
		return clienteDAO.getCliente(email, password);
	}
	
	/**
	 * Método para obtener un cliente por su id
	 * 
	 * @param id_cliente Recibe la id de un cliente
	 * @return Devuelve un pojo Cliente
	 */
	public Cliente getClientePorId(Integer id_cliente) {
		ClienteDAO clienteDAO = new ClienteDAO();
		return clienteDAO.getClientePorId(id_cliente);
	}
	
	/**
	 * Método para obtener un cliente a través de su email y su password
	 * 
	 * @param email    Recibe su email y password
	 * @param password
	 * @return Devuelve un pojo Cliente
	 */
	public Cliente getClienteEmailPass(String email, String password) {
		ClienteDAO clienteDAO = new ClienteDAO();
		return clienteDAO.getClienteEmailPass(email, password);
	}
	
	/**
	 * Método para activar un cliente en BD
	 * 
	 * @param id_cliente Recibe la id de un cliente
	 */
	public void activarCliente(Integer id_cliente) {
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.activarCliente(id_cliente);
	}
	
	/**
	 * Método para actualizar el nombre de un cliente
	 * 
	 * @param nombre     Recibe su nombre
	 * @param id_cliente Recibe su id
	 */
	public void updateNombre(String nombre, Integer id_cliente) {
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.updateNombre(nombre, id_cliente);
	}
	
	/**
	 * Método para actualizar el apellido de un cliente
	 * 
	 * @param apellido   Recibe su apellido
	 * @param id_cliente Recibe su id
	 */
	public void updateApellido(String apellido, Integer id_cliente) {
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.updateApellido(apellido, id_cliente);
	}
	
	/**
	 * Método para actualizar el teléfono de un cliente
	 * 
	 * @param telefono   Recibe el num de teléfono
	 * @param id_cliente Recibe la id del cliente
	 */
	public void updateTelf(String telefono, Integer id_cliente) {
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.updateTelf(telefono, id_cliente);
	}
	
	/**
	 * Método para actualizar el password de un cliente
	 * 
	 * @param password   Recibe el password
	 * @param id_cliente REcibe la id del cliente
	 */
	public void updatePassword(String password, Integer id_cliente) {
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.updatePassword(password, id_cliente);
	}
	
	/**
	 * Método para actualizar la foto del cliente
	 * 
	 * @param foto       Recibe la foto
	 * @param id_cliente REcibe la id del cliente
	 */
	public void updateFoto(String foto, Integer id_cliente) {
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.updateFoto(foto, id_cliente);
	}
	
	/**
	 * Método para actualizar la dirección del cliente
	 * 
	 * @param id_cliente Recibe la id del cliente
	 */
	public void updateDireccion(Integer id_cliente) {
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.updateDireccion(id_cliente);
	}
	
	/**
	 * Método para borrar un cliente de la BD
	 * 
	 * @param id_cliente REcibe la id del cliente
	 */
	public void bajaCliente(Integer id_cliente) {
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.bajaCliente(id_cliente);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
