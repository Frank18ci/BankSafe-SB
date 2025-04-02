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

import com.bank.dto.UserDTO;
import com.bank.model.User;
import com.bank.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class SecurityController {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository clienteRepository;
	
	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
		User user = User.builder()
				.username(userDTO.getUsername())
				.password(passwordEncoder.encode(userDTO.getPassword()))
				.build();
		
		clienteRepository.save(user);
		return ResponseEntity.ok(user);
		}
	@GetMapping("/perfil")
	public String getPerfilUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
        return "Bienvenido, " + user.getUsername() + ". Tu password es " + user.getPassword();
	}
}
