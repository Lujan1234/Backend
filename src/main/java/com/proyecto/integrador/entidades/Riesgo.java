package com.proyecto.integrador.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "riesgo")
public class Riesgo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRiesgo;
	private String rango;
	private String descripcion;

	public Riesgo() {
		super();
	}

	public Riesgo(int idRiesgo, String rango, String descripcion) {
		super();
		this.idRiesgo = idRiesgo;
		this.rango = rango;
		this.descripcion = descripcion;
	}

	public int getIdRiesgo() {
		return idRiesgo;
	}

	public void setIdRiesgo(int idRiesgo) {
		this.idRiesgo = idRiesgo;
	}

	public String getRango() {
		return rango;
	}

	public void setRango(String rango) {
		this.rango = rango;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}