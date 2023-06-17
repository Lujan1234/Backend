package com.proyecto.integrador.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.integrador.entidades.OportunidadUsuario;
import com.proyecto.integrador.repositorio.OportunidadUsuarioRepository;
import com.proyecto.integrador.servicios.OportunidUsuarioService;

@Service
public class OportunidadUsuarioServiceImpl implements OportunidUsuarioService {
	
	@Autowired
	OportunidadUsuarioRepository Repository;

	@Override
	public List<OportunidadUsuario> listaOportunidadUsuario() {
		
		return Repository.findAll();
	}

	
	
 
}
