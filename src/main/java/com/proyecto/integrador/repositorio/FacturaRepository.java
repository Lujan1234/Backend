package com.proyecto.integrador.repositorio;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.integrador.entidades.Empresa;
import com.proyecto.integrador.entidades.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {
	
	List<Factura> findByEnableNot(String noActivo);

	 List<Factura> findByEmpresa(Empresa empresa);
	 
	 Optional<Factura> findByCodFactura(String codFactura);
	 
	 List<Factura> findByFechaEmisionBetween(Date fechaInicio, Date fechaFin);
}
