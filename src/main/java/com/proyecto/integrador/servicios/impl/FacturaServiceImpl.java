package com.proyecto.integrador.servicios.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.integrador.entidades.Empresa;
import com.proyecto.integrador.entidades.Factura;
import com.proyecto.integrador.repositorio.FacturaRepository;
import com.proyecto.integrador.servicios.FacturaService;

@Service
public class FacturaServiceImpl implements FacturaService {

	@Autowired
	FacturaRepository repositorio;
	
	@Override
	public Factura insertarActualizarFactura(Factura obj) {
		
		return repositorio.save(obj);
	}

	@Override
	public List<Factura> listaFactura() {
		
		return repositorio.findAll();
	}

	@Override
	public Optional<Factura> buscarxId(int id) {
		
		return repositorio.findById(id);
	}

	@Override
	public List<Factura> listaDifNotEnable(String noActivo) {
		
		return repositorio.findByEnableNot(noActivo);
	}

	@Override
	public List<Factura> listarFacturasPorEmpresa(Empresa empresa) {
		// TODO Auto-generated method stub
		return repositorio.findByEmpresa(empresa);
	}

	

	@Override
	public int obtenerUltimoNumeroFactura() {
		 int ultimoNumeroFactura = 0;
	        List<Factura> facturas = repositorio.findAll();
	        for (Factura factura : facturas) {
	            String numeroFactura = factura.getCodFactura();
	            String numero = numeroFactura.substring(numeroFactura.indexOf('-') + 1);
	            int numeroInt = Integer.parseInt(numero);
	            if (numeroInt > ultimoNumeroFactura) {
	                ultimoNumeroFactura = numeroInt;
	            }
	        }
	        return ultimoNumeroFactura;
	}

	@Override
	public Optional<Factura> buscarxCod(String codFactura) {
		// TODO Auto-generated method stub
		return repositorio.findByCodFactura(codFactura);
	}

	@Override
	public List<Factura> listarFacturasPorRangoFechas(Date fechaInicio, Date fechaFin) {
		// TODO Auto-generated method stub
		return repositorio.findByFechaEmisionBetween(fechaInicio, fechaFin);
	}


	



}
