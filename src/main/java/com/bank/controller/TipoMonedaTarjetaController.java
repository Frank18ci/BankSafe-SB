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

import com.bank.dto.TipoMonedaTarjetaDTO;
import com.bank.service.TipoMonedaTarjetaService;

@RestController
@RequestMapping("/tipoMonedaTarjeta")
public class TipoMonedaTarjetaController {
	@Autowired
	private TipoMonedaTarjetaService tipoMonedaTarjetaService;
	
	@GetMapping
	public ResponseEntity<?> getTipoTarjeta() {
		return ResponseEntity.status(200).body(tipoMonedaTarjetaService.list());
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getTipoTarjetaById(@PathVariable int id){
		return ResponseEntity.status(200).body(tipoMonedaTarjetaService.find(id));
	}
	@PostMapping
	public ResponseEntity<?> createTipoTarjeta(@RequestBody TipoMonedaTarjetaDTO tipoMonedaTarjetaDTO ){
		TipoMonedaTarjetaDTO u = tipoMonedaTarjetaService.save(tipoMonedaTarjetaDTO);
		return ResponseEntity.status(201).body(u);
	}
	@PutMapping
	public ResponseEntity<?> updateTipoTarjeta (@RequestBody TipoMonedaTarjetaDTO tipoMonedaTarjetaDTO ){
		TipoMonedaTarjetaDTO u = tipoMonedaTarjetaService.update(tipoMonedaTarjetaDTO);
		return ResponseEntity.status(200).body(u);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTipoTarjeta(@PathVariable int id){
		String mensaje = tipoMonedaTarjetaService.delete(id);
		Map<String, Object> mapper = new HashMap<String, Object>();
		 mapper.put("message", mensaje);
		return ResponseEntity.status(200).body(mapper);
	}

}
