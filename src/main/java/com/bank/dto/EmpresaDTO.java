package com.bank.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.bank.model.Empresa;
import com.bank.model.TipoEmpresa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaDTO {
	private int id;	
	private String nombre;
	private TipoEmpresaDTO tipoEmpresa;
	public static EmpresaDTO empresaToEmpresaDTO(Empresa empresa) {
		return EmpresaDTO.builder()
				.id(empresa.getId())
				.nombre(empresa.getNombre())
				.tipoEmpresa(empresa.getTipoEmpresa() != null ?
						TipoEmpresaDTO.tipoEmpresaToTipoEmpresaDTO(empresa.getTipoEmpresa()) :
						TipoEmpresaDTO.builder().build())
				.build();
	}
	public static List<EmpresaDTO> listaEmpresaToListaEmpresaDTO(List<Empresa> empresas){
		return empresas.stream().map(EmpresaDTO::empresaToEmpresaDTO)
				.collect(Collectors.toList());
	}
	public static Empresa empresaDTOToEmpresa(EmpresaDTO empresaDTO) {
		return Empresa.builder()
				.id(empresaDTO.getId())
				.nombre(empresaDTO.getNombre())
				.tipoEmpresa(empresaDTO.getTipoEmpresa() != null ?
						TipoEmpresaDTO.tipoEmpresaDTOToTipoEmpresa(empresaDTO.getTipoEmpresa()) :
						TipoEmpresa.builder().build())
				.build();
	}
}
