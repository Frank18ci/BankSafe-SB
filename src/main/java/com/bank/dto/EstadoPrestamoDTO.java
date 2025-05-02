package com.bank.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.bank.model.EstadoPrestamo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstadoPrestamoDTO {
	private int id;
	private String estadoPrestamo;
	
	public static EstadoPrestamoDTO estadoPrestamoToEstadoPrestamoDTO(EstadoPrestamo estadoPrestamo) {
		return EstadoPrestamoDTO.builder()
				.id(estadoPrestamo.getId())
				.estadoPrestamo(estadoPrestamo.getEstadoPrestamo())
				.build();
	}
	public static List<EstadoPrestamoDTO> listEstadoPrestamoToListEstadoPrestamoDTO(List<EstadoPrestamo> estadoPrestamos) {
		return estadoPrestamos.stream()
				.map(EstadoPrestamoDTO::estadoPrestamoToEstadoPrestamoDTO)
				.collect(Collectors.toList());
	}
	public static EstadoPrestamo estadoPrestamoDTOToEstadoPrestamo(EstadoPrestamoDTO estadoPrestamoDTO) {
		return EstadoPrestamo.builder()
				.id(estadoPrestamoDTO.getId())
				.estadoPrestamo(estadoPrestamoDTO.getEstadoPrestamo())
				.build();
	}
}
