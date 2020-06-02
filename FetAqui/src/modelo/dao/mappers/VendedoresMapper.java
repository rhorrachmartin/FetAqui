package modelo.dao.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import modelo.pojo.PedidoDetallado;
import modelo.pojo.Producto;
import modelo.pojo.Vendedor;

/**
 * Interfaz mapper para manejar los mapper de Vendedor
 * 
 * @author ramon
 *
 */
public interface VendedoresMapper {

	/**
	 * Método para obtener un vendedor a través de su email y password
	 * 
	 * @param email
	 * @param password
	 * @return Devuelve un pojo Vendedor
	 */
	public Vendedor getVendedor(@Param("email") String email, @Param("password") String password);

	/**
	 * Método para obtener un vendedor a través de su id
	 * 
	 * @param id_vendedor Recibe la id del vendedor
	 * @return Devuelve un pojo Vendedor
	 */
	public Vendedor getVendedorPorId(@Param("id_vendedor") Integer id_vendedor);

	/**
	 * Método para obtener un vendedor a través de su email
	 * 
	 * @param email Recibe el email del vendedor
	 * @return Devuelve un pojo Vendedor
	 */
	public Vendedor getVendedorEmail(@Param("email") String email);

	/**
	 * Método para obtener un vendedor a través de su email y password
	 * 
	 * @param email
	 * @param password
	 * @return Devuelve un pojo Vendedor
	 */
	public Vendedor getVendedorEmailPass(@Param("email") String email, @Param("password") String password);

	/**
	 * Método para obtener todos los vendedores
	 * 
	 * @return Devuelve un arraylist de Vendedor
	 */
	public ArrayList<Vendedor> getVendedores();

	/**
	 * Método para obtener todos los vendedores de una Poblacion
	 * 
	 * @param id_poblacion REcibe la id de la poblacion
	 * @return Devuelve un arraylist de poblacion
	 */
	public ArrayList<Vendedor> getVendedoresPoblacion(@Param("id_poblacion") Integer id_poblacion);

	/**
	 * Método para insertar un usuario Vendedor en la BD
	 * 
	 * @param v Recibe un pojo Vendedor
	 */
	public void insertarVendedor(Vendedor v);

	/**
	 * Método para activar un vendedor
	 * 
	 * @param id_vendedor recibe el id del vendedor
	 */
	public void activarVendedor(Integer id_vendedor);

	/**
	 * Método para activar la venta online de un vendedor
	 * 
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void activarVentaOnline(Integer id_vendedor);

	/**
	 * Método para desactivar la venta online de un vendedor
	 * 
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void desactivarVentaOnline(Integer id_vendedor);

	/**
	 * Método para actualizar el nif del vendedor
	 * 
	 * @param nif         Recibe el NIF
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void updateNif(@Param("nif") String nif, @Param("id_vendedor") Integer id_vendedor);

	/**
	 * Método para actualizar el nombre de un vendedor
	 * 
	 * @param nombre      Recibe el nombre
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void updateNombre(@Param("nombre") String nombre, @Param("id_vendedor") Integer id_vendedor);

	/**
	 * Método para actualizar el teléfono de un vendedor
	 * 
	 * @param telefono    Recibe el teléfono
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void updateTelefono(@Param("telefono") String telefono, @Param("id_vendedor") Integer id_vendedor);

	/**
	 * Método para actualizar el password de un vendedor
	 * 
	 * @param password    Recibe el password
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void updatePassword(@Param("password") String password, @Param("id_vendedor") Integer id_vendedor);

	/**
	 * Método para actualizar la foto de un vendedor
	 * 
	 * @param foto        Recibe la foto
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void updateFoto(@Param("foto") String foto, @Param("id_vendedor") Integer id_vendedor);

	/**
	 * Método para actualizar la dirección de un vendedor
	 * 
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void updateDireccion(@Param("id_vendedor") Integer id_vendedor);

	/**
	 * Método para borrar un vendedor de la BD
	 * 
	 * @param id_vendedor Recibe la id del vendedor
	 */
	public void bajaVendedor(@Param("id_vendedor") Integer id_vendedor);

}
