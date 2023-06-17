package com.proyecto.integrador.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OportunidadFactura")
public class OportunidadFactura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOportunidadFactura;
	
	@ManyToOne
	@JoinColumn(name="idOportunidad", insertable = false, updatable = false)
	private OportunidadInversion oportunidadInversion;
	private int idOportunidad;
	
	@ManyToOne
	@JoinColumn(name="idFactura", insertable = false, updatable = false)
	private Factura factura;
	private int idFactura;
	
	
	public OportunidadFactura() {
		super();
	}
	public OportunidadFactura(int idOportunidadFactura, OportunidadInversion oportunidadInversion, int idOportunidad,
			Factura factura, int idFactura) {
		super();
		this.idOportunidadFactura = idOportunidadFactura;
		this.oportunidadInversion = oportunidadInversion;
		this.idOportunidad = idOportunidad;
		this.factura = factura;
		this.idFactura = idFactura;
	}
	public int getIdOportunidadFactura() {
		return idOportunidadFactura;
	}
	public void setIdOportunidadFactura(int idOportunidadFactura) {
		this.idOportunidadFactura = idOportunidadFactura;
	}
	public OportunidadInversion getOportunidadInversion() {
		return oportunidadInversion;
	}
	public void setOportunidadInversion(OportunidadInversion oportunidadInversion) {
		this.oportunidadInversion = oportunidadInversion;
	}
	
	public int getIdOportunidad() {
		return idOportunidad;
	}
	public void setIdOportunidad(int idOportunidad) {
		this.idOportunidad = idOportunidad;
	}
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	
}
