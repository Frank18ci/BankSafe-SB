package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.model.TipoPrestamo;

import java.util.List;
import java.util.Optional;




public interface TipoPrestamoRepository extends JpaRepository<TipoPrestamo, Integer> {
	List<TipoPrestamo> findTipoPrestamoByEstadoTrue();
	Optional<TipoPrestamo> findTipoPrestamoByIdAndEstadoTrue(int id);
	Optional<TipoPrestamo> findTipoPrestamoByTipoAndEstadoTrue(String tipo);
}
