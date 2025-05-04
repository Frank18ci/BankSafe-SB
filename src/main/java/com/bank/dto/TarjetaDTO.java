package com.bank.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.bank.model.Tarjeta;
import com.bank.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TarjetaDTO {
	private int id;
	private String cvv;
	private Date fechaVencimiento;
	private String numeroTarjeta;
	private String claveInternet;
	private Double monto;
	
	private UserDTO user;
	private TipoTarjetaDTO tipoTarjeta;
	private TipoMonedaTarjetaDTO tipoMonedaTarjeta;
	
	public static TarjetaDTO tarjetaToTarjetaDTO(Tarjeta tarjeta) {
		return TarjetaDTO
				.builder()
				.id(tarjeta.getId())
				.cvv(tarjeta.getCvv())
				.fechaVencimiento(tarjeta.getFechaVencimiento())
				.numeroTarjeta(tarjeta.getNumeroTarjeta())
				.claveInternet(tarjeta.getClaveInternet())
				.monto(tarjeta.getMonto())
				.user(UserDTO.userToUserDTO(tarjeta.getUser() != null ? tarjeta.getUser() : User.builder().build()))
				.tipoTarjeta(TipoTarjetaDTO.builder()
						.id(tarjeta.getTipoTarjeta().getId())
						.tipo(tarjeta.getTipoTarjeta().getTipo())
						.build())
				.tipoMonedaTarjeta(TipoMonedaTarjetaDTO.tipoMonedaTarjetaToTipoMonedaTarjetaDTO(tarjeta.getTipoMonedaTarjeta()))
				.build();
	}
	public static List<TarjetaDTO> listTarjetaToTarjetaDTO(List<Tarjeta> tarjetas){
		return tarjetas.stream()
				.map(TarjetaDTO::tarjetaToTarjetaDTO)
				.collect(Collectors.toList());
	}
	public static Tarjeta tarjetaDTOTotarjeta(TarjetaDTO tarjetaDTO) {
		return Tarjeta
				.builder()
				.id(tarjetaDTO.getId())
				.cvv(tarjetaDTO.getCvv())
				.fechaVencimiento(tarjetaDTO.getFechaVencimiento())
				.numeroTarjeta(tarjetaDTO.getNumeroTarjeta())
				.claveInternet(tarjetaDTO.getClaveInternet())
				.monto(tarjetaDTO.getMonto())
				.user(UserDTO.userDTOToUser(tarjetaDTO.getUser()!= null ? tarjetaDTO.getUser() : UserDTO.builder().build()))
				.tipoTarjeta(TipoTarjetaDTO.tipoTarjetaDTOToTipoTarjeta(tarjetaDTO.getTipoTarjeta()))
				.tipoMonedaTarjeta(TipoMonedaTarjetaDTO.tipoMonedaTarjetaDTOToTipoMonedaTarjeta(tarjetaDTO.getTipoMonedaTarjeta()))
				.build();
	}
}
