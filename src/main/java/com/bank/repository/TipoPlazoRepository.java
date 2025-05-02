package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.TipoPlazo;

import java.util.List;
import java.util.Optional;



@Repository
public interface TipoPlazoRepository extends JpaRepository<TipoPlazo, Integer> {
	List<TipoPlazo> findTipoPlazoByEstadoTrue();
	Optional<TipoPlazo> findTipoPlazoByIdAndEstadoTrue(int id);
	Optional<TipoPlazo> findTipoPlazoByTipoAndEstadoTrue(String tipo);
}
