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
@Table(name="transacciones")
public class Transacciones {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idTransaccion;
	private double monto;
	@ManyToOne
	@JoinColumn(name="idCuentaBancaria", insertable = false, updatable = false)
	private CuentaBancaria cuentaBancaria;
	private int idCuentaBancaria;
	
	@ManyToOne
	@JoinColumn(name="idTipoTransaccion", insertable = false, updatable = false)
	private TipoTransaccion tipoTransaccion;
	private long idTipoTransaccion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMMM yyy HH:mm:ss a", timezone = "America/Lima")
	private Date fecha; 
	
	public Transacciones() {
		super();
	}
	
	public Transacciones(long idTransaccion, double monto, CuentaBancaria cuentaBancaria, int idCuentaBancaria,
			TipoTransaccion tipoTransaccion, long idTipoTransaccion, Date fecha) {
		super();
		this.idTransaccion = idTransaccion;
		this.monto = monto;
		this.cuentaBancaria = cuentaBancaria;
		this.idCuentaBancaria = idCuentaBancaria;
		this.tipoTransaccion = tipoTransaccion;
		this.idTipoTransaccion = idTipoTransaccion;
		this.fecha = fecha;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public long getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(long idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public CuentaBancaria getCuentaBancaria() {
		return cuentaBancaria;
	}
	public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}
	public int getIdCuentaBancaria() {
		return idCuentaBancaria;
	}
	public void setIdCuentaBancaria(int idCuentaBancaria) {
		this.idCuentaBancaria = idCuentaBancaria;
	}
	public TipoTransaccion getTipoTransaccion() {
		return tipoTransaccion;
	}
	public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}
	public long getIdTipoTransaccion() {
		return idTipoTransaccion;
	}
	public void setIdTipoTransaccion(long idTipoTransaccion) {
		this.idTipoTransaccion = idTipoTransaccion;
	}
	
}
