package modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.CategoriaDAO;
import modelo.pojo.Categoria;

@Stateless
@LocalBean
public class CategoriaEJB {

	
	public ArrayList<Categoria> getCategorias() {
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		
		return categoriaDAO.getCategorias();
	}

	
}
