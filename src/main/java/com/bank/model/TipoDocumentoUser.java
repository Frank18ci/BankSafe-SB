package com.bank.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "tipo_documento_user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoDocumentoUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String tipo;
	
	@OneToMany(mappedBy = "tipoDocumento", fetch = FetchType.LAZY, targetEntity = User.class)
	private List<User> users;
}
