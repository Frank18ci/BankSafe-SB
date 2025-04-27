package com.bank.controller;

import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import com.bank.dto.UserDTO;
import com.bank.dto.UserWithImg;
import com.bank.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<?> getUsers() {
		return ResponseEntity.status(200).body(userService.list());
	}
	@GetMapping("listTarjetas")
	public ResponseEntity<?> getUsersI() {
		return ResponseEntity.status(200).body(userService.listI());
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable int id){
		return ResponseEntity.status(200).body(userService.find(id));
	}
	
	@GetMapping("/userTarjetas/{id}")
	public ResponseEntity<?> getUserByIdI(@PathVariable int id){
		return ResponseEntity.status(200).body(userService.findI(id));
	}
	
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody UserDTO user ){
		UserDTO u = userService.save(user);
		return ResponseEntity.status(201).body(u);
	}
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody UserDTO user ){
		UserDTO u = userService.update(user);
		return ResponseEntity.status(200).body(u);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id){
		String mensaje = userService.delete(id);
		Map<String, Object> mapper = new HashMap<String, Object>();
		 mapper.put("message", mensaje);
		return ResponseEntity.status(200).body(mapper);
	}
	
	
	@GetMapping("images/{img}")
	public ResponseEntity<?> getImagen(@PathVariable String img) throws IOException{
		Path path = Paths.get("src","main","resources","static","uploads", img);
		Resource resource = new UrlResource(path.toUri());
		 if (!resource.exists() || !resource.isReadable()) {
	            throw new RuntimeException("No se puede leer la imagen: " + img);
	        }
		 String contentType = URLConnection.guessContentTypeFromName(img);
		 if(contentType == null) {
			 contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
		 }
		 return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .body(resource);
	}
	
	
	@PostMapping(value = "/img", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> uploadImagen(@ModelAttribute UserWithImg userWithImg){
		return ResponseEntity.status(HttpStatus.OK).body(userService.actualizarUser(userWithImg));
	}
	
	
}
