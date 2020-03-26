package modelo.pojo;

public class Cliente {
	
	Integer id_cliente;
	String email;
	String nombre;
	String apellido;
	String telefono;
	String poblacion;
	String direccion;
	Integer idDireccion;
	String password;
	String foto;
	Integer activado;
	
	public Cliente() {
		
	}

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(Integer idDireccion) {
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
	
	

	public Integer getActivado() {
		return activado;
	}

	public void setActivado(Integer activado) {
		this.activado = activado;
	}

	@Override
	public String toString() {
		return "Cliente [id_cliente=" + id_cliente + ", email=" + email + ", nombre=" + nombre + ", apellido="
				+ apellido + ", telefono=" + telefono + ", poblacion=" + poblacion + ", direccion=" + direccion
				+ ", idDireccion=" + idDireccion + ", password=" + password + ", foto=" + foto + "]";
	}

	
	
}
