package modelo.dao.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import modelo.pojo.Post;

/**
 * Interfaz mapper para manejar los mapper de Post
 * 
 * @author ramon
 *
 */
public interface PostsMapper {

	/**
	 * Método para obtener todos los posts
	 * 
	 * @return Devuelve un arraylist de Post
	 */
	public ArrayList<Post> getPosts();

	/**
	 * Método para obtener un Post a través de su id
	 * 
	 * @param id_post Recibe la id del posrt
	 * @return Devuelve un pojo Post
	 */
	public Post getPostPorId(Integer id_post);

	/**
	 * Método para obtener todos los Post de un vendedor a través de su id
	 * 
	 * @param id_vendedor
	 * @return Devuelve un arraylist de Post
	 */
	public ArrayList<Post> getPostsPorVendedor(@Param("id_vendedor") Integer id_vendedor);

	/**
	 * Método para insertar un Post en BD
	 * 
	 * @param post REcibe un pojo Post
	 */
	public void insertarPost(Post post);

	/**
	 * Método para borrar un post
	 * 
	 * @param id_post Recibe la id del post
	 */
	public void borrarPost(Integer id_post);

	/**
	 * Método para editar un post ya creado
	 * 
	 * @param post Recibe un pojo Post
	 */
	public void editarPost(Post post);

}
