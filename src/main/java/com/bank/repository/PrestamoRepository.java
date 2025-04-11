package com.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.bank.model.Prestamo;
import com.bank.model.TipoPrestamo;


@EnableJpaRepositories
@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer>{

	Optional<Prestamo> findPrestamoByEstado(String estado); 
	Optional<Prestamo> findPrestamoByTipoPrestamo(TipoPrestamo tipoPrestamo);
}
