package com.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


import com.bank.model.TipoMonedaTarjeta;


@EnableJpaRepositories
@Repository
public interface TipoMonedaTarjetaRepository extends JpaRepository<TipoMonedaTarjeta, Integer> {
	List<TipoMonedaTarjeta> findTipoMonedaTarjetaByEstado(boolean estado);
	Optional<TipoMonedaTarjeta> findTipoMonedaTarjetaByIdAndEstado(int id, boolean estado);
	Optional<TipoMonedaTarjeta> findTipoMonedaTarjetaByNombreAndEstado(String nombre, boolean estado);
	Optional<TipoMonedaTarjeta> findTipoMonedaTarjetaBySimboloAndEstado(String simbolo, boolean estado);
}
