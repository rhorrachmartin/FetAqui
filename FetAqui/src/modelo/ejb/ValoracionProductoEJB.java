package modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.ValoracionProductoDAO;
import modelo.pojo.ValoracionProducto;

/**
 * Clase EJB para la llamada al DAO de valoración Producto
 * 
 * @author ramon
 *
 */

@Stateless
@LocalBean
public class ValoracionProductoEJB {

	/**
	 * Método para insertar la valoracion de un producto
	 * 
	 * @param valoracionProducto Recibe un pojo ValoracionProducto
	 */
	public void insertarValoracionProducto(ValoracionProducto valoracionProducto) {

		ValoracionProductoDAO valoracionProductoDAO = new ValoracionProductoDAO();

		valoracionProductoDAO.insertarValoracionProducto(valoracionProducto);

	}

	/**
	 * Método para borrar la valoracion de un producto hecha por un usuario Cliente
	 * 
	 * @param id_cliente Recibe la id del cliente
	 */
	public void borrarValoracionCliente(Integer id_cliente) {

		ValoracionProductoDAO valoracionProductoDAO = new ValoracionProductoDAO();

		valoracionProductoDAO.borrarValoracionCliente(id_cliente);

	}

}
