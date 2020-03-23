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
