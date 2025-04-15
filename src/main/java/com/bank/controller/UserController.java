package com.bank.controller;

import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable int id){
		return ResponseEntity.status(200).body(userService.find(id));
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
	
	
	@PostMapping("/img")
	public ResponseEntity<?> uploadImagen(@RequestParam("image") MultipartFile file){
		if(file.isEmpty()) {
			System.out.println("Error al subir imagen");
			return ResponseEntity.status(500).body("Imagen vacia");
		}
		try {
			System.out.println("file " +  file);
			System.out.println("Intento");
			
			Path directorioImagen = Paths.get("src","main","resources","static","uploads");
			String rutaAbsoluta = directorioImagen.toFile().getAbsolutePath();
			byte[] byteImg = file.getBytes();
			//Aca falta agregar el id de usuario o otro dato
			String pathnuevo = file.getOriginalFilename();
			Path rutaCompleta = Paths.get(rutaAbsoluta, pathnuevo);
			
			Files.write(rutaCompleta, byteImg);
			Map<String, String> response = new HashMap<>();
			response.put("message", "Imagen subida");
			response.put("url", pathnuevo);
			return ResponseEntity.ok(response);
		} catch (IOException e) {
			return ResponseEntity.status(500).body("Error al subir la imagen");
		}
	}
	
	
}
