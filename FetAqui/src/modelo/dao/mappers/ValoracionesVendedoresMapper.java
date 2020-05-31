package modelo.dao.mappers;


import modelo.pojo.ValoracionCv;


public interface ValoracionesVendedoresMapper {

	
	public int insertarValoracionVendedor(ValoracionCv valoracionCv);
	
	public void borrarValoracionCliente(Integer id_cliente);

}
