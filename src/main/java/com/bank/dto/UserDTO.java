package com.bank.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.bank.model.RoleUser;
import com.bank.model.TipoDocumentoUser;
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
	private String correo;
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
				.correo(user.getCorreo())
				.roleUser(RoleUserDTO.rolUserToRolUserDTO(user.getRoleUser() != null ? user.getRoleUser() : RoleUser.builder().build()))
				.tipoDocumentoUser(TipoDocumentoUserDTO.tipoDocumentoUserToTipoDocumentoUserDTO(user.getTipoDocumentoUser() != null ? user.getTipoDocumentoUser() : TipoDocumentoUser.builder().build()))
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
				.correo(userDTO.getCorreo())
				.roleUser(RoleUserDTO.rolUserDTOToRolUser(userDTO.getRoleUser() != null ? userDTO.getRoleUser() : RoleUserDTO.builder().build()))
				.tipoDocumentoUser(TipoDocumentoUserDTO.tipoDocumentoUserDTOToTipoDocumentoUser(userDTO.getTipoDocumentoUser() != null ? userDTO.getTipoDocumentoUser() : TipoDocumentoUserDTO.builder().build()))
				.build();
	}
}
