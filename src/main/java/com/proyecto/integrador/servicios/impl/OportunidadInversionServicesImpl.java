package com.proyecto.integrador.servicios.impl;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.integrador.entidades.OportunidadInversion;
import com.proyecto.integrador.repositorio.OportunidadInversionRepository;
import com.proyecto.integrador.servicios.OportunidadInversionService;


@Service
public class OportunidadInversionServicesImpl implements OportunidadInversionService{
	
	@Autowired
	private OportunidadInversionRepository repositorio;

	@Override
	public OportunidadInversion insertaActualizaOportunidadInversion(OportunidadInversion obj) {
		return repositorio.save(obj);
	}

	@Override
	public List<OportunidadInversion> listaOportunidadInversionActivas(String noActivo) {
		return repositorio.findByEnableNot(noActivo);
	}

	@Override
	public Optional<OportunidadInversion> buscarxIdOportunidadInversion(int idOpoInv) {
		return repositorio.findById(idOpoInv);
	}

	@Override
	public List<OportunidadInversion> listaOportunidadInversionTodos() {
		return repositorio.findAll();
	}
	
}
