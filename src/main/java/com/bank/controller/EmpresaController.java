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

import com.bank.dto.EmpresaDTO;
import com.bank.service.EmpresaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("empresa")
@RequiredArgsConstructor
public class EmpresaController {
	private final EmpresaService empresaService;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		List<EmpresaDTO> empresaDTOs = empresaService.list();
		return ResponseEntity.status(200).body(empresaDTOs);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		EmpresaDTO estadoPrestamo = empresaService.find(id);
		return ResponseEntity.status(200).body(estadoPrestamo);
	}
	@PostMapping
	public ResponseEntity<?> save(@RequestBody EmpresaDTO empresa) {
		EmpresaDTO empresaS = empresaService.save(empresa);
		return ResponseEntity.status(201).body(empresaS);
	}
	@PutMapping
	public ResponseEntity<?> update(@RequestBody EmpresaDTO empresa) {
		EmpresaDTO empresaS = empresaService.update(empresa);
		return ResponseEntity.status(200).body(empresaS);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id) {
		String mensaje = empresaService.delete(id);
		Map<String, Object> mapper = new HashMap<String, Object>();
		 mapper.put("message", mensaje);
		return ResponseEntity.status(200).body(mensaje);
	}
}
