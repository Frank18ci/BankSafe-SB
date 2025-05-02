package com.bank.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.bank.model.Tarjeta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TarjetaIDTO {
	private int id;
	private String cvv;
	private Date fechaVencimiento;
	private String numeroTarjeta;
	private String claveInternet;
	private Double monto;
	private TipoTarjetaDTO tipoTarjeta;
	private TipoMonedaTarjetaDTO tipoMonedaTarjeta;
	
	public static TarjetaIDTO tarjetaToTarjetaIDTO(Tarjeta tarjeta) {
		return TarjetaIDTO.builder()
				.id(tarjeta.getId())
				.cvv(tarjeta.getCvv())
				.fechaVencimiento(tarjeta.getFechaVencimiento())
				.numeroTarjeta(tarjeta.getNumeroTarjeta())
				.claveInternet(tarjeta.getClaveInternet())
				.monto(tarjeta.getMonto())
				.tipoTarjeta(TipoTarjetaDTO.tipoTarjetaToTipoTarjetaDTO(tarjeta.getTipoTarjeta()))
				.tipoMonedaTarjeta(TipoMonedaTarjetaDTO.tipoMonedaTarjetaToTipoMonedaTarjetaDTO(tarjeta.getTipoMonedaTarjeta()))
				.build();
	}
	public static List<TarjetaIDTO> listTarjetaToListTarjetaIDTO(List<Tarjeta> tarjetas){
		return tarjetas.stream()
				.map(TarjetaIDTO::tarjetaToTarjetaIDTO)
				.collect(Collectors.toList());
	}
}
