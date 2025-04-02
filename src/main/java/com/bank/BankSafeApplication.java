package com.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bank.dto.RoleUserDTO;
import com.bank.service.RoleUserService;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;

@SpringBootApplication
@Slf4j
public class BankSafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankSafeApplication.class, args);
	}
	
	//@Autowired 
	//private RoleUserService roleUserService;
	
//	@Bean
//	ApplicationRunner init() {
//		return args -> {
//			RoleUserDTO roleUserDTO = RoleUserDTO.builder()
//					.id(1)
//					.tipo("USUARIO")
//					.build();
//			roleUserService.saveUserRole(roleUserDTO);
//			roleUserDTO = RoleUserDTO.builder()
//					.id(1)
//					.tipo("ADMIND")
//					.build();
//			roleUserService.saveUserRole(roleUserDTO);
//			log.info("This is RoleList", roleUserService.listUserRols());
//		};
//	}
}
