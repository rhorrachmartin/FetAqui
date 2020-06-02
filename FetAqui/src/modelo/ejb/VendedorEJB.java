package modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.VendedorDAO;
import modelo.pojo.Vendedor;

/**
 * Clase EJB para llamada al DAO de Vendedor
 * 
 * @author ramon
 *
 */

@Stateless
@LocalBean
public class VendedorEJB {

	/**
	 * Método para insertar un usuario Vendedor en la BD
	 * 
	 * @param v Recibe un pojo Vendedor
	 */
	public void insertarVendedor(Vendedor v) {

		VendedorDAO vendedorDAO = new VendedorDAO();

		vendedorDAO.insertVendedor(v);
	};

	/**
	 * Método para obtener un vendedor a través de su email
	 * 
	 * @param email Recibe el email del vendedor
	 * @return Devuelve un pojo Vendedor
	 */
	public Vendedor getVendedorEmail(String email) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		return vendedorDAO.getVendedorEmail(email);
	}

	/**
	 * Método para obtener un vendedor a través de su email y password
	 * 
	 * @param email
	 * @param password
	 * @return Devuelve un pojo Vendedor
	 */
	public Vendedor getVendedorEmailPass(String email, String password) {
		VendedorDAO vendedorDAO = new VendedorDAO();

		return vendedorDAO.getVendedorEmailPass(email, password);
	}

	/**
	 * Método para activar un vendedor
	 * 
	 * @param id_vendedor recibe el id del vendedor
	 */
	public void activarVendedor(Integer id_vendedor) {
		VendedorDAO vendedorDAO = new VendedorDAO();

		vendedorDAO.activarVendedor(id_vendedor);
	}

	/**
	 * Método para activar la venta online de un vendedor
	 * 
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void activarVentaOnline(Integer id_vendedor) {
		VendedorDAO vendedorDAO = new VendedorDAO();

		vendedorDAO.activarVentaOnline(id_vendedor);
	}

	/**
	 * Método para desactivar la venta online de un vendedor
	 * 
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void desactivarVentaOnline(Integer id_vendedor) {
		VendedorDAO vendedorDAO = new VendedorDAO();

		vendedorDAO.desactivarVentaOnline(id_vendedor);
	}

	/**
	 * Método para obtener un vendedor a través de su email y password
	 * 
	 * @param email
	 * @param password
	 * @return Devuelve un pojo Vendedor
	 */
	public Vendedor getVendedor(String email, String password) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		return vendedorDAO.getVendedor(email, password);
	}

	/**
	 * Método para obtener todos los vendedores
	 * 
	 * @return Devuelve un arraylist de Vendedor
	 */
	public ArrayList<Vendedor> getVendedores() {
		VendedorDAO vendedorDAO = new VendedorDAO();
		return vendedorDAO.getVendedores();
	}

	/**
	 * Método para obtener todos los vendedores de una Poblacion
	 * 
	 * @param id_poblacion REcibe la id de la poblacion
	 * @return Devuelve un arraylist de poblacion
	 */
	public ArrayList<Vendedor> getVendedoresPoblacion(Integer id_poblacion) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		return vendedorDAO.getVendedoresPoblacion(id_poblacion);
	}

	/**
	 * Método para obtener un vendedor a través de su id
	 * 
	 * @param id_vendedor Recibe la id del vendedor
	 * @return Devuelve un pojo Vendedor
	 */
	public Vendedor getVendedorPorId(Integer id_vendedor) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		return vendedorDAO.getVendedorPorId(id_vendedor);
	}

	/**
	 * Método para actualizar el nif del vendedor
	 * 
	 * @param nif         Recibe el NIF
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void updateNif(String nif, Integer id_vendedor) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		vendedorDAO.updateNif(nif, id_vendedor);
	}

	/**
	 * Método para actualizar el nombre de un vendedor
	 * 
	 * @param nombre      Recibe el nombre
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void updateNombre(String nombre, Integer id_vendedor) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		vendedorDAO.updateNombre(nombre, id_vendedor);
	}

	/**
	 * Método para actualizar el teléfono de un vendedor
	 * 
	 * @param telefono    Recibe el teléfono
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void updateTelefono(String telefono, Integer id_vendedor) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		vendedorDAO.updateTelefono(telefono, id_vendedor);
	}

	/**
	 * Método para actualizar el password de un vendedor
	 * 
	 * @param password    Recibe el password
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void updatePassword(String password, Integer id_vendedor) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		vendedorDAO.updatePassword(password, id_vendedor);
	}

	/**
	 * Método para actualizar la foto de un vendedor
	 * 
	 * @param foto        Recibe la foto
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void updateFoto(String foto, Integer id_vendedor) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		vendedorDAO.updateFoto(foto, id_vendedor);
	}

	/**
	 * Método para actualizar la dirección de un vendedor
	 * 
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void updateDireccion(Integer id_vendedor) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		vendedorDAO.updateDireccion(id_vendedor);
	}

	/**
	 * Método para borrar un vendedor de la BD
	 * 
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void bajaVendedor(Integer id_vendedor) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		vendedorDAO.bajaVendedor(id_vendedor);
	}

}
