package com.bank.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(tarjetaService.list());
	}
	
	@GetMapping("/buscar/{numeroTarjeta}")
	public ResponseEntity<?> getTarjetaByNumero(@PathVariable String numeroTarjeta) {
		return ResponseEntity.status(200).body(tarjetaService.findByNumeroTarjeta(numeroTarjeta));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getTarjeta(@PathVariable int id){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(tarjetaService.find(id));
	}
	@Autowired
	private PasswordEncoder passwordEncoder;
	@PostMapping
	public ResponseEntity<?> createTarjeta (@RequestBody TarjetaDTO tarjetaDTO ){
		tarjetaDTO.setClaveInternet(passwordEncoder.encode(tarjetaDTO.getClaveInternet()));
		TarjetaDTO u = tarjetaService.save(tarjetaDTO);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(u);
	}
	@PutMapping
	public ResponseEntity<?> updateTarjeta (@RequestBody TarjetaDTO tarjetaDTO ){
		TarjetaDTO u = tarjetaService.update(tarjetaDTO);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(u);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTarjeta(@PathVariable int id){
		tarjetaService.delete(id);
		Map<String, Object> mapper = new HashMap<String, Object>();
		 mapper.put("message", "Tarjeta Eliminado" + id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper);
	}
	
}
