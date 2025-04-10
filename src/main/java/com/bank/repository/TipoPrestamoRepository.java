package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.model.TipoPrestamo;

import java.util.Optional;




public interface TipoPrestamoRepository extends JpaRepository<TipoPrestamo, Integer> {
	Optional<TipoPrestamo> findTipoPrestamoById(int id);
	Optional<TipoPrestamo> findTipoPrestamoByTipo(String tipo);
}
