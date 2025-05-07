package com.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.PagoServicio;

@Repository
public interface PagoServicioRepository extends JpaRepository<PagoServicio, Integer>{
	List<PagoServicio> findByEstadoTrue();
	Optional<PagoServicio> findByIdAndEstadoTrue(int id);
	List<PagoServicio> findByServicio_CodigoAndEstadoPagoServicio_estadoServicioAndEstadoTrue(String codigoServicio, String estadoPagoServicio);
}
