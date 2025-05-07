package com.bank.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.bank.model.TipoEmpresa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoEmpresaDTO {
	private int id;	
	private String descripcion;
	
	public static TipoEmpresaDTO tipoEmpresaToTipoEmpresaDTO(TipoEmpresa tipoEmpresa) {
		return TipoEmpresaDTO.builder()
				.id(tipoEmpresa.getId())
				.descripcion(tipoEmpresa.getDescripcion())
				.build();
	}
	public static List<TipoEmpresaDTO> listTipoEmpresaToListTipoEmpresaDTO(List<TipoEmpresa> tipoEmpresas) {
		return tipoEmpresas.stream().map(TipoEmpresaDTO::tipoEmpresaToTipoEmpresaDTO)
				.collect(Collectors.toList());
	}
	public static TipoEmpresa tipoEmpresaDTOToTipoEmpresa(TipoEmpresaDTO tipoEmpresaDTO) {
		return TipoEmpresa.builder()
				.id(tipoEmpresaDTO.getId())
				.descripcion(tipoEmpresaDTO.getDescripcion())
				.build();
	}
}
