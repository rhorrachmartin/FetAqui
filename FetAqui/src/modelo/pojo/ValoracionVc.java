package modelo.pojo;

public class ValoracionVc {
	
	Integer id;
	Integer valoracion;
	Integer id_cliente;
	Integer id_vendedor;
	
	public ValoracionVc() {
		
	}
	
	public ValoracionVc(Integer id, Integer valoracion, Integer id_cliente, Integer id_vendedor) {
		super();
		this.id = id;
		this.valoracion = valoracion;
		this.id_cliente = id_cliente;
		this.id_vendedor = id_vendedor;
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

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}

	public Integer getId_vendedor() {
		return id_vendedor;
	}

	public void setId_vendedor(Integer id_vendedor) {
		this.id_vendedor = id_vendedor;
	}
	
	

}
