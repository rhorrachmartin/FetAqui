package modelo.pojo;

import java.sql.Timestamp;

public class PedidoDetallado {
	
	Integer idPedido;
	Timestamp fechaPedido;
	Timestamp fechaEntrega;
	Integer precioUnitario;
	Integer cantidad;
	double precioFinal;
	String nombreCliente;
	String poblacion;
	String direccion;
	String producto;
	String descripcionProducto;
	Integer idVendedor;
	String estado;
	
	
	public PedidoDetallado() {
		
	}


	public PedidoDetallado(Integer idPedido, Timestamp fechaPedido, Timestamp fechaEntrega, Integer precioUnitario,
			Integer cantidad, double precioFinal, String nombreCliente, String poblacion, String direccion,
			String producto, String descripcionProducto, Integer idVendedor, String estado) {
		super();
		this.idPedido = idPedido;
		this.fechaPedido = fechaPedido;
		this.fechaEntrega = fechaEntrega;
		this.precioUnitario = precioUnitario;
		this.cantidad = cantidad;
		this.precioFinal = precioFinal;
		this.nombreCliente = nombreCliente;
		this.poblacion = poblacion;
		this.direccion = direccion;
		this.producto = producto;
		this.descripcionProducto = descripcionProducto;
		this.idVendedor = idVendedor;
		this.estado = estado;
	}


	public Integer getIdPedido() {
		return idPedido;
	}


	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}


	public Timestamp getFechaPedido() {
		return fechaPedido;
	}


	public void setFechaPedido(Timestamp fechaPedido) {
		this.fechaPedido = fechaPedido;
	}


	public Timestamp getFechaEntrega() {
		return fechaEntrega;
	}


	public void setFechaEntrega(Timestamp fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}


	public Integer getPrecioUnitario() {
		return precioUnitario;
	}


	public void setPrecioUnitario(Integer precioUnitario) {
		this.precioUnitario = precioUnitario;
	}


	public Integer getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public double getPrecioFinal() {
		return precioFinal;
	}


	public void setPrecioFinal(double precioFinal) {
		this.precioFinal = precioFinal;
	}


	public String getNombreCliente() {
		return nombreCliente;
	}


	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
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


	public String getDescripcionProducto() {
		return descripcionProducto;
	}


	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}


	public Integer getIdVendedor() {
		return idVendedor;
	}


	public void setIdVendedor(Integer idVendedor) {
		this.idVendedor = idVendedor;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
