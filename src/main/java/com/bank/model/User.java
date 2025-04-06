package com.bank.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
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
	@Column (name="id")
	private int id;

	@ManyToOne(fetch = FetchType.LAZY,targetEntity = TipoDocumentoUser.class)
	@Column
	private String documento;
	@Column

	private String numeroDocumento;

	private String nombres;
	@Column
	private String apellidos;

	@Column
	private int edad;
	// validar spring security 
	@Column
	private String username;
	@Column
	private String password;
	
	//
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	
	private String imagePath;
	
	@ManyToOne(fetch = FetchType.LAZY,targetEntity = RoleUser.class)
	private RoleUser roleUser;
	@ManyToOne(fetch = FetchType.LAZY,targetEntity = TipoDocumentoUser.class)
	private TipoDocumentoUser tipoDocumento;
	@OneToMany(mappedBy = "user")
	private List<Tarjeta> tarjetas;
	//
	
}
