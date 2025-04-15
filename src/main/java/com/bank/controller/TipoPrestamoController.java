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

import com.bank.dto.TipoPrestamoDTO;
import com.bank.service.TipoPrestamoService;

@RestController
@RequestMapping("/tipoPrestamo")
public class TipoPrestamoController {
	@Autowired
	private TipoPrestamoService tipoPrestamoService;
	
	@GetMapping
	private ResponseEntity<?> getAll(){
		List<TipoPrestamoDTO> tipoPrestamos = tipoPrestamoService.list();
		return ResponseEntity.status(200).body(tipoPrestamos);
	}
	@GetMapping("/{id}")
	private ResponseEntity<?> getById(@PathVariable int id){
		TipoPrestamoDTO tipoPrestamo = tipoPrestamoService.find(id);
		return ResponseEntity.status(200).body(tipoPrestamo);
	}
	@PostMapping
	private ResponseEntity<?> save(@RequestBody TipoPrestamoDTO tipoPrestamo){
		TipoPrestamoDTO tipoPrestamoS = tipoPrestamoService.save(tipoPrestamo);
		return ResponseEntity.status(201).body(tipoPrestamoS);
	}
	@PutMapping
	private ResponseEntity<?> update(@RequestBody TipoPrestamoDTO tipoPrestamo){
		TipoPrestamoDTO tipoPrestamoS = tipoPrestamoService.update(tipoPrestamo);
		return ResponseEntity.status(200).body(tipoPrestamoS);
	}
	@DeleteMapping("/{id}")
	private ResponseEntity<?> deleteById(@PathVariable int id){
		String mensaje = tipoPrestamoService.delete(id);
		Map<String, Object> mapper = new HashMap<String, Object>();
		 mapper.put("message", mensaje);
		return ResponseEntity.status(200).body(mapper);
	}
}
