package com.bank.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(fetch = FetchType.LAZY,targetEntity = TipoDocumentoUser.class)
	private TipoDocumentoUser tipoDocumento;
	private String documento;
	private String nombres;
	private String apellidos;
	private int edad;
	//agregar el username puede ser el documento si lo consideramos como numero pero faltaria validar el tipo y el numero en ese caso
	
	private String password;
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	@ManyToOne(fetch = FetchType.LAZY,targetEntity = TypeUser.class)
	private TypeUser typeUser;
	
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, targetEntity = Cuenta.class)
	private List<Cuenta> cuentas;
}
