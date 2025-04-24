package com.bank.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.bank.model.Prestamo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrestamoDTO {
	
	private int id;
	private BigDecimal montoPrestamo;
	private BigDecimal interes;
	private Integer plazoMeses;
    private Date fechaInicio;
    private Date fechaFin;
	private Date FechaRegistro;
	private Date FechaFinalizado;
	
	private EstadoPrestamoDTO estadoPrestamo;
	private UserDTO user;
	private TipoPrestamoDTO tipoPrestamo;
	
	public static PrestamoDTO prestamoToPrestamoDTO(Prestamo prestamo) {
		return PrestamoDTO.builder()
				.montoPrestamo(prestamo.getMontoPrestamo())
				.interes(prestamo.getInteres())
				.plazoMeses(prestamo.getPlazoMeses())
				.fechaInicio(prestamo.getFechaInicio())
				.fechaFin(prestamo.getFechaFin())
				.estadoPrestamo(EstadoPrestamoDTO.estadoPrestamoToEstadoPrestamoDTO(prestamo.getEstadoPrestamo()))
				.FechaRegistro(prestamo.getFechaRegistro())
				.FechaFinalizado(prestamo.getFechaFinalizado())
				.user(UserDTO.userToUserDTO(prestamo.getUser()))
				.tipoPrestamo(TipoPrestamoDTO.tipoPrestamoToTipoPrestamoDTO(prestamo.getTipoPrestamo()))
				.build();
	}
	public static List<PrestamoDTO> listPrestamoToListPrestamoDTO(List<Prestamo> prestamos){
		return prestamos.stream()
				.map(PrestamoDTO::prestamoToPrestamoDTO)
				.collect(Collectors.toList());
	}
	public static Prestamo prestamoDTOToPrestamo(PrestamoDTO prestamoDTO) {
		return Prestamo.builder()
				.montoPrestamo(prestamoDTO.getMontoPrestamo())
				.interes(prestamoDTO.getInteres())
				.plazoMeses(prestamoDTO.getPlazoMeses())
				.fechaInicio(prestamoDTO.getFechaInicio())
				.fechaFin(prestamoDTO.getFechaFin())
				.estadoPrestamo(EstadoPrestamoDTO.estadoPrestamoDTOToEstadoPrestamo(prestamoDTO.getEstadoPrestamo()))
				.FechaRegistro(prestamoDTO.getFechaRegistro())
				.FechaFinalizado(prestamoDTO.getFechaFinalizado())
				.user(UserDTO.userDTOToUser(prestamoDTO.getUser()))
				.tipoPrestamo(TipoPrestamoDTO.tipoPrestamoDTOToTipoPrestamo(prestamoDTO.getTipoPrestamo()))
				.build();
	}
	
}
