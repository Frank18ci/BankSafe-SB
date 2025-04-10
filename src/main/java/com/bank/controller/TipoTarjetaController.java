package com.bank.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.TipoTarjetaDTO;
import com.bank.dto.UserDTO;
import com.bank.service.TipoTarjetaService;
import com.bank.service.UserService;

@RestController
@RequestMapping("/tipoTarjeta")
public class TipoTarjetaController {
	@Autowired
	private TipoTarjetaService tipoTarjetaService;
	
	@GetMapping
	public ResponseEntity<?> getTipoTarjeta() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(tipoTarjetaService.list());
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getTipoTarjeta(@PathVariable int id){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(tipoTarjetaService.find(id));
	}
	@PostMapping
	public ResponseEntity<?> createTipoTarjeta (@RequestBody TipoTarjetaDTO tipoTarjetaDTO ){
		TipoTarjetaDTO u = tipoTarjetaService.save(tipoTarjetaDTO);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(u);
	}
	@PutMapping
	public ResponseEntity<?> updateTipoTarjeta (@RequestBody TipoTarjetaDTO tipoTarjetaDTO ){
		TipoTarjetaDTO u = tipoTarjetaService.update(tipoTarjetaDTO);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(u);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTipoTarjeta(@PathVariable int id){
		tipoTarjetaService.delete(id);
		Map<String, Object> mapper = new HashMap<String, Object>();
		 mapper.put("message", "Tipo Tarjeta Eliminado" + id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper);
	}
}
