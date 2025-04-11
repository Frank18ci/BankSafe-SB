package com.bank.dto;

import java.util.Date;
import java.util.List;

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
	private String documento;
	private String numeroDocumento;
	private String nombres;
	private String apellidos;
	private Date fechaNacimiento;
	private String imagePath;
	private RoleUserDTO roleUser;
	private TipoDocumentoUserDTO tipoDocumentoUser;
	private List<TarjetaDTO> tarjetas;
	
}
