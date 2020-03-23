package modelo.pojo;

import java.sql.Timestamp;

public class Vendedor {
	
	Integer id;
	String nif;
	String nombre;
	String email;
	String password;
	String foto;
	Integer id_direccion;
	Integer telefono;
	Integer activado;
	Timestamp fecha_alta;
	Integer venta_online;
	String direccion;
	String poblacion;
	
	public Vendedor() {
		
	}
	
	public Vendedor(Integer id, String nif, String nombre, String email, String password, String foto,
			Integer id_direccion, Integer telefono, Integer activado, Timestamp fecha_alta, Integer venta_online,
			String direccion, String poblacion) {
		super();
		this.id = id;
		this.nif = nif;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.foto = foto;
		this.id_direccion = id_direccion;
		this.telefono = telefono;
		this.activado = activado;
		this.fecha_alta = fecha_alta;
		this.venta_online = venta_online;
		this.direccion = direccion;
		this.poblacion = poblacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Integer getId_direccion() {
		return id_direccion;
	}

	public void setId_direccion(Integer id_direccion) {
		this.id_direccion = id_direccion;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public Integer getActivado() {
		return activado;
	}

	public void setActivado(Integer activado) {
		this.activado = activado;
	}

	public Timestamp getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(Timestamp fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public Integer getVenta_online() {
		return venta_online;
	}

	public void setVenta_online(Integer venta_online) {
		this.venta_online = venta_online;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	
	
	
	

}
