package com.bank.model;

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
@Table(name = "tipo_moneda_tarjeta")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoMonedaTarjeta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	
	private String simboloSecundario;
	
	private String simbolo;

	@Column(unique = true)
	private String tipo;
	
	@Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean estado;
	
	@OneToMany(mappedBy = "tipoMonedaTarjeta")
	private List<Tarjeta> tarjetas;
}
