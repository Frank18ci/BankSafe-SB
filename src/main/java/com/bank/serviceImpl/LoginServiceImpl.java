package com.bank.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dto.TarjetaDTO;
import com.bank.security.jwt.JwtUtils;
import com.bank.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private JwtUtils jwtUtils;
	
	public String login(TarjetaDTO tarjetaDTO) {
		
		String jwt = jwtUtils.generateAccesToken(tarjetaDTO.getNumeroTarjeta());
		System.out.println(jwtUtils.getUsernameFromToken(jwt));
		return jwt;
	}
}
