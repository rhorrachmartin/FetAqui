package modelo.pojo;

public class Producto {
	
	Integer id;
	String nombre;
	String descripcion;
	String foto;
	double precio;
	Integer vendedor;
	Integer stock;
	Integer vendido;
	Integer categoria;
	Integer formato;
	Integer venta_online;
	
	public Producto() {
		
	}
	
	public Producto(Integer id, String nombre, String descripcion, String foto, double precio, Integer vendedor,
			Integer stock, Integer vendido, Integer categoria, Integer formato, Integer venta_online) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.foto = foto;
		this.precio = precio;
		this.vendedor = vendedor;
		this.stock = stock;
		this.vendido = vendido;
		this.categoria = categoria;
		this.formato = formato;
		this.venta_online = venta_online;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Integer getVendedor() {
		return vendedor;
	}

	public void setVendedor(Integer vendedor) {
		this.vendedor = vendedor;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getVendido() {
		return vendido;
	}

	public void setVendido(Integer vendido) {
		this.vendido = vendido;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public Integer getFormato() {
		return formato;
	}

	public void setFormato(Integer formato) {
		this.formato = formato;
	}

	public Integer getVenta_online() {
		return venta_online;
	}

	public void setVenta_online(Integer venta_online) {
		this.venta_online = venta_online;
	}
	
	
	

}
