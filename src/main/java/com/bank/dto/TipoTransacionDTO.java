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
public class TipoTransacionDTO {
	private int id;
	private BigDecimal monto;
	private Date fecha;
	private String descripcion;
}
