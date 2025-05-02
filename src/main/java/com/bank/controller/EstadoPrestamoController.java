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

import com.bank.dto.EstadoPrestamoDTO;
import com.bank.service.EstadoPrestamoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("estadoPrestamo")
@RequiredArgsConstructor
public class EstadoPrestamoController {
	private final EstadoPrestamoService estadoPrestamoService;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		List<EstadoPrestamoDTO> estadoPrestamos = estadoPrestamoService.list();
		return ResponseEntity.status(200).body(estadoPrestamos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		EstadoPrestamoDTO estadoPrestamo = estadoPrestamoService.find(id);
		return ResponseEntity.status(200).body(estadoPrestamo);
	}
	
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody EstadoPrestamoDTO estadoPrestamo) {
		EstadoPrestamoDTO estadoPrestamoS = estadoPrestamoService.save(estadoPrestamo);
		return ResponseEntity.status(201).body(estadoPrestamoS);
	}
	@PutMapping
	public ResponseEntity<?> update(@RequestBody EstadoPrestamoDTO estadoPrestamo) {
		EstadoPrestamoDTO estadoPrestamoS = estadoPrestamoService.update(estadoPrestamo);
		return ResponseEntity.status(200).body(estadoPrestamoS);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id) {
		String mensaje = estadoPrestamoService.delete(id);
		Map<String, Object> mapper = new HashMap<String, Object>();
		 mapper.put("message", mensaje);
		return ResponseEntity.status(200).body(mensaje);
	}
}
