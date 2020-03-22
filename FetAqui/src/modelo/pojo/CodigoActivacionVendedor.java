package modelo.pojo;

public class CodigoActivacionVendedor {
	
	Integer id;
	Integer vendedor;
	
	public CodigoActivacionVendedor() {
		
		
	}
	
	public CodigoActivacionVendedor(Integer id, Integer vendedor) {
		super();
		this.id = id;
		this.vendedor = vendedor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVendedor() {
		return vendedor;
	}

	public void setVendedor(Integer vendedor) {
		this.vendedor = vendedor;
	}
	
	

}
