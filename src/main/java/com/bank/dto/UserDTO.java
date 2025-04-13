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
				.roleUser(RoleUserDTO.builder()
						.id(user.getRoleUser().getId())
						.tipo(user.getRoleUser().getTipo())
						.build())
				.tipoDocumentoUser(TipoDocumentoUserDTO.builder()
						.id(user.getTipoDocumentoUser().getId())
						.tipo(user.getTipoDocumentoUser().getTipo())
						.build())
				.build();
	}
	
	public static List<UserDTO> listUserToUserDTO(List<User> users) {
		return users.stream()
				.map(UserDTO::userToUserDTO).collect(Collectors.toList());
	}
	
	public static User userDTOToUser(UserDTO userDTO)
	{
		return User
				.builder()
				.id(userDTO.getId())
				.numeroDocumento(userDTO.getNumeroDocumento())
				.nombres(userDTO.getApellidos())
				.apellidos(userDTO.getApellidos())
				.fechaNacimiento(userDTO.getFechaNacimiento())
				.imagePath(userDTO.getImagePath())
				.roleUser(RoleUser.builder()
						.id(userDTO.getRoleUser().getId())
						.tipo(userDTO.getRoleUser().getTipo())
						.build())
				.tipoDocumentoUser(TipoDocumentoUser.builder()
						.id(userDTO.getTipoDocumentoUser().getId())
						.tipo(userDTO.getTipoDocumentoUser().getTipo())
						.build())
				.build();
	}
}
