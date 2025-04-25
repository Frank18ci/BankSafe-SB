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
public class TransaccionConversionMonedaDTO {
	private int id;
	private BigDecimal montoTarjetaOrigen;
	private BigDecimal montoTarjetaDestino;
	private Date fecha;
	private TipoTransacionDTO tipoTransacion;
	private TarjetaDTO tarjetaOrigen;
	private TarjetaDTO tarjetaDestino;
}
