package com.proyecto.integrador.servicios;

import java.util.List;

import com.proyecto.integrador.entidades.Rol;

public interface RolService {

	public Rol buscarporId(Long id);

	public abstract List<Rol> listarRoles();

	public abstract Rol insertarRol(Rol rol);
}