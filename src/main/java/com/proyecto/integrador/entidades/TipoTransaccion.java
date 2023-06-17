package com.proyecto.integrador.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipoTransaccion")
public class TipoTransaccion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTipoTransaccion;
	private String tipo;
	
	public TipoTransaccion() {
		super();
	}
	public TipoTransaccion(Long idTipoTransaccion, String tipo) {
		super();
		this.idTipoTransaccion = idTipoTransaccion;
		this.tipo = tipo;
	}
	public Long getIdTipoTransaccion() {
		return idTipoTransaccion;
	}
	public void setIdTipoTransaccion(Long idTipoTransaccion) {
		this.idTipoTransaccion = idTipoTransaccion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
