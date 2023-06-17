package com.proyecto.integrador.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.integrador.entidades.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

	Optional<Empresa> findByRucAndIdEmpresaNot(String ruc, int idEmpresa);
	
	Optional<Empresa> findByRazonSocialAndIdEmpresaNot(String razonS, int idEmpresa);
	
	//Optional<Empresa> findByNroCuentaBancariaAndIdEmpresaNot(String numCB, int idEmpresa);
	
	Optional<Empresa> findByCorreoAndIdEmpresaNot(String correo, int idEmpresa);

	List<Empresa> findByEnableNot(String noActivo);
	
	 List<Empresa> findByRazonSocialContainingAndEnableNot(String keyword,String noActivo);
}
