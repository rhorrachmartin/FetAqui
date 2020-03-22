package modelo.pojo;

public class ValoracionProducto {
	
	Integer id;
	Integer valoracion;
	Integer cliente;
	Integer producto;
	
	public ValoracionProducto() {
		
	}
	
	public ValoracionProducto(Integer id, Integer valoracion, Integer cliente, Integer producto) {
		super();
		this.id = id;
		this.valoracion = valoracion;
		this.cliente = cliente;
		this.producto = producto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getValoracion() {
		return valoracion;
	}

	public void setValoracion(Integer valoracion) {
		this.valoracion = valoracion;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public Integer getProducto() {
		return producto;
	}

	public void setProducto(Integer producto) {
		this.producto = producto;
	}
	
	
	
}
