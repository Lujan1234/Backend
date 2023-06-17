package com.proyecto.integrador.servicios;

import java.util.List;
import java.util.Optional;

import com.proyecto.integrador.entidades.OportunidadInversion;

public interface OportunidadInversionService {
	public abstract OportunidadInversion insertaActualizaOportunidadInversion(OportunidadInversion obj);

	public abstract List<OportunidadInversion> listaOportunidadInversionActivas(String noActivo);
	
	public abstract List<OportunidadInversion> listaOportunidadInversionTodos();

	public Optional<OportunidadInversion> buscarxIdOportunidadInversion(int idOpoInv);
}
