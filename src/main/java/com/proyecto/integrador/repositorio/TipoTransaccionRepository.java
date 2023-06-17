package com.proyecto.integrador.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.integrador.entidades.TipoTransaccion;

public interface TipoTransaccionRepository extends JpaRepository<TipoTransaccion, Long> {
	
}
