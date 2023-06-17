package com.proyecto.integrador.servicios;

import java.util.List;

import com.proyecto.integrador.entidades.Transacciones;

public interface TransaccionService {
	public abstract Transacciones insertaTransaccion(Transacciones obj);
	public abstract List<Transacciones> listaTransaccionesTodos();
	public abstract List<Transacciones> listarTransaccionxIdCuentaBancaria(long idUsu,long idCuenta);
	public abstract List<Transacciones> listarTransaccionxIdUsuario(long idUsu); 
}
