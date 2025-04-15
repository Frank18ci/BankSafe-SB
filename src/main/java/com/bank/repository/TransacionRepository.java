package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.model.Transacion;

import java.util.Optional;
import java.util.Date;
import java.util.List;




public interface TransacionRepository extends JpaRepository<Transacion, Integer> {
	List<Transacion> findTransacionByEstado(boolean estado);
	Optional<Transacion> findTransacByIdAndEstado(int id, boolean estado);
	Optional<Transacion> findTransacionById(int id);
	Optional<Transacion> findTransacionByFecha(Date fecha);
}
