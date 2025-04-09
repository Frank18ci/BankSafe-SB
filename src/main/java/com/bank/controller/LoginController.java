package com.bank.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.TarjetaDTO;
import com.bank.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;  
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody TarjetaDTO tarjetaDTO) {
        String jwt = loginService.login(tarjetaDTO);
        Map<String, Object> mapper = new HashMap<String, Object>();
        mapper.put("token", jwt);
        return ResponseEntity.ok(mapper);
    }
}
