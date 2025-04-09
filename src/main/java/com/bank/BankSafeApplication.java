package com.bank;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bank.dto.RoleUserDTO;
import com.bank.dto.TipoDocumentoUserDTO;
import com.bank.service.RoleUserService;
import com.bank.service.TipoDocumentoUserService;

@SpringBootApplication
public class BankSafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankSafeApplication.class, args);
	}
	
	@Bean
    CommandLineRunner cargarDatosIniciales(TipoDocumentoUserService tipoDocumentoUserService, RoleUserService roleUserService) {
        return args -> {
        	TipoDocumentoUserDTO tipoDocumentoUserDTO = TipoDocumentoUserDTO.builder()
        			.tipo("DNI")
        			.build();
        	tipoDocumentoUserService.save(tipoDocumentoUserDTO);
        	tipoDocumentoUserDTO = TipoDocumentoUserDTO.builder()
        			.tipo("Pasaporte")
        			.build();
        	tipoDocumentoUserService.save(tipoDocumentoUserDTO);
        	tipoDocumentoUserDTO = TipoDocumentoUserDTO.builder()
        			.tipo("Carné de Extranjería")
        			.build();
        	tipoDocumentoUserService.save(tipoDocumentoUserDTO);
        	RoleUserDTO roleUserDTO = RoleUserDTO.builder()
        			.tipo("ADMIND")
        			.build();
        	roleUserService.save(roleUserDTO);
        	roleUserDTO = RoleUserDTO.builder()
        			.tipo("USUARIO")
        			.build();
        	roleUserService.save(roleUserDTO);
        };
    }
}
