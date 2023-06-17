package com.proyecto.integrador.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.integrador.entidades.Cartera;

public interface CarteraRespository extends JpaRepository<Cartera, Integer>{
	@Query(value="SELECT c.* FROM  carteras as c where c.id_usu = :idUsu", nativeQuery = true)
	public abstract Cartera bucarCartera(@Param("idUsu") long idUsu);
	
}
