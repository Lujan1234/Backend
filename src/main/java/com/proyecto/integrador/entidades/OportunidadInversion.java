package com.proyecto.integrador.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "OportunidadInversion")
public class OportunidadInversion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOportunidad;
	private double rendimiento;
	private double tir;
	private Double monto;
	private Double montoRecaudado;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
	private Date fechaCaducidad;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
	private Date fechaRegistro;
	private String enable;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
	private Date fechaPago;

	@ManyToOne
	@JoinColumn(name = "idEmpresa", insertable = false, updatable = false)
	private Empresa empresa;
	private int idEmpresa;
	@ManyToOne
	@JoinColumn(name = "idUsu", insertable = false, updatable = false)
	private Usuario usuario;
	private long idUsu;

	public OportunidadInversion() {
		super();
	}

	public OportunidadInversion(int idOportunidad, double rendimiento, double tir, Double monto, Double montoRecaudado,
			Date fechaCaducidad, Date fechaRegistro, String enable, Date fechaPago, Empresa empresa, int idEmpresa,
			Usuario usuario, long idUsu) {
		super();
		this.idOportunidad = idOportunidad;
		this.rendimiento = rendimiento;
		this.tir = tir;
		this.monto = monto;
		this.montoRecaudado = montoRecaudado;
		this.fechaCaducidad = fechaCaducidad;
		this.fechaRegistro = fechaRegistro;
		this.enable = enable;
		this.fechaPago = fechaPago;
		this.empresa = empresa;
		this.idEmpresa = idEmpresa;
		this.usuario = usuario;
		this.idUsu = idUsu;
	}

	public int getIdOportunidad() {
		return idOportunidad;
	}

	public void setIdOportunidad(int idOportunidad) {
		this.idOportunidad = idOportunidad;
	}

	public double getRendimiento() {
		return rendimiento;
	}

	public void setRendimiento(double rendimiento) {
		this.rendimiento = rendimiento;
	}

	public double getTir() {
		return tir;
	}

	public void setTir(double tir) {
		this.tir = tir;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Double getMontoRecaudado() {
		return montoRecaudado;
	}

	public void setMontoRecaudado(Double montoRecaudado) {
		this.montoRecaudado = montoRecaudado;
	}

	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public long getIdidUsu() {
		return idUsu;
	}

	public void setIdidUsu(long idUsu) {
		this.idUsu = idUsu;
	}
	
}