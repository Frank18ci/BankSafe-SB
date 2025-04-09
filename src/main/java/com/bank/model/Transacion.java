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
@Table(name = "transacion")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column
	private BigDecimal monto;
	@Column
	private String descripcion;
	@Column
	private Date fecha;
	//
	private TipoTransacion tipoTransaccion;
	private Tarjeta tarjeta;
	private User User;
}
