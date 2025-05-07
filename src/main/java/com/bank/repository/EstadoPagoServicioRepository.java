package com.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.EstadoPagoServicio;

@Repository
public interface EstadoPagoServicioRepository extends JpaRepository<EstadoPagoServicio, Integer>{
	List<EstadoPagoServicio> findByEstadoTrue();
	Optional<EstadoPagoServicio> findByIdAndEstadoTrue(int id);
}
