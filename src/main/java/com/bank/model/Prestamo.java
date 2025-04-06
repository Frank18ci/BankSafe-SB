package com.bank.model;


import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	@Column(name = "id")
	private int id;

	private BigDecimal montoPrestamo;
	@Column
    private BigDecimal interes;
	@Column
    private Integer plazoMeses;
	@Column
    private Date fechaInicio;
	@Column
    private Date fechaFin;
	@Column
    private String estado;
	//

	private int intTipoPrestamo;
	private Date FechaRegistro;
	private Date FechaFinalizado;
	//
	private User user;
	private TipoPrestamo tipoPrestamo;

}
