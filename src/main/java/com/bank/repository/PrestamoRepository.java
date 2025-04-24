package com.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.Prestamo;
import com.bank.model.TipoPrestamo;


@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer>{
	List<Prestamo> findPrestamoByEstadoTrue();
	Optional<Prestamo> findPrestamoByIdAndEstadoTrue(int id); 
	Optional<Prestamo> findPrestamoByTipoPrestamoAndEstadoTrue(TipoPrestamo tipoPrestamo);
}
