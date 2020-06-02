package modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.PostDAO;
import modelo.pojo.Post;

/**
 * Clase EJB para la llamada al DAO de Post
 * @author ramon
 *
 */

@Stateless
@LocalBean
public class PostEJB {
	
	/**
	 * Método para obtener todos los posts
	 * 
	 * @return Devuelve un arraylist de Post
	 */
	public ArrayList<Post> getPosts() {
		PostDAO postDAO = new PostDAO();

		return postDAO.getPosts();
	}
	
	/**
	 * Método para obtener todos los Post de un vendedor a través de su id
	 * 
	 * @param id_vendedor
	 * @return Devuelve un arraylist de Post
	 */
	public ArrayList<Post> getPostsVendedor(Integer id_vendedor) {
		PostDAO postDAO = new PostDAO();

		return postDAO.getPostsVendedor(id_vendedor);
	}
	
	/**
	 * Método para insertar un Post en BD
	 * 
	 * @param post REcibe un pojo Post
	 */
	public void insertarPost(Post post) {
		PostDAO postDAO = new PostDAO();

		postDAO.insertarPost(post);
	}
	
	/**
	 * Método para borrar un post
	 * 
	 * @param id_post Recibe la id del post
	 */
	public void borrarPost(Integer id_post) {
		PostDAO postDAO = new PostDAO();

		postDAO.borrarPost(id_post);
	}
	
	/**
	 * Método para obtener un Post a través de su id
	 * 
	 * @param id_post Recibe la id del posrt
	 * @return Devuelve un pojo Post
	 */
	public Post getPostPorId(Integer id_post) {
		PostDAO postDAO = new PostDAO();
		
		return postDAO.getPostPorId(id_post);
	}
	
	/**
	 * Método para editar un post ya creado
	 * 
	 * @param post Recibe un pojo Post
	 */
	public void editarPost(Post post) {
		PostDAO postDAO = new PostDAO();
		
		postDAO.editarPost(post);
	}
}
