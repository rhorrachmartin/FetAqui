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

	public void borrarValoracionCliente(Integer id_cliente) {
		ValoracionPostDAO valoracionPostDAO = new ValoracionPostDAO();

		valoracionPostDAO.borrarValoracionCliente(id_cliente);
	}

}
