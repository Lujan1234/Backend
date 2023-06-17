package com.proyecto.integrador.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "monedas")
public class Monedas implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMonedas;

	private String nomMonedas;

	private String valorMoneda;


	
	public int getIdMonedas() {
		return idMonedas;
	}
	public void setIdMonedas(int idMonedas) {
		this.idMonedas = idMonedas;
	}
	public String getNomMonedas() {
		return nomMonedas;
	}
	public void setNomMonedas(String nomMonedas) {
		this.nomMonedas = nomMonedas;
	}
	public String getValorMoneda() {
		return valorMoneda;
	}
	public void setValorMoneda(String valorMoneda) {
		this.valorMoneda = valorMoneda;
	}
	
	
	
	

}
