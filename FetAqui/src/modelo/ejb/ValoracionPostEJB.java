package modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.ValoracionPostDAO;
import modelo.pojo.ValoracionPost;

/**
 * Clase EJB para la llamada al DAO valoración POST
 * @author ramon
 *
 */

@Stateless
@LocalBean
public class ValoracionPostEJB {
	
	/**
	 * Método para insertar una valoracion de un Post
	 * 
	 * @param valoracionPost recibe un pojo ValoracionPost
	 */
	public void insertarValoracionPostPorDefecto(ValoracionPost valoracionPost) {
		ValoracionPostDAO valoracionPostDAO = new ValoracionPostDAO();

		valoracionPostDAO.insertarValoracionPostPorDefecto(valoracionPost);
	}
	
	/**
	 * Método para borrar la valoracion de un post hecha por un cliente
	 * 
	 * @param id_cliente Recibe la id del cliente
	 */
	public void borrarValoracionCliente(Integer id_cliente) {
		ValoracionPostDAO valoracionPostDAO = new ValoracionPostDAO();

		valoracionPostDAO.borrarValoracionCliente(id_cliente);
	}

}
