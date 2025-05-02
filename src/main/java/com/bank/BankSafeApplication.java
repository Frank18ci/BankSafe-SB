package com.bank;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bank.dto.EstadoPrestamoDTO;
import com.bank.dto.RoleUserDTO;
import com.bank.dto.TarjetaDTO;
import com.bank.dto.TipoDocumentoUserDTO;
import com.bank.dto.TipoMonedaTarjetaDTO;
import com.bank.dto.TipoPlazoDTO;
import com.bank.dto.TipoTarjetaDTO;
import com.bank.dto.TipoTransacionDTO;
import com.bank.dto.UserDTO;
import com.bank.model.EstadoPrestamo;
import com.bank.service.EstadoPrestamoService;
import com.bank.service.RoleUserService;
import com.bank.service.TarjetaService;
import com.bank.service.TipoDocumentoUserService;
import com.bank.service.TipoMonedaTarjetaService;
import com.bank.service.TipoPlazoService;
import com.bank.service.TipoTarjetaService;
import com.bank.service.TipoTransacionService;

@SpringBootApplication
public class BankSafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankSafeApplication.class, args);
	}
	
	@Bean
    CommandLineRunner cargarDatosIniciales(
    		TipoDocumentoUserService tipoDocumentoUserService,
    		RoleUserService roleUserService,
    		TipoTarjetaService tipoTarjetaService,
    		TipoMonedaTarjetaService tipoMonedaTarjetaService,
    		TipoPlazoService tipoPrestamoService,
    		TipoTransacionService tipoTransacionService,
    		EstadoPrestamoService estadoPrestamoService,
    		TarjetaService tarjetaService,
    		PasswordEncoder passwordEncoder
    		) {
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
        		.simbolo("PEN")
        		.simboloSecundario("S/.")
        		.build();
        	tipoMonedaTarjetaService.save(tipoMonedaTarjetaDTO);
        	
        	tipoMonedaTarjetaDTO= TipoMonedaTarjetaDTO.builder()
            		.tipo("DOLARES")
            		.simbolo("USD")
            		.simboloSecundario("$")
            		.build();
            tipoMonedaTarjetaService.save(tipoMonedaTarjetaDTO);
            tipoMonedaTarjetaDTO= TipoMonedaTarjetaDTO.builder()
            		.tipo("EUROS")
            		.simbolo("EUR")
            		.simboloSecundario("€")
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
            
            TipoPlazoDTO tipoPrestamoDTO = TipoPlazoDTO.builder()
            		.tipo("TRIMESTRAL")
            		.valorAnual(4)
            		.build();
            tipoPrestamoService.save(tipoPrestamoDTO);
            tipoPrestamoDTO = TipoPlazoDTO.builder()
            		.tipo("CUATRIMESTRAL")
            		.valorAnual(3)
            		.build();
            tipoPrestamoDTO = TipoPlazoDTO.builder()
            		.tipo("ANUAL")
            		.valorAnual(1)
            		.build();
            tipoPrestamoService.save(tipoPrestamoDTO);
            tipoPrestamoDTO = TipoPlazoDTO.builder()
                    .tipo("SEMESTRAL")
                    .valorAnual(2)
                    .build();
            tipoPrestamoService.save(tipoPrestamoDTO);
            tipoPrestamoDTO = TipoPlazoDTO.builder()
                    .tipo("MENSUAL")
                    .valorAnual(12)
                    .build();
            tipoPrestamoService.save(tipoPrestamoDTO);
            EstadoPrestamoDTO estadoPrestamoDTO = EstadoPrestamoDTO.builder()
            		.estadoPrestamo("Activo")
            		.build();
            estadoPrestamoService.save(estadoPrestamoDTO);
            estadoPrestamoDTO = EstadoPrestamoDTO.builder()
            		.estadoPrestamo("Pagado")
            		.build();
            estadoPrestamoService.save(estadoPrestamoDTO);
            estadoPrestamoDTO = EstadoPrestamoDTO.builder()
            		.estadoPrestamo("Vencido")
            		.build();
            estadoPrestamoService.save(estadoPrestamoDTO);
            TipoTransacionDTO tipoTransacionDTO = TipoTransacionDTO.builder()
            		.tipo("TRANSFERENCIA")
            		.build();
            tipoTransacionService.save(tipoTransacionDTO);
            tipoTransacionDTO = TipoTransacionDTO.builder()
            		.tipo("PAGO DE SERVICIO")
            		.build();
            tipoTransacionService.save(tipoTransacionDTO);
            
            TarjetaDTO tarjeta = TarjetaDTO.builder()
            	.cvv("123")
            	.monto(5640.0)
            	.numeroTarjeta("1234")
            	.claveInternet(passwordEncoder.encode("1234"))
            	.tipoTarjeta(TipoTarjetaDTO.builder()
            			.id(1)
            			.build())
            	.tipoMonedaTarjeta(TipoMonedaTarjetaDTO.builder()
            			.id(1)
            			.build())
            	.user(UserDTO.builder()
            			.nombres("Franklin")
            			.apellidos("Carpio")
            			.tipoDocumentoUser(TipoDocumentoUserDTO.builder()
            					.id(1)
            					.build())
            			.build())
            .build();
            tarjetaService.save(tarjeta);
            tarjeta = TarjetaDTO.builder()
                	.cvv("123")
                	.monto(10200.0)
                	.numeroTarjeta("12345")
                	.claveInternet(passwordEncoder.encode("12345"))
                	.tipoTarjeta(TipoTarjetaDTO.builder()
                			.id(1)
                			.build())
                	.tipoMonedaTarjeta(TipoMonedaTarjetaDTO.builder()
                			.id(2)
                			.build())
                	.user(UserDTO.builder()
                			.id(1)
                			.tipoDocumentoUser(TipoDocumentoUserDTO.builder()
                					.id(1)
                					.build())
                			.build())
                .build();
            tarjetaService.save(tarjeta);
            tarjeta = TarjetaDTO.builder()
                	.cvv("123")
                	.monto(10200.0)
                	.numeroTarjeta("123456")
                	.claveInternet(passwordEncoder.encode("123456"))
                	.tipoTarjeta(TipoTarjetaDTO.builder()
                			.id(2)
                			.build())
                	.tipoMonedaTarjeta(TipoMonedaTarjetaDTO.builder()
                			.id(3)
                			.build())
                	.user(UserDTO.builder()
                			.id(1)
                			.tipoDocumentoUser(TipoDocumentoUserDTO.builder()
                					.id(1)
                					.build())
                			.build())
                .build();
            tarjetaService.save(tarjeta);
            tarjeta = TarjetaDTO.builder()
                	.cvv("123")
                	.monto(6900.0)
                	.numeroTarjeta("1234567")
                	.claveInternet(passwordEncoder.encode("1234567"))
                	.tipoTarjeta(TipoTarjetaDTO.builder()
                			.id(1)
                			.build())
                	.tipoMonedaTarjeta(TipoMonedaTarjetaDTO.builder()
                			.id(3)
                			.build())
                	.user(UserDTO.builder()
                			.nombres("Rusbel")
                			.apellidos("Ramos")
                			.tipoDocumentoUser(TipoDocumentoUserDTO.builder()
                					.id(1)
                					.build())
                			.build())
                .build();
            tarjetaService.save(tarjeta);
            tarjeta = TarjetaDTO.builder()
                	.cvv("123")
                	.monto(10020.0)
                	.numeroTarjeta("12345678")
                	.claveInternet(passwordEncoder.encode("12345678"))
                	.tipoTarjeta(TipoTarjetaDTO.builder()
                			.id(2)
                			.build())
                	.tipoMonedaTarjeta(TipoMonedaTarjetaDTO.builder()
                			.id(2)
                			.build())
                	.user(UserDTO.builder()
                			.id(2)
                			.tipoDocumentoUser(TipoDocumentoUserDTO.builder()
                					.id(1)
                					.build())
                			.build())
                .build();
            tarjetaService.save(tarjeta);
            tarjeta = TarjetaDTO.builder()
                	.cvv("123")
                	.monto(10200.0)
                	.numeroTarjeta("123456789")
                	.claveInternet(passwordEncoder.encode("123456789"))
                	.tipoTarjeta(TipoTarjetaDTO.builder()
                			.id(2)
                			.build())
                	.tipoMonedaTarjeta(TipoMonedaTarjetaDTO.builder()
                			.id(1)
                			.build())
                	.user(UserDTO.builder()
                			.id(2)
                			.tipoDocumentoUser(TipoDocumentoUserDTO.builder()
                					.id(1)
                					.build())
                			.build())
                .build();
            tarjetaService.save(tarjeta);
            tarjeta = TarjetaDTO.builder()
                	.cvv("123")
                	.monto(5000.0)
                	.numeroTarjeta("12")
                	.claveInternet(passwordEncoder.encode("12"))
                	.tipoTarjeta(TipoTarjetaDTO.builder()
                			.id(2)
                			.build())
                	.tipoMonedaTarjeta(TipoMonedaTarjetaDTO.builder()
                			.id(2)
                			.build())
                	.user(UserDTO.builder()
                			.nombres("Jose")
                			.apellidos("Chuma")
                			.tipoDocumentoUser(TipoDocumentoUserDTO.builder()
                					.id(1)
                					.build())
                			.build())
                .build();
                tarjetaService.save(tarjeta);
                tarjeta = TarjetaDTO.builder()
                    	.cvv("123")
                    	.monto(10000.0)
                    	.numeroTarjeta("123")
                    	.claveInternet(passwordEncoder.encode("123"))
                    	.tipoTarjeta(TipoTarjetaDTO.builder()
                    			.id(2)
                    			.build())
                    	.tipoMonedaTarjeta(TipoMonedaTarjetaDTO.builder()
                    			.id(3)
                    			.build())
                    	.user(UserDTO.builder()
                    			.id(3)
                    			.tipoDocumentoUser(TipoDocumentoUserDTO.builder()
                    					.id(1)
                    					.build())
                    			.build())
                    .build();
                tarjetaService.save(tarjeta);
                tarjeta = TarjetaDTO.builder()
                    	.cvv("123")
                    	.monto(1000.0)
                    	.numeroTarjeta("1")
                    	.claveInternet(passwordEncoder.encode("1"))
                    	.tipoTarjeta(TipoTarjetaDTO.builder()
                    			.id(1)
                    			.build())
                    	.tipoMonedaTarjeta(TipoMonedaTarjetaDTO.builder()
                    			.id(1)
                    			.build())
                    	.user(UserDTO.builder()
                    			.id(3)
                    			.tipoDocumentoUser(TipoDocumentoUserDTO.builder()
                    					.id(1)
                    					.build())
                    			.build())
                    .build();
                tarjetaService.save(tarjeta);
        };
    }
}
