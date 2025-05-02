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

import com.bank.dto.TipoDocumentoUserDTO;
import com.bank.service.TipoDocumentoUserService;


@RestController
@RequestMapping("/tipoDocumentoUser")
public class TipoDocumentoUserController {
	@Autowired
	private TipoDocumentoUserService tipoDocumentoUserService;
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		List<TipoDocumentoUserDTO> lista = tipoDocumentoUserService.list();
		return ResponseEntity.status(200).body(lista);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable int id){
		TipoDocumentoUserDTO tipoDocumento = tipoDocumentoUserService.find(id);
		return ResponseEntity.status(200).body(tipoDocumento);
	}
	@PostMapping
	public ResponseEntity<?> createTipoDocumentoUser(@RequestBody TipoDocumentoUserDTO tipoDocumentoUser){
		TipoDocumentoUserDTO tipoDocumento = tipoDocumentoUserService.save(tipoDocumentoUser);
		return ResponseEntity.status(201).body(tipoDocumento);
	}
	@PutMapping
	public ResponseEntity<?> updateTipoDocumentoUser(@RequestBody TipoDocumentoUserDTO tipoDocumentoUser){
		TipoDocumentoUserDTO tipoDocumento = tipoDocumentoUserService.update(tipoDocumentoUser);
		return ResponseEntity.status(200).body(tipoDocumento);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id){
		String mensaje = tipoDocumentoUserService.delete(id);
		Map<String, Object> mapper = new HashMap<String, Object>();
		 mapper.put("message", mensaje);
		return ResponseEntity.status(200).body(mapper);
	}
}
