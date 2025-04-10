package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.TipoTarjeta;
import java.util.Optional;


@Repository
public interface TipoTarjetaRepository extends JpaRepository<TipoTarjeta, Integer> {
	Optional<TipoTarjeta> findTipoTarjetaById(int id);
	Optional<TipoTarjeta> findTipoTarjetaByTipo(String tipo);;
}
