package com.proyecto.integrador.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.integrador.entidades.CuentaBancaria;

public interface CuentaBancariaRepository extends JpaRepository<CuentaBancaria, Integer> {
	@Query(value ="SELECT c.* FROM  cuentasbancarias as c where usuario_id = :idUsu AND  c.enable = \"Activo\"",nativeQuery = true)
	List<CuentaBancaria> listaCuentasBancariaXUsuAcT(@Param("idUsu") long idUsu);
}
