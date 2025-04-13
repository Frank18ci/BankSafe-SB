package com.bank.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
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
	@Column
	private BigDecimal monto;
	@Column
	private Date fecha;
	@Column
	private String descripcion;
	
	private boolean estado;
	
	@OneToMany(mappedBy = "tipoTransaccion")
	private List<Transacion> transaciones;
}
