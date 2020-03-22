package modelo.pojo;

public class ValoracionPost {
	
	Integer id;
	Integer valoracion;
	Integer id_cliente;
	Integer id_post;
	
	public ValoracionPost() {
		
	}
	
	public ValoracionPost(Integer id, Integer valoracion, Integer id_cliente, Integer id_post) {
		super();
		this.id = id;
		this.valoracion = valoracion;
		this.id_cliente = id_cliente;
		this.id_post = id_post;
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

	public Integer getId_post() {
		return id_post;
	}

	public void setId_post(Integer id_post) {
		this.id_post = id_post;
	}
	
	

}
