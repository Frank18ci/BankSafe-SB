package com.bank.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.TipoTarjetaDTO;
import com.bank.service.TipoTarjetaService;

@RestController
@RequestMapping("/tipoTarjeta")
public class TipoTarjetaController {
	@Autowired
	private TipoTarjetaService tipoTarjetaService;
	
	@GetMapping
	public ResponseEntity<?> getTipoTarjeta() {
		return ResponseEntity.status(200).body(tipoTarjetaService.list());
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getTipoTarjetaById(@PathVariable int id){
		return ResponseEntity.status(200).body(tipoTarjetaService.find(id));
	}
	@PostMapping
	public ResponseEntity<?> createTipoTarjeta(@RequestBody TipoTarjetaDTO tipoTarjeta ){
		TipoTarjetaDTO u = tipoTarjetaService.save(tipoTarjeta);
		return ResponseEntity.status(201).body(u);
	}
	@PutMapping
	public ResponseEntity<?> updateTipoTarjeta(@RequestBody TipoTarjetaDTO tipoTarjeta ){
		TipoTarjetaDTO u = tipoTarjetaService.update(tipoTarjeta);
		return ResponseEntity.status(200).body(u);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTipoTarjeta(@PathVariable int id){
		String mensaje = tipoTarjetaService.delete(id);
		Map<String, Object> mapper = new HashMap<String, Object>();
		 mapper.put("message", mensaje);
		return ResponseEntity.status(200).body(mapper);
	}
}
