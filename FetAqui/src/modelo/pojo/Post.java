package modelo.pojo;

public class Post {
	
	Integer id;
	String texto;
	Integer autor;
	
	public Post() {
		
	}
	
	public Post(Integer id, String texto, Integer autor) {
		super();
		this.id = id;
		this.texto = texto;
		this.autor = autor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Integer getAutor() {
		return autor;
	}

	public void setAutor(Integer autor) {
		this.autor = autor;
	}
	
	

}
