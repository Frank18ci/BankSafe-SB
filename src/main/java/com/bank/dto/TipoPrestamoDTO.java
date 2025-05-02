package com.bank.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.bank.model.TipoPrestamo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoPrestamoDTO {

	private int id;
	private String tipo;
	
	public static TipoPrestamoDTO tipoPrestamoToTipoPrestamoDTO(TipoPrestamo tipoPrestamo) {
		return TipoPrestamoDTO.builder()
				.id(tipoPrestamo.getId())
				.tipo(tipoPrestamo.getTipo())
				.build();
	}
	public static List<TipoPrestamoDTO> listTipoPretamoToListTipoPrestamoDTO(List<TipoPrestamo> tipoPrestamos){
		return tipoPrestamos.stream()
				.map(TipoPrestamoDTO::tipoPrestamoToTipoPrestamoDTO)
				.collect(Collectors.toList());
	}
	public static TipoPrestamo tipoPrestamoDTOToTipoPrestamo(TipoPrestamoDTO tipoPrestamoDTO) {
		return TipoPrestamo.builder()
				.id(tipoPrestamoDTO.getId())
				.tipo(tipoPrestamoDTO.getTipo())
				.build();
	}
}
