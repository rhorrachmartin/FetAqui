package modelo.pojo;

import java.sql.Timestamp;

public class Pedido {
	
	Integer id_pedido;
	Timestamp fecha_pedido;
	Timestamp fecha_entrega;
	Integer cliente;
	Integer id_vendedor;
	Integer destino;
	String estado;
	
	public Pedido() {
		
	}

	public Integer getId() {
		return id_pedido;
	}

	public void setId(Integer id_pedido) {
		this.id_pedido = id_pedido;
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

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public Integer getDestino() {
		return destino;
	}

	public void setDestino(Integer destino) {
		this.destino = destino;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(Integer id_pedido) {
		this.id_pedido = id_pedido;
	}

	public Integer getId_vendedor() {
		return id_vendedor;
	}

	public void setId_vendedor(Integer id_vendedor) {
		this.id_vendedor = id_vendedor;
	}
	
	
	

}
