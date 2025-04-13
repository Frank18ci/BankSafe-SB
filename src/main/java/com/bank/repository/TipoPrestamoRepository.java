package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.model.TipoPrestamo;

import java.util.List;
import java.util.Optional;




public interface TipoPrestamoRepository extends JpaRepository<TipoPrestamo, Integer> {
	List<TipoPrestamo> findTipoPrestamoByEstado(boolean estado);
	Optional<TipoPrestamo> findTipoPrestamoByIdAndEstado(int id, boolean estado);
	Optional<TipoPrestamo> findTipoPrestamoByTipoAndEstado(String tipo, boolean estado);
}
