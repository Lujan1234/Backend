package com.proyecto.integrador.controladores;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.integrador.entidades.CuentaBancaria;
import com.proyecto.integrador.servicios.CuentaBancariaService;
import com.proyecto.integrador.utils.AppSettings;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class CuentaBancariaController {

	@Autowired
	private CuentaBancariaService cuentabancariaService;

	@GetMapping("/detalleCuentaBancaria/{id}")
	@ResponseBody
	public ResponseEntity<?> buscarPorId(@PathVariable int id, HttpSession session) {
		HashMap<String, Object> response = new HashMap<>();
		try {
			long idUsuAct = (long) session.getAttribute("idUsuActual");
			List<CuentaBancaria> lista = cuentabancariaService.listaCuentaBancariaxIdUsuAct(idUsuAct);
			// Buscar coincidencias con Stream
			boolean existe = lista.stream().anyMatch(cuenta -> cuenta.getIdCuentaBancaria() == id);
			if (existe) {
				Optional<CuentaBancaria> optional = cuentabancariaService.buscarxId(id);
				if (optional.isPresent()) {
					return ResponseEntity.ok(optional.get());
				}
				response.put("mensaje", "Algo salio mal! intentelo más tarde");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			} else {
				response.put("mensaje", "La Cuenta Bancaria con codigo " + id + " no existe");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.put("mensaje", "Hubo un error al buscar la Cuenta Bancaria: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/listarCuentaBancaria")
	@ResponseBody
	public ResponseEntity<List<CuentaBancaria>> listaCuentaBancaria() {
		List<CuentaBancaria> lista = cuentabancariaService.listaCuentaBancariaTodos();
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/user/listarCuentaBancaria")
	@ResponseBody
	public ResponseEntity<List<CuentaBancaria>> listaCuentaBancariaXUsuAct(HttpSession session) {
		long idUsuAct = (long) session.getAttribute("idUsuActual");
		List<CuentaBancaria> lista = cuentabancariaService.listaCuentaBancariaxIdUsuAct(idUsuAct);
		return ResponseEntity.ok(lista);
	}

	// ANTHONY
	@PostMapping("/registrarCuentaBancaria")
	@ResponseBody
	public ResponseEntity<?> insertaCuentaBancaria(@RequestBody CuentaBancaria obj, HttpSession session) {
		HashMap<String, Object> salida = new HashMap<>();
		try {
			long idUsuAct = (long) session.getAttribute("idUsuActual");
			obj.setUsuarioId(idUsuAct);
			obj.setIdCuentaBancaria(0);
			obj.setEnable("Activo");
			obj.setFechaRegistro(new Date());
			obj.setSaldo(45000.00);
			CuentaBancaria objsalida = cuentabancariaService.insertaActualizaCuentaBancaria(obj);
			if (objsalida == null) {
				salida.put("mensaje", "No se registro");
				salida.put("usuario", obj);
				return new ResponseEntity<>(salida,HttpStatus.BAD_REQUEST);
			} else {
				salida.put("mensaje", " Tú cuenta se registro exitosamente");
				salida.put("cuentaBancaria", obj);
			}
		} catch (DataAccessException e) {
			salida.put("mensaje", "Error al registrar el cuenta bancaria");
			salida.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(salida,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(salida);
	}

	@PutMapping("/actualizarCuentaBancaria")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaCuentaBancaria(@RequestBody CuentaBancaria obj,
			HttpSession session) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Optional<CuentaBancaria> optional = cuentabancariaService.listaCuentaBancariaxId(obj.getIdCuentaBancaria());
			if (optional.isPresent()) {
				long idUsuAct = (long) session.getAttribute("idUsuActual");
				obj.setUsuarioId(idUsuAct);
				obj.setEnable("Activo");
				CuentaBancaria objsalida = cuentabancariaService.insertaActualizaCuentaBancaria(obj);
				if (objsalida == null) {
					salida.put("mensaje", "No se actualizo");
				} else {
					salida.put("mensaje", "Se actualizo la cuenta:   " + obj.getIdCuentaBancaria());
				}
			} else {
				salida.put("mensaje", "El id " + obj.getIdCuentaBancaria() + " no existe");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Ocurrio un error" + e.getStackTrace());
		}

		return ResponseEntity.ok(salida);
	}
	@DeleteMapping("user/eliminarCuentaBancaria/{id}")
	@ResponseBody
	public ResponseEntity<?> eliminarUsuario(@PathVariable int id, HttpSession session) {
		HashMap<String, Object> response = new HashMap<>();
		try {
			long idUsuAct = (long) session.getAttribute("idUsuActual");
			List<CuentaBancaria> lista = cuentabancariaService.listaCuentaBancariaxIdUsuAct(idUsuAct);
			// Buscar coincidencias con Stream
			boolean existe = lista.stream().anyMatch(cuenta -> cuenta.getIdCuentaBancaria() == id);
			if (existe) {
				Optional<CuentaBancaria> cuentaEliminada = cuentabancariaService.buscarxId(id);
				cuentaEliminada.get().setEnable("No Activo");
				CuentaBancaria objsalida = cuentabancariaService.insertaActualizaCuentaBancaria(cuentaEliminada.get());
				if (objsalida == null) {
					response.put("mensaje", "No se elimino correctamente la cuenta bancaria");
					return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
				} else {
					response.put("mensaje", "Se elimino la cuenta");
				}
			} else {
				response.put("mensaje", "La Cuenta Bancaria con codigo " + id + " no existe");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			response.put("mensaje", "Hubo un error al eliminar la cuenta bancaria");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(response);
	}

}
