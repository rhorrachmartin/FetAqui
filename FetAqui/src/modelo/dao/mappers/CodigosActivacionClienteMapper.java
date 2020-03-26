package modelo.dao.mappers;

import modelo.pojo.CodigoActivacionCliente;

public interface CodigosActivacionClienteMapper {
	
	public void insertCodigo(CodigoActivacionCliente codigo);
	
	public int buscarClientePorCodigo(int codigo);
	
	public void borrarCodigo(int id_cliente);
	
	public boolean existeCodigo(int codigo);

	
}