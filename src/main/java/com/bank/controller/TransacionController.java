package com.bank.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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

import com.bank.dto.TransaccionConversionMonedaDTO;
import com.bank.dto.TransacionDTO;
import com.bank.service.TransaccionService;

@RestController
@RequestMapping("transacion")
public class TransacionController {
	@Autowired
	private TransaccionService transaccionService;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		List<TransacionDTO> transacions = transaccionService.list();
		return ResponseEntity.status(200).body(transacions);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		TransacionDTO transacion = transaccionService.find(id);
		return ResponseEntity.status(200).body(transacion);
	}
	@GetMapping("/busquedaFechaNumeroTarjeta")
	public ResponseEntity<?> getByFechaBAndTarjetaOrigenNumero(
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate fechaInicio,
			@RequestParam @DateTimeFormat(iso = ISO.DATE)LocalDate fechaFin,
			@RequestParam String numeroTarjeta) {
		Date fechaI = Date.from(fechaInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date fechaF = Date.from(fechaFin.atStartOfDay(ZoneId.systemDefault()).toInstant());
		List<TransacionDTO> transacions = transaccionService.listByFechaBetweenAndTarjetaOrigen_numeroTarjeta(fechaI, fechaF, numeroTarjeta);
		return ResponseEntity.status(200).body(transacions);
	}
	@GetMapping("/busquedaFechaNumeroTarjetaMesActual")
	public ResponseEntity<?> getByTarjetaOrigenNumeroMesActual(
			@RequestParam String numeroTarjeta) {
		List<TransacionDTO> transacions = transaccionService.listByTarjetaOrigen_numeroTarjetaActualMes(numeroTarjeta);
		return ResponseEntity.status(200).body(transacions);
	}
	@GetMapping("/busquedaFechaNumeroTarjetaMesAnterior")
	public ResponseEntity<?> getByTarjetaOrigenNumeroMesAnterior(
			@RequestParam String numeroTarjeta) {
		List<TransacionDTO> transacions = transaccionService.listByTarjetaOrigen_numeroTarjetaUltimoMes(numeroTarjeta);
		return ResponseEntity.status(200).body(transacions);
	}
	@PostMapping
	public ResponseEntity<?> save(@RequestBody TransacionDTO transacion) {
		TransacionDTO transacionS = transaccionService.save(transacion);
		return ResponseEntity.status(201).body(transacionS);
	}
	@PutMapping
	public ResponseEntity<?> update(@RequestBody TransacionDTO transacion) {
		TransacionDTO transacionS = transaccionService.save(transacion);
		return ResponseEntity.status(201).body(transacionS);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id) {
		String mensaje = transaccionService.delete(id);
		Map<String, Object> mapper = new HashMap<String, Object>();
		 mapper.put("message", mensaje);
		return ResponseEntity.status(200).body(mensaje);
	}
	@PostMapping("/transferenciaDineroConvertido")
	public ResponseEntity<?> trasferenciaDineroConvertido(@RequestBody TransaccionConversionMonedaDTO transaccionConversionMoneda) {
		
		return ResponseEntity.status(201).body(transaccionService.realizarConversionyTransferencia(transaccionConversionMoneda));
	}
}
