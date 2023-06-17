package com.proyecto.integrador.servicios;

import java.util.List;
import java.util.Optional;

import com.proyecto.integrador.entidades.Empresa;

public interface EmpresaService {
	public abstract Empresa insertarActualizarEmpresa(Empresa obj);

	public abstract List<Empresa> listaEmpresa();

	public abstract Optional<Empresa> buscarxId(int id);

	public abstract Optional<Empresa> listExistexCorreo(String correo, int idEmpresa);
	
	public abstract Optional<Empresa> listExistexRuc(String ruc, int idEmpresa);
	
	//public abstract Optional<Empresa> listExistexNroCuentaBancaria(String numCB, int idEmpresa);
	
	public abstract Optional<Empresa> listExistexRazonSocial(String razonS, int idEmpresa);

	public abstract List<Empresa> listaDiffNotEnable(String noActivo);
	
	public abstract List<Empresa> buscarxRazonSocialContainsActive(String keyword, String noActivo);

}
