package com.proyecto.integrador.entidades;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nombre;
	private String apellidoPa;
	private String apellidoMa;
	private String telefono;
	private String correo;
	private String username;
	private String password;
	private String foto;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
	private Date fecha;
	private String dni;;
	private String enable;
	
	@ManyToOne
	@JoinColumn(name="idTipoUsu", insertable = false, updatable = false)
	private Rol tiporol;
	private long idTipoUsu;
	

	
	
	


	public Usuario() {
		super();
	}



	public Usuario(long id, String nombre, String apellidoPa, String apellidoMa, String telefono, String correo,
			String username, String password, String foto, Date fecha, String dni, String enable, Rol tiporol, long idTipoUsu ) {
			 
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidoPa = apellidoPa;
		this.apellidoMa = apellidoMa;
		this.telefono = telefono;
		this.correo = correo;
		this.username = username;
		this.password = password;
		this.foto = foto;
		this.fecha = fecha;
		this.dni = dni;
		this.enable = enable;
		this.tiporol = tiporol;
		this.idTipoUsu = idTipoUsu;
	
	}



	


	public Rol getTiporol() {
		return tiporol;
	}



	public void setTiporol(Rol tiporol) {
		this.tiporol = tiporol;
	}



	public long getIdTipoUsu() {
		return idTipoUsu;
	}



	public void setIdTipoUsu(long idTipoUsu) {
		this.idTipoUsu = idTipoUsu;
	}


	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellidoPa() {
		return apellidoPa;
	}



	public void setApellidoPa(String apellidoPa) {
		this.apellidoPa = apellidoPa;
	}



	public String getApellidoMa() {
		return apellidoMa;
	}



	public void setApellidoMa(String apellidoMa) {
		this.apellidoMa = apellidoMa;
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



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getFoto() {
		return foto;
	}



	public void setFoto(String foto) {
		this.foto = foto;
	}



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date date) {
		this.fecha = date;
	}



	public String getDni() {
		return dni;
	}



	public void setDni(String dni) {
		this.dni = dni;
	}



	public String getEnable() {
		return enable;
	}



	public void setEnable(String enable) {
		this.enable = enable;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    Set<GrantedAuthority> authorities = new HashSet<>();
	    authorities.add(new SimpleGrantedAuthority(tiporol.getTipo()));
	    return authorities;
	}



//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Set<Authority> autoridades = new HashSet<>();
//		this.usuarioRoles.forEach(usuarioRol -> {
//			autoridades.add(new Authority(usuarioRol.getRol().getTipo()));
//		});
//		return autoridades;
//	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	

	
}