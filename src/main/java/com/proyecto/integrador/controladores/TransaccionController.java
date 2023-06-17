package com.proyecto.integrador.controladores;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.integrador.entidades.Cartera;
import com.proyecto.integrador.entidades.CuentaBancaria;
import com.proyecto.integrador.entidades.TipoTransaccion;
import com.proyecto.integrador.entidades.Transacciones;
import com.proyecto.integrador.servicios.CarteraService;
import com.proyecto.integrador.servicios.CuentaBancariaService;
import com.proyecto.integrador.servicios.TipoTransaccionService;
import com.proyecto.integrador.servicios.TransaccionService;
import com.proyecto.integrador.utils.AppSettings;

//hecho por bruno
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class TransaccionController {
	@Autowired
	private TransaccionService transaccionService;
	@Autowired
	private TipoTransaccionService tipoTransaccionService;
	@Autowired
	private CuentaBancariaService cuentaBancariaService;
	@Autowired
	private CarteraService carteraService;

	@GetMapping("/listaTipoTransacciones")
	@ResponseBody
	public ResponseEntity<?> listaTipoTransacciones() {
		List<TipoTransaccion> lista = tipoTransaccionService.listaTipoTransaccion();
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/admin/listaTransacciones")
	@ResponseBody
	public ResponseEntity<List<Transacciones>> listaTransacciones() {
		List<Transacciones> lista = transaccionService.listaTransaccionesTodos();
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/user/listaTransacciones")
	@ResponseBody
	public ResponseEntity<List<Transacciones>> listaTransaccionesxUsuarioActual(HttpSession session) {
		long idUsuAct = (long) session.getAttribute("idUsuActual");
		List<Transacciones> lista = transaccionService.listarTransaccionxIdUsuario(idUsuAct);
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/user/listaTransacciones/{id}")
	@ResponseBody
	public ResponseEntity<?> listaTransaccionesxIdCuentaBancaria(HttpSession session, @PathVariable long id) {
		HashMap<String, Object> response = new HashMap<>();
		try {
			long idUsuAct = (long) session.getAttribute("idUsuActual");
			List<Transacciones> lista = transaccionService.listarTransaccionxIdCuentaBancaria(idUsuAct, id);
			return ResponseEntity.ok(lista);
		} catch (Exception e) {
			response.put("mensaje", "Hubo un error al listar: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/deposito")
	@ResponseBody
	public ResponseEntity<?> depositarCuenta(@RequestBody Transacciones transaccion, HttpSession session) {
		HashMap<String, Object> response = new HashMap<>();
		try {
			long idUsuAct = (long) session.getAttribute("idUsuActual");
			List<CuentaBancaria> lista = cuentaBancariaService.listaCuentaBancariaxIdUsuAct(idUsuAct);
			int IdCuentaB = transaccion.getIdCuentaBancaria();
			// Buscar coincidencias con Stream
			boolean existe = lista.stream().anyMatch(cuenta -> cuenta.getIdCuentaBancaria() == IdCuentaB);
			if (existe) {
				// Buscar cartera del usuario actual
				Cartera cartera = carteraService.buscarCartera(idUsuAct);
				double saldoActualCartera = cartera.getSaldo();
				// Buscar cuenta bancaria
				Optional<CuentaBancaria> optional = cuentaBancariaService.buscarxId(IdCuentaB);
				CuentaBancaria cuenta = optional.get();
				double saldoActualCB = cuenta.getSaldo();
				if (saldoActualCB < transaccion.getMonto()) {
					response.put("mensaje", "No cuenta con saldo suficiente en su cuenta Bancaria!");
					return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
				} else {
					// deposito
					double nuevoSaldoCB = saldoActualCB - transaccion.getMonto();
					cuenta.setSaldo(nuevoSaldoCB);
					cuentaBancariaService.insertaActualizaCuentaBancaria(cuenta);
					double nuevoSaldoCartera = saldoActualCartera + transaccion.getMonto();
					cartera.setSaldo(nuevoSaldoCartera);
					carteraService.insertaActualizaCartera(cartera);
				}
				// Guardar la transaccion
				transaccion.setIdTipoTransaccion(1);
				transaccion.setFecha(new Date());
				transaccionService.insertaTransaccion(transaccion);
				response.put("mensaje", "Deposito realizado con exito");
				return ResponseEntity.ok(response);
			} else {
				response.put("mensaje", "La cuenta bancaria con Id: " + IdCuentaB + " no existe");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			response.put("mensaje", "Hubo un error al realizar la transaccion: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/retiro")
	@ResponseBody
	public ResponseEntity<?> retirarCuenta(@RequestBody Transacciones transaccion, HttpSession session) {
		HashMap<String, Object> response = new HashMap<>();
		try {
			long idUsuAct = (long) session.getAttribute("idUsuActual");
			List<CuentaBancaria> lista = cuentaBancariaService.listaCuentaBancariaxIdUsuAct(idUsuAct);
			int IdCuentaB = transaccion.getIdCuentaBancaria();
			// Buscar coincidencias con Stream
			boolean existe = lista.stream().anyMatch(cuenta -> cuenta.getIdCuentaBancaria() == IdCuentaB);
			if (existe) {
				// Buscar cartera del usuario actual
				Cartera cartera = carteraService.buscarCartera(idUsuAct);
				double saldoActualCartera = cartera.getSaldo();
				// Buscar cuenta bancaria
				Optional<CuentaBancaria> optional = cuentaBancariaService.buscarxId(IdCuentaB);
				CuentaBancaria cuenta = optional.get();
				double saldoActualCB = cuenta.getSaldo();
				if (saldoActualCartera < transaccion.getMonto()) {
					response.put("mensaje", "No cuenta con saldo suficiente en su cartera!");
					return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
				} else {
					// retiro
					double nuevoSaldoCB = saldoActualCB + transaccion.getMonto();
					cuenta.setSaldo(nuevoSaldoCB);
					cuentaBancariaService.insertaActualizaCuentaBancaria(cuenta);
					double nuevoSaldoCartera = saldoActualCartera - transaccion.getMonto();
					cartera.setSaldo(nuevoSaldoCartera);
					carteraService.insertaActualizaCartera(cartera);
				}
				// Guardar la transaccion
				transaccion.setIdTipoTransaccion(2);
				transaccion.setFecha(new Date());
				transaccionService.insertaTransaccion(transaccion);
				response.put("mensaje", "Retiro realizado con exito");
				return ResponseEntity.ok(response);
			} else {
				response.put("mensaje", "La cuenta bancaria con Id: " + IdCuentaB + " no existe");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			response.put("mensaje", "Hubo un error al realizar la transaccion: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
