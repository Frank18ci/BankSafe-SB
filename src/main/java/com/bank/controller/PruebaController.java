package com.bank.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.model.Tarjeta;

@RestController
@RequestMapping("/protected")
public class PruebaController {
	@GetMapping
	public ResponseEntity<?> getPrueba() {
		String hola = "holaaa, path protegido";
		 Map<String, Object> mapper = new HashMap<String, Object>();
		 mapper.put("token", hola);
		 return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper);
	}
}
