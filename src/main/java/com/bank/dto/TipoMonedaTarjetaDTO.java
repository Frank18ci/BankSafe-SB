package com.bank.dto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoMonedaTarjetaDTO {
	private int id;
	private String nombre;
	private String simbolo;
	private String tipo;
}
