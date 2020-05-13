package modelo.pojo;

public class ValoracionPost {
	
	Integer id;
	Integer valoracion;
	Integer id_cliente;
	String nombre_autor;
	Integer id_post;
	
	public ValoracionPost() {
		
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



	public String getNombre_autor() {
		return nombre_autor;
	}



	public void setNombre_autor(String nombre_autor) {
		this.nombre_autor = nombre_autor;
	}
	
	

}
