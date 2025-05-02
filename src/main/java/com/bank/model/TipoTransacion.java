package com.bank.model;

import java.util.List;

import jakarta.persistence.Entity;
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
@Table(name = "tipo_transacion")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoTransacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String tipo;
	
	private boolean estado;
	
	@OneToMany(mappedBy = "tipoTransacion")
	private List<Transacion> transaciones;
}
