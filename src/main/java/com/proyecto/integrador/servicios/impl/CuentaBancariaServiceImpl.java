package com.proyecto.integrador.servicios.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.integrador.entidades.CuentaBancaria;
import com.proyecto.integrador.repositorio.CuentaBancariaRepository;
import com.proyecto.integrador.servicios.CuentaBancariaService;

@Service
public class CuentaBancariaServiceImpl implements CuentaBancariaService {
	
	@Autowired
	private CuentaBancariaRepository repositorio;
	
	@Override
	public CuentaBancaria insertaActualizaCuentaBancaria(CuentaBancaria obj) {
		return repositorio.save(obj);
	}
	@Override
	public List<CuentaBancaria> listaCuentaBancariaTodos() {
		return repositorio.findAll();
	}
	@Override
	public Optional<CuentaBancaria> listaCuentaBancariaxId(int idCuentabancaria) {
		
		return repositorio.findById(idCuentabancaria);
	}
	@Override
	public Optional<CuentaBancaria> buscarxId(int id) {
		return repositorio.findById(id);
	}
	@Override
	public List<CuentaBancaria> listaCuentaBancariaxIdUsuAct(long id) {
		return repositorio.listaCuentasBancariaXUsuAcT(id);
	}
	
	
	
	
	

}
