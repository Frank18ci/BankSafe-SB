package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.model.Transacion;

import java.util.Optional;
import java.util.Date;



public interface TransacionRepository extends JpaRepository<Transacion, Integer> {
	Optional<Transacion> findTransacionById(int id);
	Optional<Transacion> findTransacionByFecha(Date fecha);
}
