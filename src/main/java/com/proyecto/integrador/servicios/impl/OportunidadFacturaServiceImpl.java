package com.proyecto.integrador.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.integrador.entidades.OportunidadFactura;
import com.proyecto.integrador.repositorio.OportunidadFacturaRepository;
import com.proyecto.integrador.servicios.OportunidadFacturaService;

@Service
public class OportunidadFacturaServiceImpl implements OportunidadFacturaService{
	
	@Autowired
	OportunidadFacturaRepository repository;
	
	@Override
	public List<OportunidadFactura> listaOportunidadFactura() {
		
		return repository.findAll();
	}

	@Override
	public OportunidadFactura insertarOportunidaFactura(OportunidadFactura oFactrura) {
		return repository.save(oFactrura);
	}
	
	

}
