package com.bank.model;


import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tarjeta")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tarjeta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;	
	@Column(length = 3)
	private String cvv;
	
	@Temporal(TemporalType.DATE)
	private Date fechaVencimiento;
	
	//String security toma la tarjeta como username
	@Column(unique = true, length = 16)
	private String numeroTarjeta;
	//String security toma la clave internet como password
	private String claveInternet;
	
	private Double monto;
	
	@Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean estado;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne
	private TipoTarjeta tipoTarjeta;
	@ManyToOne
	private TipoMonedaTarjeta tipoMonedaTarjeta;
	
	@OneToMany(mappedBy = "tarjetaOrigen")
	private List<Transacion> transacionesOrigen;
	@OneToMany(mappedBy = "tarjetaDestino")
	private List<Transacion> transacionesDestino;
}
