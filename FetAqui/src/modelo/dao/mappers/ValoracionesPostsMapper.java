package modelo.dao.mappers;

import org.apache.ibatis.annotations.Param;

import modelo.pojo.ValoracionPost;

/**
 * Interfaz para manejar los mapper de valoraciones de un post
 * @author ramon
 *
 */
public interface ValoracionesPostsMapper {

	/**
	 * Método para insertar una valoracion de un Post
	 * 
	 * @param valoracionPost recibe un pojo ValoracionPost
	 */
	public int insertarValoracionPostPorDefecto(ValoracionPost valoracionPost);

	/**
	 * Método para borrar la valoracion de un post hecha por un cliente
	 * 
	 * @param id_cliente Recibe la id del cliente
	 */
	public void borrarValoracionCliente(@Param("id_cliente") Integer id_cliente);

}
