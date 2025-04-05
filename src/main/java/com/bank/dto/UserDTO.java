package com.bank.dto;

import java.util.Date;


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
	private String nombres;
	private String apellidos;
	private int edad;
	private String username;
	private String password;
	private Date fechaNacimiento;
}
