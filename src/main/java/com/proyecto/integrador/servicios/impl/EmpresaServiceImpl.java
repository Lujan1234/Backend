package com.proyecto.integrador.servicios.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.integrador.entidades.Empresa;
import com.proyecto.integrador.repositorio.EmpresaRepository;
import com.proyecto.integrador.servicios.EmpresaService;
@Service
public class EmpresaServiceImpl implements EmpresaService{

	@Autowired
	EmpresaRepository repo;

	@Override
	public Empresa insertarActualizarEmpresa(Empresa obj) {
		return repo.save(obj);
	}

	@Override
	public List<Empresa> listaEmpresa() {
		return repo.findAll();
	}

	@Override
	public Optional<Empresa> buscarxId(int id) {
		return repo.findById(id);
	}

	@Override
	public Optional<Empresa> listExistexCorreo(String correo, int idEmpresa) {
		return repo.findByCorreoAndIdEmpresaNot(correo, idEmpresa);
	}

	@Override
	public List<Empresa> listaDiffNotEnable(String noActivo) {
		return repo.findByEnableNot(noActivo);
	}

	@Override
	public Optional<Empresa> listExistexRuc(String ruc, int idEmpresa) {
		return repo.findByRucAndIdEmpresaNot(ruc, idEmpresa);
	}

	/*@Override
	public Optional<Empresa> listExistexNroCuentaBancaria(String numCB, int idEmpresa) {
		return repo.findByNroCuentaBancariaAndIdEmpresaNot(numCB, idEmpresa);
	}*/

	@Override
	public Optional<Empresa> listExistexRazonSocial(String razonS, int idEmpresa) {
		return repo.findByRazonSocialAndIdEmpresaNot(razonS, idEmpresa);
	}

	@Override
	public List<Empresa> buscarxRazonSocialContainsActive(String keyword,String noActivo) {
		return repo.findByRazonSocialContainingAndEnableNot(keyword,noActivo);
	}
	
}
