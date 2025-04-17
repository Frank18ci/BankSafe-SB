package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.apicambiomoneda.CambioMonedaConnector;

@RestController
@RequestMapping("/cambioMoneda")
public class CambioMonedaController {
	@Autowired
	private CambioMonedaConnector cambioMonedaConnector;
	@GetMapping("/{nombre}")
	public ResponseEntity<?> getAll(@PathVariable String nombre){
		return ResponseEntity.status(200).body(cambioMonedaConnector.getConversion(nombre));
	}
	@GetMapping("/{base}/{target}")
	public ResponseEntity<?> getBaseToTarget(@PathVariable String base, @PathVariable String target){
		return ResponseEntity.status(200).body(cambioMonedaConnector.getConversion(base, target));
	}
}
