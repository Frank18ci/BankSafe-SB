package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.TipoTarjeta;

import java.util.List;
import java.util.Optional;


@Repository
public interface TipoTarjetaRepository extends JpaRepository<TipoTarjeta, Integer> {
	List<TipoTarjeta> findTipoTarjetaByEstado(boolean estado);
	Optional<TipoTarjeta> findTipoTarjetaByIdAndEstado(int id, boolean estado);
	Optional<TipoTarjeta> findTipoTarjetaByTipoAndEstado(String tipo, boolean estado);
}
