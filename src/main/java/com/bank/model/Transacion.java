package com.bank.model;

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
	private int id;
}
