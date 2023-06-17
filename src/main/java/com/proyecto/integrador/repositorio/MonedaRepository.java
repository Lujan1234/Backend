package com.proyecto.integrador.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.integrador.entidades.Monedas;

public interface MonedaRepository extends JpaRepository<Monedas,Integer>{
	
	@Query("from Monedas")
	public List<Monedas> findAllMonedas();
}
