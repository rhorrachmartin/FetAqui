package modelo.pojo;

public class DetallePedido {
	
	Integer id_detalle;
	Integer id_pedido;
	Integer id_producto;
	Double precio_unidad;
	Integer cantidad;
	Integer dto;
	
	public DetallePedido() {
		
	}
	
	public DetallePedido(Integer id_detalle, Integer id_pedido, Integer id_producto, Double precio_unidad, Integer cantidad,
			Integer dto) {
		super();
		this.id_detalle = id_detalle;
		this.id_pedido = id_pedido;
		this.id_producto = id_producto;
		this.precio_unidad = precio_unidad;
		this.cantidad = cantidad;
		this.dto = dto;
	}

	public Integer getId() {
		return id_detalle;
	}

	public void setId(Integer id_detalle) {
		this.id_detalle = id_detalle;
	}

	public Integer getPedido() {
		return id_pedido;
	}

	public void setPedido(Integer id_pedido) {
		this.id_pedido = id_pedido;
	}

	public Integer getProducto() {
		return id_producto;
	}

	public void setProducto(Integer id_producto) {
		this.id_producto = id_producto;
	}

	public Double getPrecioUnitario() {
		return precio_unidad;
	}

	public void setPrecioUnitario(Double precio_unidad) {
		this.precio_unidad = precio_unidad;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getDto() {
		return dto;
	}

	public void setDto(Integer dto) {
		this.dto = dto;
	}
	
	
	
}
