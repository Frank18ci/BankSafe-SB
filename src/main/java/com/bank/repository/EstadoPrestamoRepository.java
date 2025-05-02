package com.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.EstadoPrestamo;

@Repository
public interface EstadoPrestamoRepository extends JpaRepository<EstadoPrestamo, Integer>{
	List<EstadoPrestamo> findByEstadoTrue();
	
} 
