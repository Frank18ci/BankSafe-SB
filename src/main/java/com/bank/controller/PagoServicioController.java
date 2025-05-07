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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.PagoServicioDTO;
import com.bank.service.PagoServicioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("pagoServicio")
@RequiredArgsConstructor
public class PagoServicioController {
	private final PagoServicioService pagoServicioService;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		List<PagoServicioDTO> pagoServicios = pagoServicioService.list();
		return ResponseEntity.status(200).body(pagoServicios);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		PagoServicioDTO pagoServicio = pagoServicioService.find(id);
		return ResponseEntity.status(200).body(pagoServicio);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody PagoServicioDTO pagoServicio) {
		PagoServicioDTO pagoServicioS = pagoServicioService.save(pagoServicio);
		return ResponseEntity.status(201).body(pagoServicioS);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody PagoServicioDTO pagoServicio) {
		PagoServicioDTO pagoServicioS = pagoServicioService.update(pagoServicio);
		return ResponseEntity.status(200).body(pagoServicioS);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id) {
		String mensaje = pagoServicioService.delete(id);
		Map<String, Object> mapper = new HashMap<String, Object>();
		mapper.put("message", mensaje);
		return ResponseEntity.status(200).body(mensaje);
	}
	
	@GetMapping("/busquedaPagar")
	public ResponseEntity<?> getBusqueda(
			@RequestParam String codigo,
			@RequestParam String estado
			) {
		List<PagoServicioDTO> pagoServicios = pagoServicioService.buscarPorCodigoAndEstado(codigo, estado);
		return ResponseEntity.status(200).body(pagoServicios);
	}
}
