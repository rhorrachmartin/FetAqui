package modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.ValoracionVendedorDAO;
import modelo.pojo.ValoracionCv;

/**
 * Clase EJB para la llamada al DAO de valoración Vendedor
 * 
 * @author ramon
 *
 */

@Stateless
@LocalBean
public class ValoracionVendedorEJB {

	/**
	 * Método para insertar la valoracion de un vendedor
	 * 
	 * @param valoracionCv Recibe pojo ValoracionCv
	 */
	public void insertarValoracionVendedor(ValoracionCv valoracionCv) {
		ValoracionVendedorDAO valoracionVendedorDAO = new ValoracionVendedorDAO();

		valoracionVendedorDAO.insertarValoracionVendedor(valoracionCv);
	}

	/**
	 * Método para borrar la valoracion de un vendedor hecha por un usuario cliente
	 * 
	 * @param id_cliente Recibe la id del cliente
	 */
	public void borrarValoracionCliente(Integer id_cliente) {
		ValoracionVendedorDAO valoracionVendedorDAO = new ValoracionVendedorDAO();

		valoracionVendedorDAO.borrarValoracionCliente(id_cliente);
	}

}
