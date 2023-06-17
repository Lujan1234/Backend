package com.proyecto.integrador.servicios.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.integrador.entidades.Riesgo;
import com.proyecto.integrador.repositorio.RiesgoRepository;
import com.proyecto.integrador.servicios.RiesgoService;

@Service
public class RiesgoServiceImpl implements RiesgoService {
	@Autowired
	RiesgoRepository repo;

	@Override
	public List<Riesgo> listarRiesgo() {
		return repo.findAll();
	}

	@Override
	public Optional<Riesgo> buscarRiesgoxId(int idRiesgo) {
		return repo.findById(idRiesgo);
	}

	@Override
	public Riesgo insetarRiesgo(Riesgo riesgo) {
		return repo.save(riesgo);
	}

}
