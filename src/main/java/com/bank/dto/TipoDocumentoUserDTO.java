package com.bank.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.bank.model.TipoDocumentoUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoDocumentoUserDTO {
	private int id;
	private String tipo;
	
	public static TipoDocumentoUserDTO tipoDocumentoUserToTipoDocumentoUserDTO(TipoDocumentoUser tipoDocumentoUser) {
		return TipoDocumentoUserDTO
				.builder()
				.id(tipoDocumentoUser.getId())
				.tipo(tipoDocumentoUser.getTipo())
				.build();
	}
	public static List<TipoDocumentoUserDTO> listTipoDocumentoUserToListTipoDocumentoUserDTO(List<TipoDocumentoUser> tipoDocumentoUsers){
		return tipoDocumentoUsers.stream()
				.map(TipoDocumentoUserDTO::tipoDocumentoUserToTipoDocumentoUserDTO)
				.collect(Collectors.toList());
	}
	public static TipoDocumentoUser tipoDocumentoUserDTOToTipoDocumentoUser(TipoDocumentoUserDTO tipoDocumentoUserDTO) {
		return TipoDocumentoUser
				.builder()
				.id(tipoDocumentoUserDTO.getId())
				.tipo(tipoDocumentoUserDTO.getTipo())
				.build();
	}
}
