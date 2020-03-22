package modelo.pojo;

public class DetallePedido {
	
	Integer id;
	Integer pedido;
	Integer producto;
	Integer precioUnitario;
	Integer cantidad;
	Integer dto;
	
	public DetallePedido() {
		
	}
	
	public DetallePedido(Integer id, Integer pedido, Integer producto, Integer precioUnitario, Integer cantidad,
			Integer dto) {
		super();
		this.id = id;
		this.pedido = pedido;
		this.producto = producto;
		this.precioUnitario = precioUnitario;
		this.cantidad = cantidad;
		this.dto = dto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPedido() {
		return pedido;
	}

	public void setPedido(Integer pedido) {
		this.pedido = pedido;
	}

	public Integer getProducto() {
		return producto;
	}

	public void setProducto(Integer producto) {
		this.producto = producto;
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

	public Integer getDto() {
		return dto;
	}

	public void setDto(Integer dto) {
		this.dto = dto;
	}
	
	
	
}
