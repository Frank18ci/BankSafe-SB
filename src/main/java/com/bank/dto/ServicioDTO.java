package com.bank.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.bank.model.Empresa;
import com.bank.model.Servicio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServicioDTO {
	private int id;	
	private String codigo;
	private String descripcion;
	private EmpresaDTO empresa;
	public static ServicioDTO servicioToServicioDTO(Servicio servicio) {
		return ServicioDTO.builder()
				.id(servicio.getId())
				.codigo(servicio.getCodigo())
				.descripcion(servicio.getDescripcion())
				.empresa(servicio.getEmpresa() != null ?
						EmpresaDTO.empresaToEmpresaDTO(servicio.getEmpresa()):
							EmpresaDTO.builder().build())
				.build();
	}
	public static List<ServicioDTO> listaServicioToListaServicioDTO(List<Servicio> servicios){
		return servicios.stream().map(ServicioDTO::servicioToServicioDTO)
				.collect(Collectors.toList());
	}
	public static Servicio servicioDTOToServicio(ServicioDTO servicioDTO) {
		return Servicio.builder()
				.id(servicioDTO.getId())
				.codigo(servicioDTO.getCodigo())
				.descripcion(servicioDTO.getDescripcion())
				.empresa(servicioDTO.getEmpresa() != null ?
						EmpresaDTO.empresaDTOToEmpresa(servicioDTO.getEmpresa()):
							Empresa.builder().build())
				.build();
	}
}
