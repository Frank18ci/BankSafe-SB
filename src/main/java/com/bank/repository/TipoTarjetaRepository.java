package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.TipoTarjeta;

@Repository
public interface TipoTarjetaRepository extends JpaRepository<TipoTarjeta, Integer> {

}
