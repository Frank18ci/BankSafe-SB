package com.bank.dto;


import java.util.List;
import java.util.stream.Collectors;

import com.bank.model.TipoMonedaTarjeta;

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
	private String simboloSecundario;
	private String simbolo;
	private String tipo;
	
	public static TipoMonedaTarjetaDTO tipoMonedaTarjetaToTipoMonedaTarjetaDTO(TipoMonedaTarjeta tipoMonedaTarjeta) {
		return TipoMonedaTarjetaDTO.builder()
				.id(tipoMonedaTarjeta.getId())
				.simboloSecundario(tipoMonedaTarjeta.getSimboloSecundario())
				.simbolo(tipoMonedaTarjeta.getSimbolo())
				.tipo(tipoMonedaTarjeta.getTipo())
				.build();
	}
	public static List<TipoMonedaTarjetaDTO> listTipoMonedaTarjetaToListTipoMonedaTarjetaDTO(List<TipoMonedaTarjeta> tipoMonedaTarjetas){
		return tipoMonedaTarjetas.stream()
				.map(TipoMonedaTarjetaDTO::tipoMonedaTarjetaToTipoMonedaTarjetaDTO)
				.collect(Collectors.toList());
	}
	public static TipoMonedaTarjeta tipoMonedaTarjetaDTOToTipoMonedaTarjeta(TipoMonedaTarjetaDTO tipoMonedaTarjetaDTO) {
		return TipoMonedaTarjeta.builder()
				.id(tipoMonedaTarjetaDTO.getId())
				.simboloSecundario(tipoMonedaTarjetaDTO.getSimboloSecundario())
				.simbolo(tipoMonedaTarjetaDTO.getSimbolo())
				.tipo(tipoMonedaTarjetaDTO.getTipo())
				.build();
	}
}
