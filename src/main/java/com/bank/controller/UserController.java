package com.bank.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.UserDTO;
import com.bank.service.UserService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<?> getUsers() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.list());
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable int id){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.find(id));
	}
	@PostMapping
	public ResponseEntity<?> createUser (@RequestBody UserDTO userDTO ){
		UserDTO u = userService.save(userDTO);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(u);
	}
	@PutMapping
	public ResponseEntity<?> updateUser (@RequestBody UserDTO userDTO ){
		UserDTO u = userService.update(userDTO);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(u);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id){
		userService.delete(id);
		Map<String, Object> mapper = new HashMap<String, Object>();
		 mapper.put("message", "User Eliminado" + id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper);
	}
	
	
}
