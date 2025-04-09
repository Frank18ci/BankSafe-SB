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
@Table(name = "prestamo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Prestamo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private BigDecimal montoPrestamo;
    private BigDecimal interes;
    private Integer plazoMeses;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
	private Date FechaRegistro;
	private Date FechaFinalizado;
	
	@ManyToOne
	private User user;
	@ManyToOne
	private TipoPrestamo tipoPrestamo;

}
