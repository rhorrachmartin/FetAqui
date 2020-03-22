package modelo.pojo;

public class Cliente {
	
	Integer id;
	String email;
	String nombre;
	String apellido;
	Integer telefono;
	String poblacion;
	String direccion;
	Integer idDireccion;
	String password;
	String foto;
	
	public Cliente() {
		
	}
	
	public Cliente(Integer id, String email, String nombre, String apellido, Integer telefono, Integer idDireccion,
			String password, String foto) {
		super();
		this.id = id;
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.idDireccion = idDireccion;
		this.password = password;
		this.foto = foto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public Integer getDireccion() {
		return idDireccion;
	}

	public void setDireccion(Integer idDireccion) {
		this.idDireccion = idDireccion;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
	
	

}
