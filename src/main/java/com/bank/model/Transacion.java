package com.bank.model;

import java.math.BigDecimal;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transacion")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private BigDecimal monto;
	private String descripcion;
	private Date fecha;
	
	private boolean estado;
	
	@ManyToOne
	private TipoTransacion tipoTransacion;
	@ManyToOne
	private Tarjeta tarjetaOrigen;
	@ManyToOne
	private Tarjeta tarjetaDestino;
	
	
	@ManyToOne
	private User user;
}
