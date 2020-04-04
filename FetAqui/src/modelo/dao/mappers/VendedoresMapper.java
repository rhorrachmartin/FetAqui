package modelo.dao.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import modelo.pojo.PedidoDetallado;
import modelo.pojo.Producto;
import modelo.pojo.Vendedor;

/**
 * Interfaz mapper para manejar los mapper de Direcciones
 * 
 * @author ramon
 *
 */
public interface VendedoresMapper {

	/**
	 * Método para obtener los detalles de un vendedor
	 * 
	 * @param id_vendedor recibe la id del vendedor
	 * @return devuelve un pojo vendedor
	 */
	public Vendedor getVendedor(@Param("email") String email, @Param("password") String password);

	/**
	 * Método para obtener un vendedor a traves de su mail
	 * 
	 * @param email
	 * @return
	 */
	public Vendedor getVendedorEmail(@Param("email") String email);

	/**
	 * Método para obtener un Vendedor a partir de su email y password
	 * 
	 * @param email    recibe String email
	 * @param password Recibe String password
	 * @return Devuelve un pojo Vendedor
	 */
	public Vendedor getVendedorEmailPass(@Param("email") String email, @Param("password") String password);

	/**
	 * Método para obtener las direcciones de una población
	 * 
	 * @return ArrayList<Direccion>
	 */
	public ArrayList<Vendedor> getVendedores();

	/**
	 * Método para buscar vendedores por poblacion
	 * 
	 * @param poblacion recibe string con la poblacion
	 * @return
	 */
	public ArrayList<Vendedor> getVendedoresPoblacion(@Param("poblacion") String poblacion);

	/**
	 * Método para buscar vendedores por nombre
	 * 
	 * @param nombre recibe string con el nombre
	 * @return
	 */
	public ArrayList<Vendedor> getVendedoresNombre(@Param("nombre") String nombre);

	/**
	 * Método para obtener los productos de un vendedor
	 * 
	 * @return ArrayList<Producto>
	 */
	public ArrayList<Producto> getProductosVendedor(@Param("id_vendedor") Integer id_vendedor);

	/**
	 * Método para obtener un producto de un vendedor en concreto
	 * 
	 * @param id_vendedor recibe id_vendedor
	 * @param id_producto recibe la id_producto
	 * @return devuelve un pojo Producto
	 */
	public Producto getProductoVendedor(@Param("id_vendedor") Integer id_vendedor,
			@Param("id_producto") Integer id_producto);

	/**
	 * Método para obtener todos los pedidos detallados de un vendedor
	 * 
	 * @param id_vendedor recibe la id del vendedor
	 * @return devuelve un arraylist de PedidoDetallado
	 */
	public ArrayList<PedidoDetallado> getPedidosDetallados(@Param("id_vendedor") Integer id_vendedor);

	/**
	 * Método para insertar un vendedor
	 * 
	 * @param v REcibe un pojo vendedor
	 */
	public void insertarVendedor(Vendedor v);

	/**
	 * Método para activar un vendedor
	 * 
	 * @param v Recibe un pojo vendedor
	 */
	public void activarVendedor(Integer id_vendedor);

	/**
	 * Método para activar venta onlinde un vendedor
	 * 
	 * @param v Recibe un pojo vendedor
	 */
	public void activarVentaOnline(Integer id_vendedor);

	/**
	 * Método para desactivar venta onlinde un vendedor
	 * 
	 * @param v Recibe un pojo vendedor
	 */
	public void desactivarVentaOnline(Integer id_vendedor);
	
	public void updateNif(@Param("nif") String nif, @Param("id_vendedor") Integer id_vendedor);

	public void updateNombre(@Param("nombre") String nombre, @Param("id_vendedor") Integer id_vendedor);

	public void updateTelefono(@Param("telefono") String telefono, @Param("id_vendedor") Integer id_vendedor);

	public void updatePassword(@Param("password") String password, @Param("id_vendedor") Integer id_vendedor);

	public void updateFoto(@Param("foto") String foto, @Param("id_vendedor") Integer id_vendedor);

	public void updateDireccion(@Param("id_vendedor") Integer id_vendedor);

	public void bajaVendedor(@Param("id_vendedor") Integer id_vendedor);

}
