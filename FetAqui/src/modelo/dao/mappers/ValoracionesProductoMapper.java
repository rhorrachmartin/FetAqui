package modelo.dao.mappers;

import modelo.pojo.ValoracionProducto;

/**
 * Interfaz mapper para manejar los mapper de valoraciones de un producto
 * 
 * @author ramon
 *
 */
public interface ValoracionesProductoMapper {

	/**
	 * Método para insertar la valoracion de un producto
	 * 
	 * @param valoracionProducto Recibe un pojo ValoracionProducto
	 */
	public int insertarValoracionProducto(ValoracionProducto valoracionProducto);

	/**
	 * Método para borrar la valoracion de un producto hecha por un usuario Cliente
	 * 
	 * @param id_cliente Recibe la id del cliente
	 */
	public void borrarValoracionCliente(Integer id_cliente);

}
