package com.proyecto.integrador.servicios.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.integrador.entidades.Usuario;
import com.proyecto.integrador.repositorio.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
    @Autowired
    HttpSession session;
    
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = this.usuarioRepository.findByUsername(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario no Encontrado");
			
		}
		session.setAttribute("idUsuActual", usuario.getId());
        session.setAttribute("emailUsuActual", usuario.getCorreo());
        session.setAttribute("userNameActual", usuario.getUsername());
		return usuario;
	}

}
