package com.bank.dto;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.bank.model.EstadoPrestamo;
import com.bank.model.Prestamo;
import com.bank.model.Tarjeta;
import com.bank.model.TipoPlazo;
import com.bank.model.User;

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
	private double monto;
	private double montoPrestamo;
	private double montoPagado;
	private double montoPorPlazo;
	private double interesAnual;
    private Integer plazos;
    
    private Date fechaInicio;
    private Date fechaFin;
	private Date FechaRegistro;
	private Date FechaFinalizado;
	
	private EstadoPrestamoDTO estadoPrestamo;
	private UserDTO user;
	private TipoPlazoDTO tipoPlazo;
	private TarjetaDTO tarjetaRecepcion;
	
	public static PrestamoDTO prestamoToPrestamoDTO(Prestamo prestamo) {
		return PrestamoDTO.builder()
				.monto(prestamo.getMonto())
				.montoPrestamo(prestamo.getMontoPrestamo())
				.montoPagado(prestamo.getMontoPagado())
				.montoPorPlazo(prestamo.getMontoPorPlazo())
				.plazos(prestamo.getPlazos())
				.fechaInicio(prestamo.getFechaInicio())
				.fechaFin(prestamo.getFechaFin())
				.FechaRegistro(prestamo.getFechaRegistro())
				.FechaFinalizado(prestamo.getFechaFinalizado())
				.estadoPrestamo(
					    EstadoPrestamoDTO.estadoPrestamoToEstadoPrestamoDTO(
					        Optional.ofNullable(prestamo.getEstadoPrestamo())
					                .orElse(EstadoPrestamo.builder().build())))
				.user(UserDTO.userToUserDTO(
						Optional.ofNullable(prestamo.getUser())
									.orElse(User.builder().build())))
				.tarjetaRecepcion(TarjetaDTO.tarjetaToTarjetaDTO(
							Optional.ofNullable(prestamo.getTarjetaRecepcion())
									.orElse(Tarjeta.builder().build())))
				.tipoPlazo(TipoPlazoDTO.tipoPlazoToTipoPlazoDTO(
							Optional.ofNullable(prestamo.getTipoPlazo()).orElse(TipoPlazo.builder().build())))
				.build();
	}
	public static List<PrestamoDTO> listPrestamoToListPrestamoDTO(List<Prestamo> prestamos){
		return prestamos.stream()
				.map(PrestamoDTO::prestamoToPrestamoDTO)
				.collect(Collectors.toList());
	}
	public static Prestamo prestamoDTOToPrestamo(PrestamoDTO prestamoDTO) {
		return Prestamo.builder()
				.monto(prestamoDTO.getMonto())
				.montoPrestamo(prestamoDTO.getMontoPrestamo())
				.montoPagado(prestamoDTO.getMontoPagado())
				.montoPorPlazo(prestamoDTO.getMontoPorPlazo())
				.plazos(prestamoDTO.getPlazos())
				.fechaInicio(prestamoDTO.getFechaInicio())
				.fechaFin(prestamoDTO.getFechaFin())
				.FechaRegistro(prestamoDTO.getFechaRegistro())
				.FechaFinalizado(prestamoDTO.getFechaFinalizado())
				.estadoPrestamo(
					    EstadoPrestamoDTO.estadoPrestamoDTOToEstadoPrestamo(
					        Optional.ofNullable(prestamoDTO.getEstadoPrestamo())
					                .orElse(EstadoPrestamoDTO.builder().build())))
				.user(UserDTO.userDTOToUser(
						Optional.ofNullable(prestamoDTO.getUser())
									.orElse(UserDTO.builder().build())))
				.tarjetaRecepcion(TarjetaDTO.tarjetaDTOTotarjeta(
							Optional.ofNullable(prestamoDTO.getTarjetaRecepcion())
									.orElse(TarjetaDTO.builder().build())))
				.tipoPlazo(TipoPlazoDTO.tipoPlazoDTOToTipoPlazo(
							Optional.ofNullable(prestamoDTO.getTipoPlazo()).orElse(TipoPlazoDTO.builder().build())))
				.build();
	}
	
}
