package com.bank.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "servicio")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Servicio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	@Column(unique = true)
	private String codigo;
	private String descripcion;
	@ManyToOne
	private Empresa empresa;
	@OneToMany(mappedBy = "servicio")
	private List<PagoServicio> pagoServicios;
	
	@Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean estado;
}
