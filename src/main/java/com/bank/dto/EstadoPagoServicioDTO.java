package com.bank.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.bank.model.EstadoPagoServicio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstadoPagoServicioDTO {
	private int id;	
	private String estadoServicio;
	public static EstadoPagoServicioDTO estadoPagoServicioToEstadoPagoServicioDTO(EstadoPagoServicio estadoPagoServicio) {
		return EstadoPagoServicioDTO.builder()
				.id(estadoPagoServicio.getId())
				.estadoServicio(estadoPagoServicio.getEstadoServicio())
				.build();
	}
	public static List<EstadoPagoServicioDTO> listaEstadoPagoServicioToListaEstadoPagoServicioDTO(List<EstadoPagoServicio> estadoPagoServicios){
		return estadoPagoServicios.stream().map(EstadoPagoServicioDTO::estadoPagoServicioToEstadoPagoServicioDTO)
				.collect(Collectors.toList());
	}
	public static EstadoPagoServicio estadoPagoServicioDTOToEstadoPagoServicio(EstadoPagoServicioDTO estadoPagoServicioDTO) {
		return EstadoPagoServicio.builder()
				.id(estadoPagoServicioDTO.getId())
				.estadoServicio(estadoPagoServicioDTO.getEstadoServicio())
				.build();
	}
}
