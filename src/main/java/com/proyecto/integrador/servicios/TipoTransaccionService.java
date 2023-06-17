package com.proyecto.integrador.servicios;

import java.util.List;
import java.util.Optional;

import com.proyecto.integrador.entidades.TipoTransaccion;

public interface TipoTransaccionService {
	public abstract List<TipoTransaccion> listaTipoTransaccion();

	public abstract TipoTransaccion insertarTipoTransaccion(TipoTransaccion tipo);
	public abstract Optional<TipoTransaccion> buscarxId(long id);
}
