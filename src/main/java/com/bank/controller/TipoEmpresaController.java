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

import com.bank.dto.TipoEmpresaDTO;
import com.bank.service.TipoEmpresaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("tipoEmpresa")
@RequiredArgsConstructor
public class TipoEmpresaController {
	private final TipoEmpresaService tipoEmpresaService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<TipoEmpresaDTO> tipoEmpresas = tipoEmpresaService.list();
		return ResponseEntity.status(200).body(tipoEmpresas);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		TipoEmpresaDTO tipoEmpresa = tipoEmpresaService.find(id);
		return ResponseEntity.status(200).body(tipoEmpresa);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody TipoEmpresaDTO tipoEmpresa) {
		TipoEmpresaDTO tipoEmpresaS = tipoEmpresaService.save(tipoEmpresa);
		return ResponseEntity.status(201).body(tipoEmpresaS);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody TipoEmpresaDTO tipoEmpresa) {
		TipoEmpresaDTO tipoEmpresaS = tipoEmpresaService.update(tipoEmpresa);
		return ResponseEntity.status(200).body(tipoEmpresaS);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id) {
		String mensaje = tipoEmpresaService.delete(id);
		Map<String, Object> mapper = new HashMap<String, Object>();
		mapper.put("message", mensaje);
		return ResponseEntity.status(200).body(mensaje);
	}
}
