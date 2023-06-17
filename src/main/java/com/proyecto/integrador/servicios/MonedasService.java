package com.proyecto.integrador.servicios;

import java.util.List;
import java.util.Optional;

import com.proyecto.integrador.entidades.Monedas;

public interface MonedasService {

	public List<Monedas> listarMonedas();
	public abstract Monedas insertarMonedas(Monedas monedas);
	public abstract Optional<Monedas> buscarxId(int id);
}
