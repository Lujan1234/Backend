package com.proyecto.integrador.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bancos")
public class Bancos implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBancos;

	private String nomBancos;
	
	public int getIdBancos() {
		return idBancos;
	}
	public void setIdBancos(int idBancos) {
		this.idBancos = idBancos;
	}
	public String getNomBancos() {
		return nomBancos;
	}
	public void setNomBancos(String nomBancos) {
		this.nomBancos = nomBancos;
	}
	


}
