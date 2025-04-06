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
public class PrestamoDTO {
	
	private int id;
	private BigDecimal montoPrestamo;
	private BigDecimal interes;
	private Integer plazoMeses;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
	private int intTipoPrestamo;
	private Date FechaRegistro;
	private Date FechaFinalizado;
}
