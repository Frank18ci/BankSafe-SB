package com.bank.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.bank.model.EstadoPagoServicio;
import com.bank.model.PagoServicio;
import com.bank.model.Servicio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagoServicioDTO {
	private int id;	
	private double montoPago;
	private Date fechaPago;
	private ServicioDTO servicio;
	private EstadoPagoServicioDTO estadoPagoServicio;
	public static PagoServicioDTO pagoServicioToPagoServicioDTO(PagoServicio pagoServicio) {
		return PagoServicioDTO.builder()
				.id(pagoServicio.getId())
				.montoPago(pagoServicio.getMontoPago())
				.fechaPago(pagoServicio.getFechaPago())
				.servicio(pagoServicio.getServicio() != null ?
						ServicioDTO.servicioToServicioDTO(pagoServicio.getServicio()) :
						ServicioDTO.builder().build())
				.estadoPagoServicio(pagoServicio.getEstadoPagoServicio() != null ?
						EstadoPagoServicioDTO.estadoPagoServicioToEstadoPagoServicioDTO(pagoServicio.getEstadoPagoServicio()):
						EstadoPagoServicioDTO.builder().build())
				.build();
	}
	public static List<PagoServicioDTO> listaPagoServicioToListaPagoServicioDTO(List<PagoServicio> pagoServicios){
		return pagoServicios.stream().map(PagoServicioDTO::pagoServicioToPagoServicioDTO)
				.collect(Collectors.toList());
	}
	public static PagoServicio pagoServicioDTOToPagoServicio(PagoServicioDTO pagoServicioDTO) {
		return PagoServicio.builder()
				.id(pagoServicioDTO.getId())
				.montoPago(pagoServicioDTO.getMontoPago())
				.fechaPago(pagoServicioDTO.getFechaPago())
				.servicio(pagoServicioDTO.getServicio() != null ?
						ServicioDTO.servicioDTOToServicio(pagoServicioDTO.getServicio()) :
						Servicio.builder().build())
				.estadoPagoServicio(pagoServicioDTO.getEstadoPagoServicio() != null ?
						EstadoPagoServicioDTO.estadoPagoServicioDTOToEstadoPagoServicio(pagoServicioDTO.getEstadoPagoServicio()):
						EstadoPagoServicio.builder().build())
				.build();
	}
	
}
