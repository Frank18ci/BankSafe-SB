package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.TipoMonedaTarjeta;

@Repository
public interface TipoMonedaTarjetaRepository extends JpaRepository<TipoMonedaTarjeta, Integer> {

}
