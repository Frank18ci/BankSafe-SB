package com.bank;

import java.util.Calendar;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bank.dto.EmpresaDTO;
import com.bank.dto.EstadoPagoServicioDTO;
import com.bank.dto.EstadoPrestamoDTO;
import com.bank.dto.PagoServicioDTO;
import com.bank.dto.RoleUserDTO;
import com.bank.dto.ServicioDTO;
import com.bank.dto.TarjetaDTO;
import com.bank.dto.TipoDocumentoUserDTO;
import com.bank.dto.TipoEmpresaDTO;
import com.bank.dto.TipoMonedaTarjetaDTO;
import com.bank.dto.TipoPlazoDTO;
import com.bank.dto.TipoTarjetaDTO;
import com.bank.dto.TipoTransacionDTO;
import com.bank.dto.UserDTO;
import com.bank.service.EmpresaService;
import com.bank.service.EstadoPagoServicioService;
import com.bank.service.EstadoPrestamoService;
import com.bank.service.PagoServicioService;
import com.bank.service.RoleUserService;
import com.bank.service.ServicioService;
import com.bank.service.TarjetaService;
import com.bank.service.TipoDocumentoUserService;
import com.bank.service.TipoEmpresaService;
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
    		TipoEmpresaService tipoEmpresaService,
    		EmpresaService empresaService,
    		ServicioService servicioService,
    		EstadoPagoServicioService estadoPagoServicioService,
    		PagoServicioService pagoServicioService,
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
                
                //Agregando servicios
        		//TipoEmpresaService tipoEmpresaService,
        		//EmpresaService empresaService,
        		//ServicioService servicioService,
        		//EstadoPagoServicioService estadoPagoServicioService,
        		//PagoServicioService pagoServicioService,
                TipoEmpresaDTO tipoInternet = tipoEmpresaService.save(
                        TipoEmpresaDTO.builder().descripcion("Internet").build()
                );
                tipoEmpresaService.save(tipoInternet);
                TipoEmpresaDTO tipoAgua = tipoEmpresaService.save(
                        TipoEmpresaDTO.builder().descripcion("Agua").build()
                );
                tipoEmpresaService.save(tipoAgua);
                TipoEmpresaDTO tipoElectricidad = tipoEmpresaService.save(
                        TipoEmpresaDTO.builder().descripcion("Electricidad").build()
                );
                tipoEmpresaService.save(tipoElectricidad);
        		
        		EmpresaDTO empresaDTO = EmpresaDTO.builder()
        				.nombre("Perú Seal")
        				.tipoEmpresa(TipoEmpresaDTO.builder()
        						.id(3)
        						.build())
        				.build();
        		empresaService.save(empresaDTO);
        		empresaDTO = EmpresaDTO.builder()
        				.nombre("Sedapar")
        				.tipoEmpresa(TipoEmpresaDTO.builder()
        						.id(2)
        						.build())
        				.build();
        		empresaService.save(empresaDTO);
        		empresaDTO = EmpresaDTO.builder()
        				.nombre("Claro")
        				.tipoEmpresa(TipoEmpresaDTO.builder()
        						.id(1)
        						.build())
        				.build();
        		empresaService.save(empresaDTO);
        		empresaDTO = EmpresaDTO.builder()
        				.nombre("Win")
        				.tipoEmpresa(TipoEmpresaDTO.builder()
        						.id(1)
        						.build())
        				.build();
        		empresaService.save(empresaDTO);
        		empresaDTO = EmpresaDTO.builder()
        				.nombre("Movistar")
        				.tipoEmpresa(TipoEmpresaDTO.builder()
        						.id(1)
        						.build())
        				.build();
        		empresaService.save(empresaDTO);
        		
        		
        		
        		ServicioDTO servicioDTO = ServicioDTO.builder()
        				.codigo("PERU-001")
        				.descripcion("Servicio Eléctrico Perú Seal")
        				.empresa(EmpresaDTO.builder()
        						.id(1)
        						.build())
        				.build();
        		servicioService.save(servicioDTO);
        		servicioDTO = ServicioDTO.builder()
        				.codigo("SEDA-001")
        				.descripcion("Servicio de Agua Sedapar")
        				.empresa(EmpresaDTO.builder()
        						.id(2)
        						.build())
        				.build();
        		servicioService.save(servicioDTO);
        		servicioDTO = ServicioDTO.builder()
        				.codigo("CLARO-001")
        				.descripcion("Servicio de Internet Claro")
        				.empresa(EmpresaDTO.builder()
        						.id(3)
        						.build())
        				.build();
        		servicioService.save(servicioDTO);
        		servicioDTO = ServicioDTO.builder()
        				.codigo("WIN-001")
        				.descripcion("Servicio de Internet Win")
        				.empresa(EmpresaDTO.builder()
        						.id(4)
        						.build())
        				.build();
        		servicioService.save(servicioDTO);
        		servicioDTO = ServicioDTO.builder()
        				.codigo("MOVI-001")
        				.descripcion("Servicio de Internet Movistar")
        				.empresa(EmpresaDTO.builder()
        						.id(5)
        						.build())
        				.build();
        		servicioService.save(servicioDTO);
        		
        		
        		
        		EstadoPagoServicioDTO estadoPagoServicioDTO = EstadoPagoServicioDTO.builder()
        				.estadoServicio("Pagado")
        				.build();
        		estadoPagoServicioService.save(estadoPagoServicioDTO);
        		estadoPagoServicioDTO = EstadoPagoServicioDTO.builder()
        				.estadoServicio("Pendiente")
        				.build();
        		estadoPagoServicioService.save(estadoPagoServicioDTO);
        		
        		
        		Calendar calendar = Calendar.getInstance();
        		calendar.add(Calendar.DAY_OF_MONTH, 10);
        		Date fechaDiezDias = calendar.getTime();

        		PagoServicioDTO pagoServicio1 = PagoServicioDTO.builder()
        		        .montoPago(100.00)
        		        .fechaPago(fechaDiezDias)
        		        .servicio(ServicioDTO.builder().id(1).build())
        		        .estadoPagoServicio(EstadoPagoServicioDTO.builder().id(2).build())
        		        .build();
        		pagoServicioService.save(pagoServicio1);
        		PagoServicioDTO pagoServicio2 = PagoServicioDTO.builder()
        		        .montoPago(150.00)
        		        .fechaPago(fechaDiezDias)
        		        .servicio(ServicioDTO.builder().id(2).build())
        		        .estadoPagoServicio(EstadoPagoServicioDTO.builder().id(2).build())
        		        .build();
        		pagoServicioService.save(pagoServicio2);
        		PagoServicioDTO pagoServicio3 = PagoServicioDTO.builder()
        		        .montoPago(200.00)
        		        .fechaPago(fechaDiezDias)
        		        .servicio(ServicioDTO.builder().id(3).build())
        		        .estadoPagoServicio(EstadoPagoServicioDTO.builder().id(2).build())
        		        .build();
        		pagoServicioService.save(pagoServicio3);
        		PagoServicioDTO pagoServicio4 = PagoServicioDTO.builder()
        		        .montoPago(250.00)
        		        .fechaPago(fechaDiezDias)
        		        .servicio(ServicioDTO.builder().id(4).build())
        		        .estadoPagoServicio(EstadoPagoServicioDTO.builder().id(2).build())
        		        .build();
        		pagoServicioService.save(pagoServicio4);
        		PagoServicioDTO pagoServicio5 = PagoServicioDTO.builder()
        		        .montoPago(300.00)
        		        .fechaPago(fechaDiezDias)
        		        .servicio(ServicioDTO.builder().id(5).build())
        		        .estadoPagoServicio(EstadoPagoServicioDTO.builder().id(2).build())
        		        .build();
        		pagoServicioService.save(pagoServicio5);
        		PagoServicioDTO pagoServicio6 = PagoServicioDTO.builder()
        		        .montoPago(350.00)
        		        .fechaPago(fechaDiezDias)
        		        .servicio(ServicioDTO.builder().id(1).build())
        		        .estadoPagoServicio(EstadoPagoServicioDTO.builder().id(2).build())
        		        .build();
        		pagoServicioService.save(pagoServicio6);
        		PagoServicioDTO pagoServicio7 = PagoServicioDTO.builder()
        		        .montoPago(400.00)
        		        .fechaPago(fechaDiezDias)
        		        .servicio(ServicioDTO.builder().id(2).build())
        		        .estadoPagoServicio(EstadoPagoServicioDTO.builder().id(2).build())
        		        .build();
        		pagoServicioService.save(pagoServicio7);
        		PagoServicioDTO pagoServicio8 = PagoServicioDTO.builder()
        		        .montoPago(450.00)
        		        .fechaPago(fechaDiezDias)
        		        .servicio(ServicioDTO.builder().id(3).build())
        		        .estadoPagoServicio(EstadoPagoServicioDTO.builder().id(2).build())
        		        .build();
        		pagoServicioService.save(pagoServicio8);
        		PagoServicioDTO pagoServicio9 = PagoServicioDTO.builder()
        		        .montoPago(500.00)
        		        .fechaPago(fechaDiezDias)
        		        .servicio(ServicioDTO.builder().id(4).build())
        		        .estadoPagoServicio(EstadoPagoServicioDTO.builder().id(2).build())
        		        .build();
        		pagoServicioService.save(pagoServicio9);
        		PagoServicioDTO pagoServicio10 = PagoServicioDTO.builder()
        		        .montoPago(550.00)
        		        .fechaPago(fechaDiezDias)
        		        .servicio(ServicioDTO.builder().id(5).build())
        		        .estadoPagoServicio(EstadoPagoServicioDTO.builder().id(2).build())
        		        .build();
        		pagoServicioService.save(pagoServicio10);
        		
        		
        };
    }
}
