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

import com.bank.dto.TipoTransacionDTO;
import com.bank.service.TipoTransacionService;

@RestController
@RequestMapping("/tipoTransacion")
public class TipoTransacionController {
	@Autowired
	private TipoTransacionService tipoTransacionService;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		List<TipoTransacionDTO> TipoTransacions = tipoTransacionService.list();
		return ResponseEntity.status(200).body(TipoTransacions);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		TipoTransacionDTO tipoTransacion = tipoTransacionService.find(id);
		return ResponseEntity.status(200).body(tipoTransacion);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody TipoTransacionDTO tipoTransacion) {
		TipoTransacionDTO tipoTransacionS = tipoTransacionService.save(tipoTransacion);
		return ResponseEntity.status(200).body(tipoTransacionS);
	}
	@PutMapping
	public ResponseEntity<?> update(@RequestBody TipoTransacionDTO tipoTransacion) {
		TipoTransacionDTO tipoTransacionS = tipoTransacionService.update(tipoTransacion);
		return ResponseEntity.status(200).body(tipoTransacionS);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id) {
		String mensaje = tipoTransacionService.delete(id);
		Map<String, Object> mapper = new HashMap<String, Object>();
		 mapper.put("message", mensaje);
		return ResponseEntity.status(200).body(mensaje);
	}
}
