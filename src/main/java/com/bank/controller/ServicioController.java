package com.bank.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.ServicioDTO;
import com.bank.service.ServicioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("servicio")
@RequiredArgsConstructor
public class ServicioController {
	private final ServicioService servicioService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<ServicioDTO> servicios = servicioService.list();
		return ResponseEntity.status(200).body(servicios);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		ServicioDTO servicio = servicioService.find(id);
		return ResponseEntity.status(200).body(servicio);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody ServicioDTO servicio) {
		ServicioDTO servicioS = servicioService.save(servicio);
		return ResponseEntity.status(201).body(servicioS);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody ServicioDTO servicio) {
		ServicioDTO servicioS = servicioService.update(servicio);
		return ResponseEntity.status(200).body(servicioS);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id) {
		String mensaje = servicioService.delete(id);
		Map<String, Object> mapper = new HashMap<String, Object>();
		mapper.put("message", mensaje);
		return ResponseEntity.status(200).body(mensaje);
	}
}
