package com.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.bank.model.TipoMonedaTarjeta;


@Repository
public interface TipoMonedaTarjetaRepository extends JpaRepository<TipoMonedaTarjeta, Integer> {
	List<TipoMonedaTarjeta> findTipoMonedaTarjetaByEstadoTrue();
	Optional<TipoMonedaTarjeta> findTipoMonedaTarjetaByIdAndEstadoTrue(int id);
	Optional<TipoMonedaTarjeta> findTipoMonedaTarjetaBySimboloAndEstadoTrue(String simbolo);
}
