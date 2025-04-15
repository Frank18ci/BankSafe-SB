package com.bank.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.bank.model.RoleUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleUserDTO {
	private int id;
	private String tipo;
	
	public static RoleUserDTO rolUserToRolUserDTO(RoleUser roleUser){
		return RoleUserDTO
				.builder()
				.id(roleUser.getId())
				.tipo(roleUser.getTipo())
				.build();
	}
	public static List<RoleUserDTO> listRoleUserToListUserDTO(List<RoleUser> roleUsers){
		return roleUsers.stream()
				.map(RoleUserDTO::rolUserToRolUserDTO)
				.collect(Collectors.toList());
	}
	public static RoleUser rolUserDTOToRolUser(RoleUserDTO roleUserDTO){
		return RoleUser
				.builder()
				.id(roleUserDTO.getId())
				.tipo(roleUserDTO.getTipo())
				.build();
	}
}
