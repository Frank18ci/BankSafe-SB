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

import com.bank.dto.RoleUserDTO;
import com.bank.service.RoleUserService;

@RestController
@RequestMapping("/roleUser")
public class RoleUserController {
	@Autowired
	private RoleUserService roleUserService;
	
	@GetMapping
	private ResponseEntity<?> getAll(){
		List<RoleUserDTO> roleUsers = roleUserService.list();
		return ResponseEntity.status(200).body(roleUsers);
	}
	@GetMapping("/{id}")
	private ResponseEntity<?> getById(@PathVariable int id){
		RoleUserDTO roleUser = roleUserService.find(id);
		return ResponseEntity.status(200).body(roleUser);
	}
	@PostMapping
	private ResponseEntity<?> save(@RequestBody RoleUserDTO roleUser){
		RoleUserDTO roleUserS = roleUserService.save(roleUser);
		return ResponseEntity.status(201).body(roleUserS);
	}
	@PutMapping
	private ResponseEntity<?> update(@RequestBody RoleUserDTO roleUser){
		RoleUserDTO roleUserS = roleUserService.update(roleUser);
		return ResponseEntity.status(200).body(roleUserS);
	}
	@DeleteMapping("/{id}")
	private ResponseEntity<?> deleteById(@PathVariable int id){
		String mensaje = roleUserService.delete(id);
		Map<String, Object> mapper = new HashMap<String, Object>();
		 mapper.put("message", mensaje);
		return ResponseEntity.status(200).body(mapper);
	}
}
