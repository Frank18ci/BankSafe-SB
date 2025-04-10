package com.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.model.Prestamo;
import com.bank.model.TipoPrestamo;



public interface PrestamoRepository extends JpaRepository<Prestamo, Integer>{

	Optional<Prestamo> findPrestamoByEstado(String estado); 
	Optional<Prestamo> findPrestamoByTipoPrestamo(TipoPrestamo tipoPrestamo);
}
