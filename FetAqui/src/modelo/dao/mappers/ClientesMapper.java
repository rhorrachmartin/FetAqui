package modelo.dao.mappers;


import org.apache.ibatis.annotations.Param;

import modelo.pojo.Cliente;

/**
 * Interfaz mapper para manejar los mapper de Clientes
 * 
 * @author ramon
 *
 */
public interface ClientesMapper {

	/**
	 * Método para obtener los usuarios
	 * 
	 * @param email   Espera recibir String con el email del cliente
	 * @param password Espera recibir String con el password del cliente
	 * @return Devuelve un pojo Cliente
	 */
	public Cliente getCliente(@Param("email") String email, @Param("password") String password);

}
