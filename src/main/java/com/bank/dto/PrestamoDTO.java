package com.bank.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.bank.model.User;

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
	private Date FechaRegistro;
	private Date FechaFinalizado;
	//
	private UserDTO userDTO;
	private TipoPrestamoDTO tipoPrestamoDTO;
}
