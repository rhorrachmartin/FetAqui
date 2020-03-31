package modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.DireccionDAO;
import modelo.pojo.Direccion;

@Stateless
@LocalBean
public class DireccionEJB {

	

	public void insertarDireccion(Direccion direccion) {
		DireccionDAO direccionDAO = new DireccionDAO();
		direccionDAO.insertarDireccion(direccion);
	}

	
}
