package com.proyecto.integrador.servicios;

import java.util.List;

import com.proyecto.integrador.entidades.OportunidadFactura;

public interface OportunidadFacturaService {

	public abstract List<OportunidadFactura> listaOportunidadFactura();

	public abstract OportunidadFactura insertarOportunidaFactura(OportunidadFactura oFactrura);
}
