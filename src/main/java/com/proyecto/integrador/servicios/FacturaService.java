package com.proyecto.integrador.servicios;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.proyecto.integrador.entidades.Empresa;
import com.proyecto.integrador.entidades.Factura;

public interface FacturaService {

	public abstract Factura insertarActualizarFactura(Factura obj);

	public abstract List<Factura> listaFactura();

	public abstract Optional<Factura> buscarxId(int id);
	
	public abstract Optional<Factura> buscarxCod(String codFactura);
	
	public abstract List<Factura> listaDifNotEnable(String noActivo);
	
	List<Factura> listarFacturasPorEmpresa(Empresa empresa);
	
	public abstract int obtenerUltimoNumeroFactura();
	
	List<Factura> listarFacturasPorRangoFechas(Date fechaInicio, Date fechaFin);
	
	
}
