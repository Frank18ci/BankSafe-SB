package com.bank.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.bank.model.RoleUser;
import com.bank.model.Tarjeta;
import com.bank.model.TipoDocumentoUser;
import com.bank.model.TipoMonedaTarjeta;
import com.bank.model.TipoTarjeta;
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
				.user(UserDTO.userToUserDTO(tarjeta.getUser()))
				.tipoTarjeta(TipoTarjetaDTO.builder()
						.id(tarjeta.getTipoTarjeta().getId())
						.tipo(tarjeta.getTipoTarjeta().getTipo())
						.build())
				.tipoMonedaTarjeta(TipoMonedaTarjetaDTO.builder()
						.id(tarjeta.getTipoMonedaTarjeta().getId())
						.nombre(tarjeta.getTipoMonedaTarjeta().getNombre())
						.simbolo(tarjeta.getTipoMonedaTarjeta().getSimbolo())
						.tipo(tarjeta.getTipoMonedaTarjeta().getTipo())
						.build())
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
				.user(User.builder()
						.id(tarjetaDTO.getUser().getId())
						.nombres(tarjetaDTO.getUser().getNumeroDocumento())
						.apellidos(tarjetaDTO.getUser().getApellidos())
						.fechaNacimiento(tarjetaDTO.getUser().getFechaNacimiento())
						.imagePath(tarjetaDTO.getUser().getImagePath())
						.roleUser(RoleUser.builder()
								.id(tarjetaDTO.getUser().getRoleUser().getId())
								.tipo(tarjetaDTO.getUser().getRoleUser().getTipo())
								.build())
						.tipoDocumentoUser(TipoDocumentoUser.builder()
								.id(tarjetaDTO.getUser().getTipoDocumentoUser().getId())
								.tipo(tarjetaDTO.getUser().getTipoDocumentoUser().getTipo())
								.build())
						.build())
				.tipoTarjeta(TipoTarjeta.builder()
						.id(tarjetaDTO.getTipoTarjeta().getId())
						.tipo(tarjetaDTO.getTipoTarjeta().getTipo())
						.build())
				.tipoMonedaTarjeta(TipoMonedaTarjeta.builder()
						.id(tarjetaDTO.getTipoMonedaTarjeta().getId())
						.nombre(tarjetaDTO.getTipoMonedaTarjeta().getNombre())
						.simbolo(tarjetaDTO.getTipoMonedaTarjeta().getSimbolo())
						.tipo(tarjetaDTO.getTipoMonedaTarjeta().getTipo())
						.build())
				.build();
	}
}
