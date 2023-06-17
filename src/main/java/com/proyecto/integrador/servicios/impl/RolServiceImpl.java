package com.proyecto.integrador.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.integrador.entidades.Rol;
import com.proyecto.integrador.repositorio.RolRepository;
import com.proyecto.integrador.servicios.RolService;

@Service
public class RolServiceImpl implements RolService {

	@Autowired
	private RolRepository repositorio;

	@Override
	public Rol buscarporId(Long id) {
		Rol rol = repositorio.findById(id).orElse(null);
		return rol;
	}

	@Override
	public List<Rol> listarRoles() {
		return repositorio.findAll();
	}

	@Override
	public Rol insertarRol(Rol rol) {
		return repositorio.save(rol);
	}
}
