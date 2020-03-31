package modelo.dao.mappers;


import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import modelo.pojo.Cliente;
import modelo.pojo.PedidoDetallado;

/**
 * Interfaz mapper para manejar los mapper de Clientes
 * 
 * @author ramon
 *
 */
public interface ClientesMapper {
	
	/**Método para insertar un cliente en BD
	 * 
	 * @param c Recibe un pojo cliente
	 */
	public void insertarCliente(Cliente c);
	
	/**
	 * Método para activar un cliente 
	 * @param id_cliente Recibe la id del cliente
	 */
	public void activarCliente(Integer id_cliente);
	
	public void updateNombre(@Param("nombre") String nombre, @Param("id_cliente") Integer id_cliente);
	
	public void updateApellido(@Param("apellido") String apellido, @Param("id_cliente") Integer id_cliente);
	
	public void updateTelf(@Param("telefono") String telefono, @Param("id_cliente") Integer id_cliente);
	
	public void updatePassword(@Param("password") String password, @Param("id_cliente") Integer id_cliente);
	
	public void updateFoto(@Param("foto") String foto, @Param("id_cliente") Integer id_cliente);
	
	public void updateDireccion(Integer id_cliente);
	
	public void bajaCliente(Integer id_cliente);
	/**
	 * Método para obtener un cliente a traves de su email
	 * 
	 * @param email   Espera recibir String con el email del cliente
	 * @return Devuelve un pojo Cliente
	 */
	public Cliente getClienteEmail(@Param("email") String email);
	
	public Cliente getClientePorId(Integer id_cliente);
	
	
	/**
	 * Método para obtener un Cliente a partir de su email y password
	 * @param email recibe String email
	 * @param password Recibe String password
	 * @return Devuelve un pojo Cliente
	 */
	public Cliente getClienteEmailPass(@Param("email")String email, @Param("password") String password);
	
	/**
	 * Método para obtener un cliente
	 * 
	 * @param email   Espera recibir String con el email del cliente
	 * @param password Espera recibir String con el password del cliente
	 * @return Devuelve un pojo Cliente
	 */
	public Cliente getCliente(@Param("email") String email, @Param("password") String password);
	
	/**
	 * Método para obtener todos los pedidos de un cliente para su historial
	 * @param id_cliente recibe el id del cliente
	 * @return devuelve un arraylist de PedidoDetallado
	 */
	public ArrayList<PedidoDetallado> getPedidos(@Param("id_cliente") Integer id_cliente);

}
