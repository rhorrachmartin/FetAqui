package modelo.pojo;

import java.sql.Timestamp;

public class Pedido {
	
	Integer id;
	Timestamp fecha_pedido;
	Timestamp fecha_entrega;
	Integer cliente;
	Integer destino;
	String estado;
	
	public Pedido() {
		
	}
	
	public Pedido(Integer id, Timestamp fecha_pedido, Timestamp fecha_entrega, Integer cliente, Integer destino,
			String estado) {
		super();
		this.id = id;
		this.fecha_pedido = fecha_pedido;
		this.fecha_entrega = fecha_entrega;
		this.cliente = cliente;
		this.destino = destino;
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	
	

}
