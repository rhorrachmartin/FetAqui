package modelo.pojo;

public class Producto {

	Integer id;
	String nombre;
	String descripcion;
	String foto;
	double precio;
	double valoracion;
	Integer stock;
	Integer vendido;
	Integer id_categoria;
	String categoria;
	Integer id_formato;
	Integer venta_online;
	String formato;
	Integer id_vendedor;
	String nombre_vendedor;

	public Producto() {

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

	public double getValoracion() {
		return valoracion;
	}

	public void setValoracion(double valoracion) {
		this.valoracion = valoracion;
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

	public Integer getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(Integer id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getId_formato() {
		return id_formato;
	}

	public void setId_formato(Integer id_formato) {
		this.id_formato = id_formato;
	}

	public Integer getVenta_online() {
		return venta_online;
	}

	public void setVenta_online(Integer venta_online) {
		this.venta_online = venta_online;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public Integer getId_vendedor() {
		return id_vendedor;
	}

	public void setId_vendedor(Integer id_vendedor) {
		this.id_vendedor = id_vendedor;
	}

	public String getNombre_vendedor() {
		return nombre_vendedor;
	}

	public void setNombre_vendedor(String nombre_vendedor) {
		this.nombre_vendedor = nombre_vendedor;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", foto=" + foto
				+ ", precio=" + precio + ", valoracion=" + valoracion + ", stock=" + stock + ", vendido=" + vendido
				+ ", id_categoria=" + id_categoria + ", categoria=" + categoria + ", id_formato=" + id_formato
				+ ", venta_online=" + venta_online + ", formato=" + formato + ", id_vendedor=" + id_vendedor
				+ ", nombre_vendedor=" + nombre_vendedor + "]";
	}
	
	

}
