package com.proyecto.integrador.controladores;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.integrador.entidades.Cartera;
import com.proyecto.integrador.servicios.CarteraService;
import com.proyecto.integrador.utils.AppSettings;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class CarteraController {
	@Autowired
	CarteraService carteraService;

	@GetMapping("/detalleCartera")
	@ResponseBody
	public ResponseEntity<?> detalleCartera(HttpSession session) {
		HashMap<String, Object> response = new HashMap<>();
		try {
			long idUsuAct = (long) session.getAttribute("idUsuActual");
			Cartera cartera = carteraService.buscarCartera(idUsuAct);
			if (cartera == null) {
				response.put("mensaje", "El no se encontro la cartera, contacta con un administrador");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			return ResponseEntity.ok(cartera);
		} catch (Exception e) {
			response.put("mensaje", "Hubo un error al buscar al usuario: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/admin/listarCarteras")
	@ResponseBody
	public ResponseEntity<List<Cartera>> listarCarteras() {
		List<Cartera> lista = carteraService.listaCarteras();
		return ResponseEntity.ok(lista);
	}
}
