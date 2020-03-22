package modelo.pojo;

public class Direccion {
	
	Integer id;
	String direccion;
	Integer poblacion;
	
	public Direccion() {
		
	}
	
	public Direccion(Integer id, String direccion, Integer poblacion) {
		super();
		this.id = id;
		this.direccion = direccion;
		this.poblacion = poblacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(Integer poblacion) {
		this.poblacion = poblacion;
	}
	
	

}
