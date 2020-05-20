package modelo.pojo;

import java.sql.Timestamp;

public class PedidoDetallado {

	Integer id_pedido;
	Integer id_detalle;
	Timestamp fecha_pedido;
	Timestamp fecha_entrega;
	double precio_unidad;
	Integer cantidad;
	double precio_final;
	String cliente;
	String poblacion;
	String direccion;
	String producto;
	Integer id_vendedor;
	Integer id_cliente;
	String estado;
	String vendedor;
	String foto;

	public PedidoDetallado() {
		
	}

	public Integer getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(Integer id_pedido) {
		this.id_pedido = id_pedido;
	}	

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}

	public Integer getId_detalle() {
		return id_detalle;
	}

	public void setId_detalle(Integer id_detalle) {
		this.id_detalle = id_detalle;
	}

	public Timestamp getFecha_pedido() {
		return fecha_pedido;
	}

	public void setFecha_pedido(Timestamp fecha_pedido) {
		this.fecha_pedido = fecha_pedido;
	}

	public Timestamp getFecha_entrega() {
		return fecha_entrega;
	}

	public void setFecha_entrega(Timestamp fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}

	public double getPrecio_unidad() {
		return precio_unidad;
	}

	public void setPrecio_unidad(double precio_unidad) {
		this.precio_unidad = precio_unidad;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio_final() {
		return precio_final;
	}

	public void setPrecio_final(double precio_final) {
		this.precio_final = precio_final;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
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

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}
	
	

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Integer getId_vendedor() {
		return id_vendedor;
	}

	public void setId_vendedor(Integer id_vendedor) {
		this.id_vendedor = id_vendedor;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	@Override
	public String toString() {
		return "PedidoDetallado [id_pedido=" + id_pedido + ", fecha_pedido=" + fecha_pedido + ", fecha_entrega="
				+ fecha_entrega + ", precio_unidad=" + precio_unidad + ", cantidad=" + cantidad + ", precio_final="
				+ precio_final + ", cliente=" + cliente + ", poblacion=" + poblacion + ", direccion=" + direccion
				+ ", producto=" + producto + ", id_vendedor=" + id_vendedor + ", estado=" + estado + ", vendedor="
				+ vendedor + "]";
	}
	
	

}
