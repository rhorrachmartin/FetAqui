package modelo.pojo;

public class Direccion {
	
	Integer id;
	String direccion;
	Integer id_poblacion;
	String poblacion;
	
	public Direccion() {
		
	}
	
	public Direccion(Integer id, String direccion, Integer id_poblacion, String poblacion) {
		super();
		this.id = id;
		this.direccion = direccion;
		this.id_poblacion = id_poblacion;
		this.poblacion = poblacion;
	}
	
	

	public Integer getId_poblacion() {
		return id_poblacion;
	}

	public void setId_poblacion(Integer id_poblacion) {
		this.id_poblacion = id_poblacion;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
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

	
	
	

}
