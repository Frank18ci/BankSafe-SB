package com.bank.model;

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
@Table(name = "pago_servicio")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagoServicio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	private double montoPago;
	private Date fechaPago;
	@ManyToOne
	private Servicio servicio;
	@ManyToOne
	private EstadoPagoServicio estadoPagoServicio;
}
