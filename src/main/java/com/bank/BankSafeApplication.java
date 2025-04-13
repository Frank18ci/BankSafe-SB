package com.bank;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bank.dto.RoleUserDTO;
import com.bank.dto.TipoDocumentoUserDTO;
import com.bank.dto.TipoMonedaTarjetaDTO;
import com.bank.dto.TipoTarjetaDTO;
import com.bank.service.RoleUserService;
import com.bank.service.TipoDocumentoUserService;
import com.bank.service.TipoMonedaTarjetaService;
import com.bank.service.TipoTarjetaService;

@SpringBootApplication
public class BankSafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankSafeApplication.class, args);
	}
	
	@Bean
    CommandLineRunner cargarDatosIniciales(TipoDocumentoUserService tipoDocumentoUserService, RoleUserService roleUserService, TipoTarjetaService tipoTarjetaService, TipoMonedaTarjetaService tipoMonedaTarjetaService) {
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
        	TipoMonedaTarjetaDTO tipoMonedaTarjetaDTO= TipoMonedaTarjetaDTO.builder()
        		.tipo("SOLES")
        		.build();
        	tipoMonedaTarjetaService.save(tipoMonedaTarjetaDTO);
        	
        	tipoMonedaTarjetaDTO= TipoMonedaTarjetaDTO.builder()
            		.tipo("DOLARES")
            		.build();
            tipoMonedaTarjetaService.save(tipoMonedaTarjetaDTO);
            TipoTarjetaDTO tipoTarjetaDTO = TipoTarjetaDTO.builder()
            		.tipo("CREDITO")
            		.build();
            tipoTarjetaService.save(tipoTarjetaDTO);
            tipoTarjetaDTO = TipoTarjetaDTO.builder()
            		.tipo("DEBITO")
            		.build();
            tipoTarjetaService.save(tipoTarjetaDTO);
            
        };
    }
}
