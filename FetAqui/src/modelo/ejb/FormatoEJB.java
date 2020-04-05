package modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.FormatoDAO;
import modelo.pojo.Formato;

@Stateless
@LocalBean
public class FormatoEJB {

	public ArrayList<Formato> getFormatos() {
		FormatoDAO formatoDAO = new FormatoDAO();

		return formatoDAO.getFormatos();
	}

}
