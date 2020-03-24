package modelo.dao.mappers;

import modelo.pojo.CodigoActivacionVendedor;

public interface CodigosActivacionVendedorMapper {
	
	public void insertCodigo(CodigoActivacionVendedor codigo);
	
	public int buscarVendedorPorCodigo(int codigo);
	
	public void borrarCodigo(int id_vendedor);
	
	public boolean existeCodigo(int codigo);

	
}