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

import com.bank.dto.TipoPlazoDTO;
import com.bank.service.TipoPlazoService;

@RestController
@RequestMapping("/tipoPlazo")
public class TipoPlazoController {
	@Autowired
	private TipoPlazoService tipoPlazoService;
	
	@GetMapping
	private ResponseEntity<?> getAll(){
		List<TipoPlazoDTO> tipoPrestamos = tipoPlazoService.list();
		return ResponseEntity.status(200).body(tipoPrestamos);
	}
	@GetMapping("/{id}")
	private ResponseEntity<?> getById(@PathVariable int id){
		TipoPlazoDTO tipoPrestamo = tipoPlazoService.find(id);
		return ResponseEntity.status(200).body(tipoPrestamo);
	}
	@PostMapping
	private ResponseEntity<?> save(@RequestBody TipoPlazoDTO tipoPrestamo){
		TipoPlazoDTO tipoPrestamoS = tipoPlazoService.save(tipoPrestamo);
		return ResponseEntity.status(201).body(tipoPrestamoS);
	}
	@PutMapping
	private ResponseEntity<?> update(@RequestBody TipoPlazoDTO tipoPrestamo){
		TipoPlazoDTO tipoPrestamoS = tipoPlazoService.update(tipoPrestamo);
		return ResponseEntity.status(200).body(tipoPrestamoS);
	}
	@DeleteMapping("/{id}")
	private ResponseEntity<?> deleteById(@PathVariable int id){
		String mensaje = tipoPlazoService.delete(id);
		Map<String, Object> mapper = new HashMap<String, Object>();
		 mapper.put("message", mensaje);
		return ResponseEntity.status(200).body(mapper);
	}
}
