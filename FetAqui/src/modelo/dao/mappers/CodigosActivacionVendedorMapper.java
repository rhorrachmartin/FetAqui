package modelo.dao.mappers;

import modelo.pojo.CodigoActivacionVendedor;

/**
 * Interfaz para manejar los mapper de códigos de activación
 * 
 * @author ramon
 *
 */
public interface CodigosActivacionVendedorMapper {

	/**
	 * Método para insertar un codigo de activación de vendedor en BD
	 * 
	 * @param c Recibe CodigoActivacionVendedor
	 */
	public void insertCodigo(CodigoActivacionVendedor codigo);

	/**
	 * Método para recoger el id de un usuario asociado al código
	 * 
	 * @param codigo
	 * @return Devuelve la id del vendedor
	 */
	public int buscarVendedorPorCodigo(int codigo);

	/**
	 * Método para borrar un codigo de activación en función del id de usuario
	 * 
	 * @param id_vendedor
	 */
	public void borrarCodigo(int id_vendedor);

	/**
	 * Método para comprobar si un codigo de activación existe en BD
	 * 
	 * @param codigo
	 * @return Devuelve un boolean
	 */
	public boolean existeCodigo(int codigo);

}