package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.TipoTransacion;
import java.util.Optional;
import java.util.List;



@Repository
public interface TipoTransaccionRepository extends JpaRepository<TipoTransacion, Integer> {
	List<TipoTransacion> findTipoTransacionByEstado(boolean estado);
	Optional<TipoTransacion> findTipoTransacionByIdAndEstado(int id, boolean estado);
}
