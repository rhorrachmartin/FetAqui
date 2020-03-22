package modelo.pojo;

public class Poblacion {
	
	Integer id;
	String nombre;
	
	public Poblacion() {
		
	}
	
	public Poblacion(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
