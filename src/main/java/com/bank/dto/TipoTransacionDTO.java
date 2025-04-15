package com.bank.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.bank.model.TipoTransacion;

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
	public static TipoTransacionDTO tipoTransacionToTipoTransacionDTO(TipoTransacion tipoTransacion) {
		return TipoTransacionDTO.builder()
				.id(tipoTransacion.getId())
				.monto(tipoTransacion.getMonto())
				.fecha(tipoTransacion.getFecha())
				.descripcion(tipoTransacion.getDescripcion())
				.build();
	}
	public static List<TipoTransacionDTO> listTipoTransacionToListTipoTransacionDTO(List<TipoTransacion> tipoTransaciones){
		return tipoTransaciones.stream()
				.map(TipoTransacionDTO::tipoTransacionToTipoTransacionDTO)
				.collect(Collectors.toList());
	}
	public static TipoTransacion tipoTransacionDTOToTipoTransacion(TipoTransacionDTO tipoTransacionDTO) {
		return TipoTransacion.builder()
				.id(tipoTransacionDTO.getId())
				.monto(tipoTransacionDTO.getMonto())
				.fecha(tipoTransacionDTO.getFecha())
				.descripcion(tipoTransacionDTO.getDescripcion())
				.build();
	}
}
