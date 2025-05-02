package com.bank.controller;

import java.util.HashMap;
import java.util.List;
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

import com.bank.dto.PrestamoDTO;
import com.bank.service.PrestamoService;

@RestController
@RequestMapping("prestamo")
public class PrestamoController {
	@Autowired
	private PrestamoService prestamoService;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		List<PrestamoDTO> prestamos = prestamoService.list();
		return ResponseEntity.status(200).body(prestamos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		PrestamoDTO prestamo = prestamoService.find(id);
		return ResponseEntity.status(200).body(prestamo);
	}
	
	@GetMapping("/buscarUsuario/{idUsuario}")
	public ResponseEntity<?> getByUsuarioId(@PathVariable int idUsuario) {
		List<PrestamoDTO> prestamo = prestamoService.findByUsuarioId(idUsuario);
		return ResponseEntity.status(200).body(prestamo);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody PrestamoDTO prestamo) {
		PrestamoDTO prestamoS = prestamoService.save(prestamo);
		return ResponseEntity.status(201).body(prestamoS);
	}
	@PutMapping
	public ResponseEntity<?> update(@RequestBody PrestamoDTO prestamo) {
		PrestamoDTO prestamoS = prestamoService.update(prestamo);
		return ResponseEntity.status(200).body(prestamoS);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id) {
		String mensaje = prestamoService.delete(id);
		Map<String, Object> mapper = new HashMap<String, Object>();
		 mapper.put("message", mensaje);
		return ResponseEntity.status(200).body(mensaje);
	}
	
	
}
