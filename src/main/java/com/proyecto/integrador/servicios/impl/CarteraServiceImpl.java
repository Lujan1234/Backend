package com.proyecto.integrador.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.integrador.entidades.Cartera;
import com.proyecto.integrador.repositorio.CarteraRespository;
import com.proyecto.integrador.servicios.CarteraService;

@Service
public class CarteraServiceImpl implements CarteraService{
	@Autowired
	private CarteraRespository repo;
	@Override
	public Cartera buscarCartera(long idUsuario) {
		return repo.bucarCartera(idUsuario);
	}

	@Override
	public List<Cartera> listaCarteras() {
		return repo.findAll();
	}

	@Override
	public Cartera insertaActualizaCartera(Cartera obj) {
		return repo.save(obj);
	}

}
