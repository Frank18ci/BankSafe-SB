package com.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


import com.bank.model.TipoMonedaTarjeta;


@EnableJpaRepositories
@Repository
public interface TipoMonedaTarjetaRepository extends JpaRepository<TipoMonedaTarjeta, Integer> {
	Optional<TipoMonedaTarjeta> findTipoMonedaTarjetaById(int id);
	Optional<TipoMonedaTarjeta> findTipoMonedaTarjetaByNombre(String nombre);
	Optional<TipoMonedaTarjeta> findTipoMonedaTarjetaBySimbolo(String simbolo);
}
