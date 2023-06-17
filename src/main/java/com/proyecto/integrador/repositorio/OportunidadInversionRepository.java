package com.proyecto.integrador.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.integrador.entidades.OportunidadInversion;



public interface OportunidadInversionRepository extends JpaRepository<OportunidadInversion, Integer> {

	List<OportunidadInversion> findByEnableNot(String noActivo);
	
	
}
