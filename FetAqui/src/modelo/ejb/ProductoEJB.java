package modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.ProductoDAO;
import modelo.pojo.Producto;

@Stateless
@LocalBean
public class ProductoEJB {

	public void insertarProducto(Producto producto) {
		ProductoDAO productoDAO = new ProductoDAO();
		productoDAO.insertarProducto(producto);
	}

	public ArrayList<Producto> getProductosVendedor(Integer id_vendedor) {
		ProductoDAO productoDAO = new ProductoDAO();
		return productoDAO.getProductosVendedor(id_vendedor);
	}

	public Producto getProductoPorId(Integer id_producto) {
		ProductoDAO productoDAO = new ProductoDAO();
		return productoDAO.getProductoPorId(id_producto);
	}

	public void activarVentaOnline(Integer id_producto) {
		ProductoDAO productoDAO = new ProductoDAO();
		productoDAO.activarVentaOnline(id_producto);
	}

	public void desactivarVentaOnline(Integer id_producto) {
		ProductoDAO productoDAO = new ProductoDAO();
		productoDAO.desactivarVentaOnline(id_producto);
	}

	public void borrarProducto(Integer id_producto) {
		ProductoDAO productoDAO = new ProductoDAO();
		productoDAO.borrarProducto(id_producto);
	}

	public void actualizarProducto(Producto producto) {
		ProductoDAO productoDAO = new ProductoDAO();
		productoDAO.actualizarProducto(producto);
	}
	
	public void actualizarImagenProducto(Producto producto) {
		ProductoDAO productoDAO = new ProductoDAO();
		productoDAO.actualizarImagenProducto(producto);
	}

}
