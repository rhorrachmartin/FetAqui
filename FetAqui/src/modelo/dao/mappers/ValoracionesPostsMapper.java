package modelo.dao.mappers;


import org.apache.ibatis.annotations.Param;

import modelo.pojo.ValoracionPost;


public interface ValoracionesPostsMapper {

	
	public int insertarValoracionPostPorDefecto(ValoracionPost valoracionPost);
	
	public void borrarValoracionCliente(@Param("id_cliente") Integer id_cliente);

}
