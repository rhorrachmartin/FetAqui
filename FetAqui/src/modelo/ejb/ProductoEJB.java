package modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.ProductoDAO;
import modelo.pojo.Producto;

@Stateless
@LocalBean
public class ProductoEJB {

	public Integer insertarProducto(Producto producto) {
		ProductoDAO productoDAO = new ProductoDAO();
		return productoDAO.insertarProducto(producto);
	}

	public ArrayList<Producto> getProductosVendedor(Integer id_vendedor) {
		ProductoDAO productoDAO = new ProductoDAO();
		return productoDAO.getProductosVendedor(id_vendedor);
	}
	
	public ArrayList<Producto> getProductosVendedorCategoria(Integer id_vendedor, Integer id_categoria) {
		ProductoDAO productoDAO = new ProductoDAO();
		return productoDAO.getProductosVendedorCategoria(id_vendedor, id_categoria);
	}
	
	public ArrayList<Producto> getProductosCategoria(Integer id_categoria) {
		ProductoDAO productoDAO = new ProductoDAO();
		return productoDAO.getProductosCategoria(id_categoria);
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
	
	public ArrayList<Producto> getProductos() {
		ProductoDAO productoDAO = new ProductoDAO();
		return productoDAO.getProductos();
	}

}
