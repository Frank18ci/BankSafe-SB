package com.bank.apicambiomoneda.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversionTipoMonedaDTO {
	private int id;
	private String tipo;
	private String simbolo;
	private Double valor;
}
