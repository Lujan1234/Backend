package com.proyecto.integrador.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "empresas")
public class Empresa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEmpresa;
	private String representanteLegal;
	private String nomEmpresa;
	private String ruc;
	private String razonSocial;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "America/Lima")
	private Date fechaDeInicioActv;
	private String direccion;
	private String telefono;
	private String correo;
	//private String nroCuentaBancaria;
	private String sector;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "America/Lima")
	private Date fechRegistro;
	private String enable;
	public Empresa() {
		super();
	}
	public Empresa(int idEmpresa, String representanteLegal, String nomEmpresa, String ruc, String razonSocial,
			Date fechaDeInicioActv, String direccion, String telefono, String correo, String sector, Date fechRegistro,
			String enable) {
		super();
		this.idEmpresa = idEmpresa;
		this.representanteLegal = representanteLegal;
		this.nomEmpresa = nomEmpresa;
		this.ruc = ruc;
		this.razonSocial = razonSocial;
		this.fechaDeInicioActv = fechaDeInicioActv;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = correo;
		this.sector = sector;
		this.fechRegistro = fechRegistro;
		this.enable = enable;
	}
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getRepresentanteLegal() {
		return representanteLegal;
	}
	public void setRepresentanteLegal(String representanteLegal) {
		this.representanteLegal = representanteLegal;
	}
	public String getNomEmpresa() {
		return nomEmpresa;
	}
	public void setNomEmpresa(String nomEmpresa) {
		this.nomEmpresa = nomEmpresa;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public Date getFechaDeInicioActv() {
		return fechaDeInicioActv;
	}
	public void setFechaDeInicioActv(Date fechaDeInicioActv) {
		this.fechaDeInicioActv = fechaDeInicioActv;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public Date getFechRegistro() {
		return fechRegistro;
	}
	public void setFechRegistro(Date fechRegistro) {
		this.fechRegistro = fechRegistro;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	
	
	
}
