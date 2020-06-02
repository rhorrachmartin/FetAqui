package modelo.dao.mappers;

import modelo.pojo.CodigoActivacionCliente;

/**
 * Interfaz para manejar los mapper de códigos de activación de cliente
 * 
 * @author ramon
 *
 */
public interface CodigosActivacionClienteMapper {

	/**
	 * Método para insertar CodigoActivacionCliente
	 * 
	 * @param c REcibe CodigoActivacionCliente
	 */
	public void insertCodigo(CodigoActivacionCliente codigo);

	/**
	 * Método para recoger el id de un usuario asociado al código
	 * 
	 * @param codigo
	 * @return Devuelve una id de cliente
	 */
	public int buscarClientePorCodigo(int codigo);

	/**
	 * Método para borrar un codigo de activación en función del id de usuario
	 * 
	 * @param id_cliente
	 */
	public void borrarCodigo(int id_cliente);

	/**
	 * Método para comprobar si un codigo de activación existe en BD
	 * 
	 * @param codigo
	 * @return Devuelve un boolean
	 */
	public boolean existeCodigo(int codigo);

}