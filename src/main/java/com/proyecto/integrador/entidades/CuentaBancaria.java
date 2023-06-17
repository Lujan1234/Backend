package com.proyecto.integrador.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cuentasbancarias")
public class CuentaBancaria implements Serializable {

    private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCuentaBancaria;
	private String nroCuenta;
	private String nroCuentaCci;
	private String cvv;
	private String mes;
	private String year;
	private Double saldo;
	private String enable;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
	private Date  fechaRegistro;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idBancos")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Bancos bancos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idMonedas")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Monedas monedas;
	
	@ManyToOne
	@JoinColumn(name="usuarioId", insertable = false, updatable = false)
	private Usuario usuario;
	private long usuarioId;
	
	public CuentaBancaria() {
		super();

	}

	public CuentaBancaria(int idCuentaBancaria, String nroCuenta, String nroCuentaCci, String cvv, String mes, String year,
			Double saldo, String enable, Date fechaRegistro, Bancos bancos, Monedas monedas,
			Usuario usuario, long usuarioId) {
		super();
		this.idCuentaBancaria = idCuentaBancaria;
		this.nroCuenta = nroCuenta;
		this.nroCuentaCci = nroCuentaCci;
		this.cvv = cvv;
		this.mes = mes;
		this.year = year;
		this.saldo = saldo;
		this.enable = enable;
		this.fechaRegistro = fechaRegistro;
		this.bancos = bancos;
		this.monedas = monedas;
		this.usuario = usuario;
		this.usuarioId = usuarioId;
	}

	public int getIdCuentaBancaria() {
		return idCuentaBancaria;
	}

	public void setIdCuentaBancaria(int idCuentaBancaria) {
		this.idCuentaBancaria = idCuentaBancaria;
	}

	public String getNroCuenta() {
		return nroCuenta;
	}

	
	public String getNroCuentaCci() {
		return nroCuentaCci;
	}

	public void setNroCuentaCci(String nroCuentaCci) {
		this.nroCuentaCci = nroCuentaCci;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}
	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Bancos getBancos() {
		return bancos;
	}

	public void setBancos(Bancos bancos) {
		this.bancos = bancos;
	}


	public Monedas getMonedas() {
		return monedas;
	}

	public void setMonedas(Monedas monedas) {
		this.monedas = monedas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	
}
