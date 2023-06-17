package com.proyecto.integrador.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Carteras")
public class Cartera {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCartera;
	private double saldo;
	@ManyToOne
	@JoinColumn(name = "idUsu", insertable = false, updatable = false)
	private Usuario usuario;
	private long idUsu;
	
	public Cartera() {
		super();
	}

	public Cartera(int idCartera, double saldo, Usuario usuario, long idUsu) {
		super();
		this.idCartera = idCartera;
		this.saldo = saldo;
		this.usuario = usuario;
		this.idUsu = idUsu;
	}

	public int getIdCartera() {
		return idCartera;
	}

	public void setIdCartera(int idCartera) {
		this.idCartera = idCartera;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public long getIdUsu() {
		return idUsu;
	}

	public void setIdUsu(long idUsu) {
		this.idUsu = idUsu;
	}
}
