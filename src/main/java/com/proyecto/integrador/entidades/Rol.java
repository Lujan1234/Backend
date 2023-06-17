package com.proyecto.integrador.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipoUsuario")

public class Rol implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTipoUsu;
	private String tipo;
	
	
	public Rol() {
		super();
	}

	public Rol(Long idTipoUsu, String tipo) {
		super();
		this.idTipoUsu = idTipoUsu;
		this.tipo = tipo;
	
	}

	public Long getIdTipoUsu() {
		return idTipoUsu;
	}

	public void setIdTipoUsu(Long idTipoUsu) {
		this.idTipoUsu = idTipoUsu;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}	
}

