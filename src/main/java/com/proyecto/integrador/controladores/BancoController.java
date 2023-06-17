package com.proyecto.integrador.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.integrador.entidades.Bancos;
import com.proyecto.integrador.servicios.BancosService;
import com.proyecto.integrador.utils.AppSettings;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class BancoController {
	
	@Autowired
	BancosService bancoServices;
	
	
	@GetMapping("/listarBancos")
	public ResponseEntity<List<Bancos>> listarTodosBancos(){
		List<Bancos> lista = bancoServices.listaBancos();
		return ResponseEntity.ok(lista);
	}

}
