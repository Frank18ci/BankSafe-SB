package com.bank.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.TarjetaDTO;

import com.bank.service.TarjetaService;



@RestController
@RequestMapping("/tarjeta")
public class TarjetaController {
	@Autowired
	private TarjetaService tarjetaService;
	
	@GetMapping
	public ResponseEntity<?> getUsers() {
		return ResponseEntity.status(200).body(tarjetaService.list());
	}
	
	@GetMapping("/buscar/{numeroTarjeta}")
	public ResponseEntity<?> getTarjetaByNumero(@PathVariable String numeroTarjeta) {
		return ResponseEntity.status(200).body(tarjetaService.findByNumeroTarjeta(numeroTarjeta));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getTarjeta(@PathVariable int id){
		return ResponseEntity.status(200).body(tarjetaService.find(id));
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping
	public ResponseEntity<?> createTarjeta(@RequestBody TarjetaDTO tarjeta ){
		tarjeta.setClaveInternet(passwordEncoder.encode(tarjeta.getClaveInternet()));
		TarjetaDTO u = tarjetaService.save(tarjeta);
		return ResponseEntity.status(201).body(u);
	}
	@PutMapping
	public ResponseEntity<?> updateTarjeta(@RequestBody TarjetaDTO tarjeta){
		TarjetaDTO u = tarjetaService.update(tarjeta);
		return ResponseEntity.status(200).body(u);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTarjeta(@PathVariable int id){
		String mensaje = tarjetaService.delete(id);
		Map<String, Object> mapper = new HashMap<String, Object>();
		 mapper.put("message", mensaje);
		return ResponseEntity.status(200).body(mapper);
	}
	
}
