package com.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer>{
	List<Servicio> findByEstadoTrue();
	Optional<Servicio> findByIdAndEstadoTrue(int id);
}
