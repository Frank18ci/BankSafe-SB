package com.bank.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.bank.model.TipoPlazo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoPlazoDTO {

	private int id;
	private String tipo;
	private int valorAnual;
	
	public static TipoPlazoDTO tipoPlazoToTipoPlazoDTO(TipoPlazo tipoPlazo) {
		return TipoPlazoDTO.builder()
				.id(tipoPlazo.getId())
				.tipo(tipoPlazo.getTipo())
				.valorAnual(tipoPlazo.getValorAnual())
				.build();
	}
	public static List<TipoPlazoDTO> listTipoPretamoToListTipoPrestamoDTO(List<TipoPlazo> tipoPlazos){
		return tipoPlazos.stream()
				.map(TipoPlazoDTO::tipoPlazoToTipoPlazoDTO)
				.collect(Collectors.toList());
	}
	public static TipoPlazo tipoPlazoDTOToTipoPlazo(TipoPlazoDTO tipoPlazoDTO) {
		return TipoPlazo.builder()
				.id(tipoPlazoDTO.getId())
				.tipo(tipoPlazoDTO.getTipo())
				.valorAnual(tipoPlazoDTO.getValorAnual())
				.build();
	}
}
