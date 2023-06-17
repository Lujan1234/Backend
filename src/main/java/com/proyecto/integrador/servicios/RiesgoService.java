package com.proyecto.integrador.servicios;

import java.util.List;
import java.util.Optional;

import com.proyecto.integrador.entidades.Riesgo;

public interface RiesgoService{	
	public abstract List<Riesgo> listarRiesgo();
	public abstract Optional<Riesgo> buscarRiesgoxId(int idRiesgo);
	public abstract Riesgo insetarRiesgo(Riesgo riesgo);
}