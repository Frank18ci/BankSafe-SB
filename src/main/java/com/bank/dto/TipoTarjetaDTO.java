package com.bank.dto;


import java.util.List;
import java.util.stream.Collectors;

import com.bank.model.TipoTarjeta;

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
	private String tipo;
	
	public static TipoTarjetaDTO tipoTarjetaToTipoTarjetaDTO(TipoTarjeta tipoTarjeta) {
		return TipoTarjetaDTO.builder()
				.id(tipoTarjeta.getId())
				.tipo(tipoTarjeta.getTipo())
				.build();
	}
	public static List<TipoTarjetaDTO> listTipoTarjetaToListTipoTarjetaDTO(List<TipoTarjeta> tipoTarjetas){
		return tipoTarjetas.stream()
				.map(TipoTarjetaDTO::tipoTarjetaToTipoTarjetaDTO)
				.collect(Collectors.toList());
	}
	public static TipoTarjeta tipoTarjetaDTOToTipoTarjeta(TipoTarjetaDTO tipoTarjetaDTO) {
		return TipoTarjeta.builder()
				.id(tipoTarjetaDTO.getId())
				.tipo(tipoTarjetaDTO.getTipo())
				.build();
	}
}
