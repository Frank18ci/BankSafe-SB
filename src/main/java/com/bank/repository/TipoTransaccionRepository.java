package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.model.TipoTransacion;
import java.util.Optional;
import java.util.Date;



public interface TipoTransaccionRepository extends JpaRepository<TipoTransacion, Integer> {
	Optional<TipoTransacion> findTipoTransacionById(int id);
	Optional<TipoTransacion> findTipoTransacionByFecha(Date fecha);
}
