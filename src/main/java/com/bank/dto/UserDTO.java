package com.bank.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.bank.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private int id;
	private String numeroDocumento;
	private String nombres;
	private String apellidos;
	private Date fechaNacimiento;
	private String imagePath;
	
	private RoleUserDTO roleUser;
	private TipoDocumentoUserDTO tipoDocumentoUser;
	
	public static UserDTO userToUserDTO(User user) {
		return UserDTO
				.builder()
				.id(user.getId())
				.numeroDocumento(user.getNumeroDocumento())
				.nombres(user.getNombres())
				.apellidos(user.getApellidos())
				.fechaNacimiento(user.getFechaNacimiento())
				.imagePath(user.getImagePath())
				.roleUser(RoleUserDTO.rolUserToRolUserDTO(user.getRoleUser()))
				.tipoDocumentoUser(TipoDocumentoUserDTO.tipoDocumentoUserToTipoDocumentoUserDTO(user.getTipoDocumentoUser()))
				.build();
	}
	
	public static List<UserDTO> listUserToUserDTO(List<User> users) {
		return users.stream()
				.map(UserDTO::userToUserDTO).collect(Collectors.toList());
	}
	
	public static User userDTOToUser(UserDTO userDTO){
		return User
				.builder()
				.id(userDTO.getId())
				.numeroDocumento(userDTO.getNumeroDocumento())
				.nombres(userDTO.getNombres())
				.apellidos(userDTO.getApellidos())
				.fechaNacimiento(userDTO.getFechaNacimiento())
				.imagePath(userDTO.getImagePath())
				.roleUser(RoleUserDTO.rolUserDTOToRolUser(userDTO.getRoleUser()))
				.tipoDocumentoUser(TipoDocumentoUserDTO.tipoDocumentoUserDTOToTipoDocumentoUser(userDTO.getTipoDocumentoUser()))
				.build();
	}
}
