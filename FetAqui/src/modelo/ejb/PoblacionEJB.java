package modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.PoblacionDAO;
import modelo.dao.VendedorDAO;
import modelo.pojo.Poblacion;
import modelo.pojo.Vendedor;

@Stateless
@LocalBean
public class PoblacionEJB {

	public Vendedor getVendedorEmail(String email) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		return vendedorDAO.getVendedorEmail(email);
	}

	public ArrayList<Poblacion> getPoblaciones() {
		PoblacionDAO poblacionDAO = new PoblacionDAO();
		return poblacionDAO.getPoblaciones();
	}

	public Poblacion getPoblacionPorId(Integer id_poblacion) {
		PoblacionDAO poblacionDAO = new PoblacionDAO();
		return poblacionDAO.getPoblacionPorId(id_poblacion);
	}

}
