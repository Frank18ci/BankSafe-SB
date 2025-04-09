package com.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.bank.model.Tarjeta;

@EnableJpaRepositories
@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Integer> {
	Optional<Tarjeta> findTarjetaByNumeroTarjeta(String numeroTarjeta);
	
}
