package com.proyecto.integrador.entidades;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OportunidadUsuario")
public class OportunidadUsuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOportunidadUsuario;
	
	@ManyToOne
	@JoinColumn(name="idOportunidad", insertable = false, updatable = false)
	private OportunidadInversion oportunidadInversion;
	private int idOportunidad;
	
	private int montoInvertido;
	
	@ManyToOne
	@JoinColumn(name="usuarioId", insertable = false, updatable = false)
	private Usuario usuario;
	private long usuarioId;
	public OportunidadUsuario(int idOportunidadUsuario, OportunidadInversion oportunidadInversion, int idOportunidad,
			int montoInvertido, Usuario usuario, long usuarioId) {
		super();
		this.idOportunidadUsuario = idOportunidadUsuario;
		this.oportunidadInversion = oportunidadInversion;
		this.idOportunidad = idOportunidad;
		this.montoInvertido = montoInvertido;
		this.usuario = usuario;
		this.usuarioId = usuarioId;
	}
	public int getIdOportunidadUsuario() {
		return idOportunidadUsuario;
	}
	public void setIdOportunidadUsuario(int idOportunidadUsuario) {
		this.idOportunidadUsuario = idOportunidadUsuario;
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
	public int getMontoInvertido() {
		return montoInvertido;
	}
	public void setMontoInvertido(int montoInvertido) {
		this.montoInvertido = montoInvertido;
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
