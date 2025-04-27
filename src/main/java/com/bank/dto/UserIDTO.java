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
public class UserIDTO {
	private int id;
	private String numeroDocumento;
	private String nombres;
	private String apellidos;
	private Date fechaNacimiento;
	private String imagePath;
	private String correo;
	private RoleUserDTO roleUser;
	private TipoDocumentoUserDTO tipoDocumentoUser;
	private List<TarjetaIDTO> tarjetas;
	public static UserIDTO userToUserIDTO(User user) {
		return UserIDTO.builder()
				.id(user.getId())
				.numeroDocumento(user.getNumeroDocumento())
				.nombres(user.getNombres())
				.apellidos(user.getApellidos())
				.fechaNacimiento(user.getFechaNacimiento())
				.imagePath(user.getImagePath())
				.correo(user.getCorreo())
				.roleUser(RoleUserDTO.rolUserToRolUserDTO(user.getRoleUser()))
				.tipoDocumentoUser(TipoDocumentoUserDTO.tipoDocumentoUserToTipoDocumentoUserDTO(user.getTipoDocumentoUser()))
				.tarjetas(TarjetaIDTO.listTarjetaToListTarjetaIDTO(user.getTarjetas()))
				.build();
	}
	public static List<UserIDTO> listUserToListUserIDTO(List<User> users) {
		return users.stream()
				.map(UserIDTO::userToUserIDTO)
				.collect(Collectors.toList());
	}
}
