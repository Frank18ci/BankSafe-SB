package com.bank.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TarjetaDTO {
	private int id;
	private String claveInternet;
	private String cvv;
	private String numeroTarjeta;
	private Date fechaVencimiento;
	
	private UserDTO user;
	private TipoTarjetaDTO tipoTarjetaDTO;
	private TipoMonedaTarjetaDTO tipoMonedaTarjetaDTO;
}
