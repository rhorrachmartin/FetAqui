package modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.PostDAO;
import modelo.pojo.Post;

@Stateless
@LocalBean
public class PostEJB {

	public ArrayList<Post> getPosts() {
		PostDAO postDAO = new PostDAO();

		return postDAO.getPosts();
	}

	public ArrayList<Post> getPostsVendedor(Integer id_vendedor) {
		PostDAO postDAO = new PostDAO();

		return postDAO.getPostsVendedor(id_vendedor);
	}

	public void insertarPost(Post post) {
		PostDAO postDAO = new PostDAO();

		postDAO.insertarPost(post);
	}

	public void borrarPost(Integer id_post) {
		PostDAO postDAO = new PostDAO();

		postDAO.borrarPost(id_post);
	}
	
	public Post getPostPorId(Integer id_post) {
		PostDAO postDAO = new PostDAO();
		
		return postDAO.getPostPorId(id_post);
	}
	
	public void editarPost(Post post) {
		PostDAO postDAO = new PostDAO();
		
		postDAO.editarPost(post);
	}
}
