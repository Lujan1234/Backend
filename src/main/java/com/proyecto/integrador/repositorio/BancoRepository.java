package com.proyecto.integrador.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.integrador.entidades.Bancos;

public interface BancoRepository extends JpaRepository<Bancos,Integer>{
	
	@Query("from Bancos")
	public List<Bancos> findAllBancos(); 

}
