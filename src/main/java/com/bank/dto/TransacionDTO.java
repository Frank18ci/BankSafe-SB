package com.bank.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransacionDTO {
	private int id;
	private BigDecimal monto;
	private String descripcion;
	private Date fecha;
	//
	private TipoTransacionDTO tipoTransacionDTO;
	private TarjetaDTO tarjetaDTO;
	private UserDTO userDTO;
}
