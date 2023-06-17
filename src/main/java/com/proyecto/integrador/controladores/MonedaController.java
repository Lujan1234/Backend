package com.proyecto.integrador.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.integrador.entidades.Monedas;
import com.proyecto.integrador.servicios.MonedasService;
import com.proyecto.integrador.utils.AppSettings;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class MonedaController {
	
	@Autowired
	MonedasService monedaService;
	
	
	@GetMapping("/listarMonedas")
	@ResponseBody
	public ResponseEntity<List<Monedas>> listarMonedas(){
		List<Monedas> lista = monedaService.listarMonedas();
		return ResponseEntity.ok(lista);
	}
	

}
