package com.bank.model;

import java.util.Date;

import java.util.List;

import jakarta.persistence.CascadeType;
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
	
	private String numeroDocumento;
	
	private String nombres;
	
	private String apellidos;
	
	private String imagePath;
	
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	private boolean estado;
	
	@ManyToOne(targetEntity = RoleUser.class)
	private RoleUser roleUser;
	
	@ManyToOne(fetch = FetchType.LAZY,targetEntity = TipoDocumentoUser.class)
	private TipoDocumentoUser tipoDocumentoUser;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<Tarjeta> tarjetas;
	
	@OneToMany(mappedBy = "user")
	private List<Prestamo> prestamos;
	
}
