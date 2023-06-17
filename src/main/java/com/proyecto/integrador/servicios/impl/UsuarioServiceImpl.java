package com.proyecto.integrador.servicios.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.integrador.entidades.Usuario;
import com.proyecto.integrador.repositorio.UsuarioRepository;
import com.proyecto.integrador.servicios.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario obtenerUsuario(String username) {
		return usuarioRepository.findByUsername(username);
	}
	@Override
	public void eliminarUsuario(Long usuarioId) {
		usuarioRepository.deleteById(usuarioId);
		
	}

	@Override
	public int ExisteporUsuario(String username,long idUsu) {
		return usuarioRepository.existeUsername(username,idUsu);
	}

	@Override
	public int ExisteporCorreo(String correo, long idUsu) {
		return usuarioRepository.existeCorreo(correo, idUsu);
	}

	@Override
	public Optional<Usuario> listaUsuarioPorId(long id) {
		return usuarioRepository.findById(id);
	}


	@Override
	public Usuario buscarUsuarioPorId(long idUsuario) {
		return usuarioRepository.findById(idUsuario).orElse(null);
	}
	@Override
	public List<Usuario> listaUsuarios() {
		return usuarioRepository.findAll();
	}
	@Override
	public Usuario insertaActualizaUsuario(Usuario obj) {
		return usuarioRepository.save(obj);
	}
	@Override
	public int ExisteporDni(String dni, long idUsu) {
		return usuarioRepository.existeDni(dni, idUsu);
	}
	@Override
	public Optional<Usuario> buscarPorDni(String dni) {
		return usuarioRepository.findByDni(dni);
	}
	@Override
	public Optional<Usuario> buscarPorCorreo(String correo) {
		return usuarioRepository.findByCorreo(correo);
	}
	@Override
	public List<Usuario> listaDiffNotEnable(String noActivo) {
		return usuarioRepository.findByEnableNot(noActivo);
	}
}
