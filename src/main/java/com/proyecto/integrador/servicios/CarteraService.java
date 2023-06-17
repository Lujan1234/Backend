package com.proyecto.integrador.servicios;

import java.util.List;

import com.proyecto.integrador.entidades.Cartera;

public interface CarteraService {
	public abstract Cartera buscarCartera(long idUsuario);
	public abstract List<Cartera> listaCarteras();
	public abstract Cartera insertaActualizaCartera(Cartera obj);
}
