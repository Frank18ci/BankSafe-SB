package com.bank.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.bank.model.TipoTransacion;
import com.bank.model.Transacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransacionDTO {
	private int id;
	private BigDecimal monto;
	private String descripcion;
	private Date fecha;
	//
	private TipoTransacionDTO tipoTransacion;
	private TarjetaDTO tarjeta;
	private UserDTO user;
	
	public static TransacionDTO transacionToTransacionDTO(Transacion transacion) {
		return TransacionDTO
				.builder()
				.id(transacion.getId())
				.monto(transacion.getMonto())
				.descripcion(transacion.getDescripcion())
				.fecha(transacion.getFecha())
				.tipoTransacion(TipoTransacionDTO.tipoTransacionToTipoTransacionDTO(transacion.getTipoTransacion()))
				.tarjeta(TarjetaDTO.tarjetaToTarjetaDTO(transacion.getTarjeta()))
				.user(UserDTO.userToUserDTO(transacion.getUser()))
				.build();
	}
	public static List<TransacionDTO> listTransacionToListTransacionDTO(List<Transacion> transaciones){
		return transaciones.stream()
				.map(TransacionDTO::transacionToTransacionDTO)
				.collect(Collectors.toList());
	}
	public static Transacion transacionDTOToTransacion(TransacionDTO transacionDTO) {
		return Transacion
				.builder()
				.id(transacionDTO.getId())
				.monto(transacionDTO.getMonto())
				.descripcion(transacionDTO.getDescripcion())
				.fecha(transacionDTO.getFecha())
				.tipoTransacion(TipoTransacionDTO.tipoTransacionDTOToTipoTransacion(transacionDTO.getTipoTransacion()))
				.tarjeta(TarjetaDTO.tarjetaDTOTotarjeta(transacionDTO.getTarjeta()))
				.user(UserDTO.userDTOToUser(transacionDTO.getUser()))
				.build();
	}
}
