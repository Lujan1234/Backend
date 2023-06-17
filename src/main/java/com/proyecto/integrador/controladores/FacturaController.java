package com.proyecto.integrador.controladores;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.proyecto.integrador.entidades.Factura;
import com.proyecto.integrador.servicios.EmpresaService;
import com.proyecto.integrador.servicios.FacturaService;
import com.proyecto.integrador.utils.AppSettings;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class FacturaController {
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private FacturaService facturaService;
	
	//buscar facturas por rango de fechas
	 @GetMapping("/facturas/{fechaInicio}/{fechaFin}")
	    @ResponseBody
	    public ResponseEntity<?> listarFacturasPorRangoFechas(
	            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
	            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
	        HashMap<String, Object> salida = new HashMap<>();
	        try {
	            List<Factura> facturas = facturaService.listarFacturasPorRangoFechas(fechaInicio, fechaFin);
	            salida.put("facturas", facturas);
	            return ResponseEntity.ok(salida);
	        } catch (DataAccessException e) {
	            salida.put("mensaje", "Error al listar las facturas por rango de fechas");
	            salida.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	            return new ResponseEntity<>(salida, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	
	
	//buscar factura por codFactura
	@GetMapping("/buscarfac/{codFactura}")
	@ResponseBody
	public ResponseEntity<?> buscarPorCod(@PathVariable String codFactura) {
		HashMap<String, Object> response = new HashMap<>();
		try {
		 Optional<Factura> facturaOptional = facturaService.buscarxCod(codFactura);
	        if (facturaOptional.isPresent()) {
	            Factura factura = facturaOptional.get();
	            response.put("factura", factura);
	            return ResponseEntity.ok(response);
	        } else {
	            response.put("mensaje", "No se encontró la factura con el código: " + codFactura);
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        response.put("mensaje", "Error al buscar la factura por código");
	        response.put("error", e.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

}
	
	//listado de facturas x empresa
	@GetMapping("/facturas/{idEmpresa}")
	@ResponseBody
	public ResponseEntity<?> listarFacturasPorEmpresa(@PathVariable int idEmpresa) {
	    HashMap<String, Object> salida = new HashMap<>();
	    try {
	        Optional<Empresa> empresaOptional = empresaService.buscarxId(idEmpresa);
	        if (empresaOptional.isEmpty()) {
	            salida.put("mensaje", "No se encontró la empresa con el id: " + idEmpresa);
	            return new ResponseEntity<>(salida, HttpStatus.NOT_FOUND);
	        }
	        
	        Empresa empresa = empresaOptional.get();
	        
	        List<Factura> facturas = facturaService.listarFacturasPorEmpresa(empresa);
	        salida.put("facturas", facturas);
	        
	        return ResponseEntity.ok(salida);
	    } catch (DataAccessException e) {
	        salida.put("mensaje", "Error al listar las facturas");
	        salida.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	        return new ResponseEntity<>(salida, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	@GetMapping("/active/listaFactura")
	@ResponseBody
	public ResponseEntity<List<Factura>> listaFacturasAct() {
		List<Factura> lista = facturaService.listaDifNotEnable("No activo");
		return ResponseEntity.ok(lista);
	}
	
	
	@GetMapping("/listaFacturas")
	@ResponseBody
	public ResponseEntity<List<Factura>> listaFacturas() {
		List<Factura> lista = facturaService.listaFactura();
		return ResponseEntity.ok(lista);
	}
	
	
	
	
	@PostMapping("/registrarFactura")
	@ResponseBody
	public ResponseEntity<?> registrarFactura(@RequestBody Factura factura) {
		HashMap<String, Object> salida = new HashMap<>();
		
		try {
			
		  // Realizar operaciones de registro
        factura.setFechaEmision(new Date()); // Asignar fecha actual
        factura.setEnable("Activo"); // Asignar estado activo
        // Obtener la empresa por id
        Optional<Empresa> empresaOptional = empresaService.buscarxId(factura.getEmpresa().getIdEmpresa());
        if (empresaOptional.isEmpty()) {
            salida.put("mensaje", "No se encontró la empresa con el id: " + factura.getEmpresa().getIdEmpresa());
            return new ResponseEntity<>(salida, HttpStatus.NOT_FOUND);
        }
        // Obtener el último número de factura
        int ultimoNumeroFactura = facturaService.obtenerUltimoNumeroFactura();
        String nuevoNumeroFactura = "F-" + String.format("%03d", ultimoNumeroFactura + 1);
        factura.setCodFactura(nuevoNumeroFactura); // Asignar el nuevo número de factura
     
        
        Empresa empresa = empresaOptional.get();
        factura.setEmpresa(empresa); // Establecer la empresa en la factura

        Factura facturaRegistrada = facturaService.insertarActualizarFactura(factura);
        
        if (facturaRegistrada != null) {
            salida.put("mensaje", "Factura registrada exitosamente");
            salida.put("factura", facturaRegistrada);
            return ResponseEntity.ok(salida);
        } else {
            salida.put("mensaje", "Error al registrar la factura");
            return new ResponseEntity<>(salida, HttpStatus.BAD_REQUEST);
        }
    } catch (DataAccessException e) {
        salida.put("mensaje", "Error al registrar la factura");
        salida.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
        return new ResponseEntity<>(salida, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
	
	@PutMapping("/actualizarFactura")
	@ResponseBody
	public ResponseEntity<?> actualizarFactura(@RequestBody Factura factura) {
	    HashMap<String, Object> salida = new HashMap<>();
	    try {
	    	
	    	// Verificar si la factura existe en la base de datos
	        Optional<Factura> existeFactura = facturaService.buscarxId(factura.getEmpresa().getIdEmpresa());
	        if (existeFactura.isEmpty()) {
	            salida.put("mensaje", "No existe factura con id: " + factura.getEmpresa().getIdEmpresa());
	            return new ResponseEntity<>(salida, HttpStatus.CONFLICT);
	        } else {
	            // Realizar operaciones de actualización
	            Factura facturaExistente = existeFactura.get();
	            
	         // Actualizar los campos necesarios de la factura existente
	            facturaExistente.setMonto(factura.getMonto());
	            facturaExistente.setFechaPago(factura.getFechaPago());
	            facturaExistente.setDescripcion(factura.getDescripcion());
	            
	            Factura facturaActualizada = facturaService.insertarActualizarFactura(facturaExistente);
	            
	            if (facturaActualizada != null) {
	                salida.put("mensaje", "Factura actualizada exitosamente");
	                salida.put("factura", facturaActualizada);
	                return ResponseEntity.ok(salida);
	            } else {
	                salida.put("mensaje", "Error al actualizar la factura");
	                return new ResponseEntity<>(salida, HttpStatus.BAD_REQUEST);
	            }
	        }
	    } catch (DataAccessException e) {
	        salida.put("mensaje", "Error al actualizar la factura");
	        salida.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	        return new ResponseEntity<>(salida, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@DeleteMapping("/eliminarFactura/{id}")
	@ResponseBody
	public ResponseEntity<?> eliminarFactura(@PathVariable int id) {
	    HashMap<String, Object> response = new HashMap<>();
	    try {
	        Optional<Factura> existeFactura = facturaService.buscarxId(id);
	        if (existeFactura.isEmpty()) {
	            response.put("mensaje", "No existe factura con id: " + id);
	            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	        } else {
	            Factura facturaEliminada = existeFactura.get();
	            facturaEliminada.setEnable("No Activo");
	            facturaService.insertarActualizarFactura(facturaEliminada);
	            response.put("mensaje", "Se eliminó exitosamente la factura");
	            return ResponseEntity.ok(response);
	        }
	    } catch (Exception e) {
	        response.put("mensaje", "Hubo un error al eliminar la factura: " + e.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	

	
}
	


