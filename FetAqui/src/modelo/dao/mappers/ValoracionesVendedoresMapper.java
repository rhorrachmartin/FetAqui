package modelo.dao.mappers;

import modelo.pojo.ValoracionCv;

/**
 * Interfaz para manejar los mapper de valoracion cliente vendedor
 * 
 * @author ramon
 *
 */
public interface ValoracionesVendedoresMapper {

	/**
	 * Método para insertar la valoracion de un vendedor
	 * 
	 * @param valoracionCv Recibe pojo ValoracionCv
	 */
	public int insertarValoracionVendedor(ValoracionCv valoracionCv);

	/**
	 * Método para borrar la valoracion de un vendedor hecha por un usuario cliente
	 * 
	 * @param id_cliente Recibe la id del cliente
	 */
	public void borrarValoracionCliente(Integer id_cliente);

}
