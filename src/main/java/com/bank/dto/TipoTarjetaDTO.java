package com.bank.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoTarjetaDTO {
	private int id;
	private String nombre;
	private String tipo;
}
