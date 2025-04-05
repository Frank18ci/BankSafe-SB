package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.TipoDocumentoUserDTO;
import com.bank.service.TipoDocumentoUserService;


@RestController
@RequestMapping("tipoDocumentoUser")
public class TipoDocumentoUserController {
	@Autowired
	private TipoDocumentoUserService tipoDocumentoUserService;
	@GetMapping
	public ResponseEntity<?> getAll(){
		List<TipoDocumentoUserDTO> lista = tipoDocumentoUserService.list();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(lista);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable int id){
		TipoDocumentoUserDTO tipoDocumento = tipoDocumentoUserService.find(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(tipoDocumento);
	}
}
