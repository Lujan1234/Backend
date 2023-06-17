package com.proyecto.integrador.controladores;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proyecto.integrador.entidades.Empresa;
import com.proyecto.integrador.servicios.EmpresaService;
import com.proyecto.integrador.utils.AppSettings;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class EmpresaController {
	@Autowired
	private EmpresaService empresaService;

	@GetMapping("active/buscarEmpresasContains/{keyWord}")
	@ResponseBody
	public ResponseEntity<List<Empresa>> buscarEmpresasActContains(@PathVariable String keyWord) {
		List<Empresa> lista = empresaService.buscarxRazonSocialContainsActive(keyWord,"No activo");
		return ResponseEntity.ok(lista);
	}
	@GetMapping("active/listaEmpresas")
	@ResponseBody
	public ResponseEntity<List<Empresa>> listaEmpresasAct() {
		List<Empresa> lista = empresaService.listaDiffNotEnable("No activo");
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/listaEmpresas")
	@ResponseBody
	public ResponseEntity<List<Empresa>> listaEmpresa() {
		List<Empresa> lista = empresaService.listaEmpresa();
		return ResponseEntity.ok(lista);
	}

	@PostMapping("/registrarEmpresa")
	@ResponseBody
	public ResponseEntity<?> registrarEmpresa(@RequestBody Empresa empresa) {
		HashMap<String, Object> salida = new HashMap<>();
		try {
			Optional<Empresa> existeCorreo = empresaService.listExistexCorreo(empresa.getCorreo(),
					empresa.getIdEmpresa());
			if (existeCorreo.isPresent()) {
				salida.put("mensaje", "El correo ya existe");
				return new ResponseEntity<HashMap<String, Object>>(salida, HttpStatus.CONFLICT);
			} else {
				Optional<Empresa> existeRuc = empresaService.listExistexRuc(empresa.getRuc(), empresa.getIdEmpresa());
				System.out.println(existeRuc);
				if (existeRuc.isPresent()) {
					salida.put("mensaje", "El Ruc ya existe");
					return new ResponseEntity<HashMap<String, Object>>(salida, HttpStatus.CONFLICT);
				} /*else {
					Optional<Empresa> existeNCB = empresaService
							.listExistexNroCuentaBancaria(empresa.getNroCuentaBancaria(), empresa.getIdEmpresa());
					if (existeNCB.isPresent()) {
						salida.put("mensaje", "El Numero de cuenta bancaria ya existe");
						return new ResponseEntity<HashMap<String, Object>>(salida, HttpStatus.CONFLICT);
					}*/ else {
						Optional<Empresa> existeRZ = empresaService.listExistexRazonSocial(empresa.getRazonSocial(),
								empresa.getIdEmpresa());
						if (existeRZ.isPresent()) {
							salida.put("mensaje", "La Razon social ya existe");
							return new ResponseEntity<HashMap<String, Object>>(salida, HttpStatus.CONFLICT);
						} else {
							empresa.setFechRegistro(new Date());
							empresa.setEnable("Activo");
							Empresa objEmpresa = empresaService.insertarActualizarEmpresa(empresa);
							if (objEmpresa != null) {
								salida.put("mensaje", "Empresa registrada exitosamente");
								salida.put("empresa", objEmpresa);
								return ResponseEntity.ok(salida);
							} else {
								salida.put("mensaje", "Error al registrar la empresa");
								return new ResponseEntity<HashMap<String, Object>>(salida, HttpStatus.BAD_REQUEST);
							}
						}
					}
				}
			/*/}*/
		} catch (DataAccessException e) {
			salida.put("mensaje", "Error al registrar empresa");
			salida.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<HashMap<String, Object>>(salida, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/actualizarEmpresa")
	@ResponseBody
	public ResponseEntity<?> actualizarEmpresa(@RequestBody Empresa empresa) {
		HashMap<String, Object> salida = new HashMap<>();
		try {
			Optional<Empresa> existeEmp = empresaService.buscarxId(empresa.getIdEmpresa());
			if (existeEmp.isEmpty()) {
				salida.put("mensaje", "No existe empresa con id: " + empresa.getIdEmpresa());
				return new ResponseEntity<HashMap<String, Object>>(salida, HttpStatus.CONFLICT);
			} else {
				Optional<Empresa> existeCorreo = empresaService.listExistexCorreo(empresa.getCorreo(),
						empresa.getIdEmpresa());
				if (existeCorreo.isPresent()) {
					salida.put("mensaje", "El correo ya existe");
					return new ResponseEntity<HashMap<String, Object>>(salida, HttpStatus.CONFLICT);
				} else {
					Optional<Empresa> existeRuc = empresaService.listExistexRuc(empresa.getRuc(),
							empresa.getIdEmpresa());
					System.out.println(existeRuc);
					if (existeRuc.isPresent()) {
						salida.put("mensaje", "El Ruc ya existe");
						return new ResponseEntity<HashMap<String, Object>>(salida, HttpStatus.CONFLICT);
					} /*else {
						Optional<Empresa> existeNCB = empresaService
								.listExistexNroCuentaBancaria(empresa.getNroCuentaBancaria(), empresa.getIdEmpresa());
						if (existeNCB.isPresent()) {
							salida.put("mensaje", "El Numero de cuenta bancaria ya existe");
							return new ResponseEntity<HashMap<String, Object>>(salida, HttpStatus.CONFLICT);
						}*/ else {
							Optional<Empresa> existeRZ = empresaService.listExistexRazonSocial(empresa.getRazonSocial(),
									empresa.getIdEmpresa());
							if (existeRZ.isPresent()) {
								salida.put("mensaje", "La razon social ya existe");
								return new ResponseEntity<HashMap<String, Object>>(salida, HttpStatus.CONFLICT);
							} else {
								Empresa empresaActu = existeEmp.get();
								empresa.setFechRegistro(empresaActu.getFechRegistro());
								empresa.setEnable(empresaActu.getEnable());
								Empresa objEmpresa = empresaService.insertarActualizarEmpresa(empresa);
								if (objEmpresa != null) {
									salida.put("mensaje", "Empresa actualizada exitosamente");
									salida.put("empresa", objEmpresa);
									return ResponseEntity.ok(salida);
								} else {
									salida.put("mensaje", "Error al registrar la empresa");
									return new ResponseEntity<HashMap<String, Object>>(salida, HttpStatus.BAD_REQUEST);
								}
							}
						}
					}
				}
			/*}*/
		} catch (DataAccessException e) {
			salida.put("mensaje", "Error al registrar empresa");
			salida.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<HashMap<String, Object>>(salida, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/eliminarEmpresa/{id}")
	@ResponseBody
	public ResponseEntity<?> eliminarEmpresa(@PathVariable int id) {
		HashMap<String, Object> response = new HashMap<>();
		try {
			Optional<Empresa> existeEmp = empresaService.buscarxId(id);
			if (existeEmp.isEmpty()) {
				response.put("mensaje", "No existe empresa con id: " + id);
				return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.CONFLICT);
			} else {
				Empresa elimEmpresa = existeEmp.get();
				elimEmpresa.setEnable("No Activo");
				empresaService.insertarActualizarEmpresa(elimEmpresa);
				response.put("mensaje", "Se elimino exitosamente la empresa");
				return ResponseEntity.ok(response);
			}
		} catch (Exception e) {
			response.put("mensaje", "Hubo un error al eliminar la empresa: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
