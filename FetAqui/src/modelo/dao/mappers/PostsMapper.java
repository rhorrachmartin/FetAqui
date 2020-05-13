package modelo.dao.mappers;


import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import modelo.pojo.Post;

/**
 * Interfaz mapper para manejar los mapper de Poblaciones
 * 
 * @author ramon
 *
 */
public interface PostsMapper {

	/**
	 * Método para obtener las categorias
	 * @return ArrayList<Categoria> 
	 */
	public ArrayList<Post> getPosts();
	
	public Post getPostPorId(Integer id_post);
	
	public ArrayList<Post> getPostsPorVendedor(@Param("id_vendedor") Integer id_vendedor);
	
	public void insertarPost(Post post);
	
	public void borrarPost(Integer id_post);
	
	public void editarPost(Post post);

}
