package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.TarjetaDTO;
import com.bank.dto.UserDTO;
import com.bank.model.Tarjeta;
import com.bank.model.User;
import com.bank.repository.TarjetaRepository;
import com.bank.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class SecurityController {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	private TarjetaRepository tarjetaRepository;
	
	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@RequestBody TarjetaDTO tarjetaDTO){
		
		Tarjeta tarjeta = Tarjeta.builder()
				.numeroTarjeta(tarjetaDTO.getNumeroTarjeta())
				.claveInternet(passwordEncoder.encode(tarjetaDTO.getClaveInternet()))
				.build();
		
		tarjetaRepository.save(tarjeta);
		return ResponseEntity.ok(tarjeta);
		}
	@GetMapping("/perfil")
	public String getPerfilUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Tarjeta tarjeta = (Tarjeta) authentication.getPrincipal();
        return "Bienvenido, " + tarjeta.getUser().getNombres() + " numero de tarjeta " + tarjeta.getNumeroTarjeta() + ". Tu password es " + tarjeta.getClaveInternet();
	}
}
