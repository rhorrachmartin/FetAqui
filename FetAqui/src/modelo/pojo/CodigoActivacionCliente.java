package modelo.pojo;

public class CodigoActivacionCliente {
	
	Integer id;
	Integer cliente;
	
	public CodigoActivacionCliente() {
		
		
	}
	
	public CodigoActivacionCliente(Integer id, Integer vendedor) {
		super();
		this.id = id;
		this.cliente = vendedor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVendedor() {
		return cliente;
	}

	public void setVendedor(Integer vendedor) {
		this.cliente = vendedor;
	}
	
	

}
