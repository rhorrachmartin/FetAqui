package modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.ValoracionPostDAO;
import modelo.pojo.ValoracionPost;

@Stateless
@LocalBean
public class ValoracionPostEJB {

	public void insertarValoracionPostPorDefecto(ValoracionPost valoracionPost) {
		ValoracionPostDAO valoracionPostDAO = new ValoracionPostDAO();
		
		valoracionPostDAO.insertarValoracionPostPorDefecto(valoracionPost);
	}

}
