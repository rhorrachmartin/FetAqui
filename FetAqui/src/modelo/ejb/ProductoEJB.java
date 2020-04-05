package modelo.ejb;

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

}
