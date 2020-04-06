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

}
