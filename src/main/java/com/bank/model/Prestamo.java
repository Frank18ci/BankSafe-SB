package com.bank.model;


import java.math.BigDecimal;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prestamo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Prestamo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private BigDecimal montoPrestamo;

	private BigDecimal interes;
    private Integer plazoMeses;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Temporal(TemporalType.TIMESTAMP)
	private Date FechaRegistro;
    @Temporal(TemporalType.TIMESTAMP)
	private Date FechaFinalizado;
	
	@Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean estado;
	
	
	@ManyToOne
	private EstadoPrestamo estadoPrestamo;
	@ManyToOne
	private User user;
	@ManyToOne
	private TipoPrestamo tipoPrestamo;

}
