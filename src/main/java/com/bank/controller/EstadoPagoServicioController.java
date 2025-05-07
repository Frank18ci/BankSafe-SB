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

import com.bank.dto.EstadoPagoServicioDTO;
import com.bank.service.EstadoPagoServicioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("estadoPagoServicio")
@RequiredArgsConstructor
public class EstadoPagoServicioController {
	private final EstadoPagoServicioService estadoPagoServicioService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<EstadoPagoServicioDTO> estadoPagoServicios = estadoPagoServicioService.list();
		return ResponseEntity.status(200).body(estadoPagoServicios);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		EstadoPagoServicioDTO estadoPagoServicio = estadoPagoServicioService.find(id);
		return ResponseEntity.status(200).body(estadoPagoServicio);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody EstadoPagoServicioDTO estadoPagoServicio) {
		EstadoPagoServicioDTO estadoPagoServicioS = estadoPagoServicioService.save(estadoPagoServicio);
		return ResponseEntity.status(201).body(estadoPagoServicioS);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody EstadoPagoServicioDTO estadoPagoServicio) {
		EstadoPagoServicioDTO estadoPagoServicioS = estadoPagoServicioService.update(estadoPagoServicio);
		return ResponseEntity.status(200).body(estadoPagoServicioS);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id) {
		String mensaje = estadoPagoServicioService.delete(id);
		Map<String, Object> mapper = new HashMap<String, Object>();
		mapper.put("message", mensaje);
		return ResponseEntity.status(200).body(mensaje);
	}
}
