package com.proyecto.integrador.servicios;

import java.util.List;
import java.util.Optional;


import com.proyecto.integrador.entidades.Usuario;

public interface UsuarioService {
	public abstract Usuario obtenerUsuario(String username);

	public abstract Optional<Usuario> listaUsuarioPorId(long id);

	public abstract void eliminarUsuario(Long usuarioId);

	public abstract int ExisteporUsuario(String username, long idUsu);

	public abstract int ExisteporCorreo(String correo, long idUsu);

	public abstract int ExisteporDni(String dni, long idUsu);

	public abstract Optional<Usuario> buscarPorDni(String dni);

	public abstract Optional<Usuario> buscarPorCorreo(String correo);

	public abstract Usuario buscarUsuarioPorId(long idUsuario);

	public abstract List<Usuario> listaUsuarios();

	public abstract Usuario insertaActualizaUsuario(Usuario obj);
	
	public abstract List<Usuario> listaDiffNotEnable(String noActivo);
}
