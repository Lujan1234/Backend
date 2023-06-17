package com.proyecto.integrador.controladores;

import java.util.ArrayList;
import java.util.Calendar;
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

import com.proyecto.integrador.entidades.Factura;
import com.proyecto.integrador.entidades.OportunidadFactura;
import com.proyecto.integrador.entidades.OportunidadInversion;
import com.proyecto.integrador.servicios.FacturaService;
import com.proyecto.integrador.servicios.OportunidadFacturaService;
import com.proyecto.integrador.servicios.OportunidadInversionService;
import com.proyecto.integrador.utils.AppSettings;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class OportunidadInversionController {

	@Autowired
	private OportunidadInversionService oportunidadInversionservice;
	@Autowired
	private OportunidadFacturaService oportunidadFacturanservice;
	@Autowired
	private FacturaService facturanservice;
	// Para almacenar las facturas
	List<Factura> facturaList = new ArrayList<Factura>();

	@PostMapping("/addFactura")
	@ResponseBody
	public ResponseEntity<?> addFacturas(@RequestBody Map<String, Object> request) {
		HashMap<String, Object> salida = new HashMap<>();
		try {
			int idFactura = (int) request.get("idFactura");
			Optional<Factura> facturaExiste = facturanservice.buscarxId(idFactura);
			if (facturaExiste.isEmpty()) {
				salida.put("mensaje", "No existe Factura con id: " + idFactura);
				return new ResponseEntity<>(salida, HttpStatus.BAD_REQUEST);
			} else {
				Boolean ingresado = facturaList.stream().anyMatch(f -> f.getIdFactura() == idFactura);
				Factura factura = facturaExiste.get();
				if (!ingresado) {
					facturaList.add(factura);
				} else {
					salida.put("mensaje", "Ups, parece que ya Ingreso esa factura");
					salida.put("Factura", factura);
					return new ResponseEntity<>(salida, HttpStatus.CONFLICT);
				}
				salida.put("Facturas", facturaList);
				return ResponseEntity.ok(salida);
			}

		} catch (DataAccessException e) {
			salida.put("mensaje", "Error al agregar factura a la lista");
			salida.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(salida, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/deleteFacturalist/{id}")
	@ResponseBody
	public ResponseEntity<?> eliminarFacturaList(@PathVariable Integer id) {
		HashMap<String, Object> salida = new HashMap<>();
		try {
			if(facturaList.isEmpty()) {
				salida.put("mensaje", "La lista esta vacia!");
				return new ResponseEntity<>(salida, HttpStatus.CONFLICT);
			}
			Boolean contiene = facturaList.stream().anyMatch(f -> f.getIdFactura() == id);
			if(!contiene) {
				salida.put("mensaje", "La lista de facturas no contiene un registro con id: "+id);
				return new ResponseEntity<>(salida, HttpStatus.CONFLICT);
			}
			for (int i = 0; i < facturaList.size(); i++) {
			    Factura f = facturaList.get(i);
			    if (f.getIdFactura() == id) {
			        facturaList.remove(i);
			        break;
			    }
			}
			salida.put("Facturas", facturaList);
			return ResponseEntity.ok(salida);
		} catch (DataAccessException e) {
			salida.put("mensaje", "Error al eliminar factura de la lista");
			salida.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(salida, HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/user/listarOportunidadInversion")
	@ResponseBody
	public ResponseEntity<List<OportunidadInversion>> listaOportunidadInversionActive() {
		List<OportunidadInversion> lista = oportunidadInversionservice.listaOportunidadInversionActivas("No Activo");
		return ResponseEntity.ok(lista);
	}
	@GetMapping("/user/buscarOportunidades/{idOportunidadIn}")
	@ResponseBody
	public ResponseEntity<?> buscarPorId(@PathVariable int idOportunidadIn){
		HashMap<String, Object> response = new HashMap<>();
		try {
			Optional<OportunidadInversion> oportunidad = oportunidadInversionservice.buscarxIdOportunidadInversion(idOportunidadIn);
			if(oportunidad == null) {
				response.put("mensaje", "No se encontro la Oportunidad con el ID " + idOportunidadIn +" en el Sistema");
				return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
			}else {
				
				
				response.put("mensaje", "Oportunidad de inversión encontrada");
				return ResponseEntity.ok(oportunidad);
			}
		}catch(Exception e) {
			response.put("mensaje", "Hubo un error al buscar al usuario: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/listarOportunidadInversion")
	@ResponseBody
	public ResponseEntity<List<OportunidadInversion>> listaOportunidadInversion() {
		List<OportunidadInversion> lista = oportunidadInversionservice.listaOportunidadInversionTodos();
		return ResponseEntity.ok(lista);
	}

	@PostMapping("/insertaOportunidadInversion")
	@ResponseBody
	public ResponseEntity<?> insertaOportunidadInversion(@RequestBody OportunidadInversion obj, HttpSession session) {
		HashMap<String, Object> salida = new HashMap<>();
		try {
			if (facturaList.isEmpty()) {
				salida.put("mensaje", "Debe agregar facturas para poder registrar la Oportunidad de Inversion!");
				salida.put("ListaFacturas", facturaList);
				return new ResponseEntity<>(salida, HttpStatus.CONFLICT);
			} else {
				long idUsuAct = (long) session.getAttribute("idUsuActual");
				obj.setIdidUsu(idUsuAct);
				obj.setEnable("Activo");
				obj.setFechaRegistro(new Date());
				obj.setMontoRecaudado(0.0);
				// Crear una instancia de Calendar
				Calendar calendar = Calendar.getInstance();

				// Establecer la fecha original en el Calendar
				calendar.setTime(obj.getFechaCaducidad());

				// Restar 2 mes
				calendar.add(Calendar.MONTH, -2);

				// Obtener la fecha resultante
				Date fechaPago = calendar.getTime();

				obj.setFechaPago(fechaPago);

				OportunidadInversion objsalida = oportunidadInversionservice.insertaActualizaOportunidadInversion(obj);
				if (objsalida == null) {
					salida.put("mensaje", "No se registro la oportunidad de inversion");
					salida.put("oportunidadInversion", obj);
					return new ResponseEntity<>(salida, HttpStatus.BAD_REQUEST);
				} else {
					int idOpInver = objsalida.getIdOportunidad();
					for (Factura f : facturaList) {
						OportunidadFactura oFactura = new OportunidadFactura();
						oFactura.setIdFactura(f.getIdFactura());
						oFactura.setIdOportunidad(idOpInver);
						OportunidadFactura oFacturaSalida = oportunidadFacturanservice
								.insertarOportunidaFactura(oFactura);
						if (oFacturaSalida == null) {
							salida.put("mensaje", "Error al registrar facturas!");
							salida.put("ListaFacturas", facturaList);
							return new ResponseEntity<>(salida, HttpStatus.CONFLICT);
						}
					}
					salida.put("mensaje", "La oportunidad de inversion se registro exitosamente!");
					salida.put("OportunidadInversion", objsalida);
					return ResponseEntity.ok(salida);
				}
			}
		} catch (DataAccessException e) {
			salida.put("mensaje", "Error al registrar el Oportunidad Inversion");
			salida.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(salida, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/actualizarOportunidadInversion")
	@ResponseBody
	public ResponseEntity<?> actualizaOportunidadInversion(@RequestBody OportunidadInversion obj) {
		HashMap<String, Object> salida = new HashMap<>();
		try {
			int idOportunidad = obj.getIdOportunidad();
			Optional<OportunidadInversion> existeOportunidad = oportunidadInversionservice
					.buscarxIdOportunidadInversion(idOportunidad);
			if (existeOportunidad.isEmpty()) {
				salida.put("mensaje", "No existe oportunidad con Id: " + idOportunidad);
				return new ResponseEntity<>(salida, HttpStatus.BAD_REQUEST);
			} else {
				OportunidadInversion objOportunidad = existeOportunidad.get();
				obj.setIdOportunidad(objOportunidad.getIdOportunidad());
				obj.setEnable(objOportunidad.getEnable());
				obj.setFechaRegistro(objOportunidad.getFechaRegistro());
				// Crear una instancia de Calendar
				Calendar calendar = Calendar.getInstance();
				// Establecer la fecha original en el Calendar
				calendar.setTime(obj.getFechaCaducidad());
				// Restar 2 mes
				calendar.add(Calendar.MONTH, -2);
				// Obtener la fecha resultante
				Date fechaPago = calendar.getTime();
				obj.setFechaPago(fechaPago);
				obj.setMontoRecaudado(objOportunidad.getMontoRecaudado());
				// obj.setUsuarioId(objOportunidad.getUsuarioId());
				OportunidadInversion objsalida = oportunidadInversionservice.insertaActualizaOportunidadInversion(obj);
				if (objsalida == null) {
					salida.put("mensaje", "No se actualizo la oportunidad de inversion");
					salida.put("oportunidadInversion", obj);
					return new ResponseEntity<>(salida, HttpStatus.BAD_REQUEST);
				} else {
					salida.put("mensaje", "La oportunidad de inversion se actualizo exitosamente!");
					salida.put("OportunidadInversion", obj);
					return ResponseEntity.ok(salida);
				}
			}
		} catch (DataAccessException e) {
			salida.put("mensaje", "Error al actualizar el Oportunidad Inversion");
			salida.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(salida, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/eliminarOportunidadInversion/{id}")
	@ResponseBody
	public ResponseEntity<?> actualizaOportunidadInversion(@PathVariable int id) {
		HashMap<String, Object> salida = new HashMap<>();
		try {
			Optional<OportunidadInversion> existeOportunidad = oportunidadInversionservice
					.buscarxIdOportunidadInversion(id);
			if (existeOportunidad.isEmpty()) {
				salida.put("mensaje", "No existe oportunidad con Id: " + id);
				return new ResponseEntity<>(salida, HttpStatus.BAD_REQUEST);
			} else {
				OportunidadInversion objOportunidad = existeOportunidad.get();
				objOportunidad.setEnable("No Activo");
				OportunidadInversion objsalida = oportunidadInversionservice
						.insertaActualizaOportunidadInversion(objOportunidad);
				if (objsalida == null) {
					salida.put("mensaje", "No se elimino la oportunidad de inversion");
					salida.put("oportunidadInversion", objOportunidad);
					return new ResponseEntity<>(salida, HttpStatus.BAD_REQUEST);
				} else {
					salida.put("mensaje", "La oportunidad de inversion se elimino exitosamente!");
					salida.put("OportunidadInversion", objOportunidad);
					return ResponseEntity.ok(salida);
				}
			}
		} catch (DataAccessException e) {
			salida.put("mensaje", "Error al eliminar el Oportunidad Inversion");
			salida.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(salida, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/detalleOportunidadInversion/{id}")
	@ResponseBody
	public ResponseEntity<?> detalleOportunidad(@PathVariable int id) {
		HashMap<String, Object> salida = new HashMap<>();
		try {
			Optional<OportunidadInversion> existeOportunidad = oportunidadInversionservice
					.buscarxIdOportunidadInversion(id);
			if (existeOportunidad.isEmpty()) {
				salida.put("mensaje", "No existe oportunidad con Id: " + id);
				return new ResponseEntity<>(salida, HttpStatus.BAD_REQUEST);
			} else {
				return ResponseEntity.ok(existeOportunidad.get());
			}
		} catch (DataAccessException e) {
			salida.put("mensaje", "Error al registrar el Oportunidad Inversion");
			salida.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(salida, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
