package com.proyecto.integrador.servicios;

import java.util.List;
import java.util.Optional;

import com.proyecto.integrador.entidades.Bancos;

public interface BancosService {

	public abstract List<Bancos> listaBancos();
	public abstract Bancos insertarBancos(Bancos bancos);
	public abstract Optional<Bancos> buscarxId(int id);
}
