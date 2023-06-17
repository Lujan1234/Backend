package com.proyecto.integrador.servicios.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.integrador.entidades.Monedas;
import com.proyecto.integrador.repositorio.MonedaRepository;
import com.proyecto.integrador.servicios.MonedasService;

@Service
public class MonedasServiceImpl implements MonedasService{
	
	@Autowired
	MonedaRepository monedaRepository;

	@Override
	public List<Monedas> listarMonedas() {
		return monedaRepository.findAllMonedas();
	}

	@Override
	public Monedas insertarMonedas(Monedas monedas) {
		return monedaRepository.save(monedas);
	}

	@Override
	public Optional<Monedas> buscarxId(int id) {
		return monedaRepository.findById(id);
	}

}
