package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.Transacion;

import java.util.Optional;
import java.util.Date;
import java.util.List;



@Repository
public interface TransacionRepository extends JpaRepository<Transacion, Integer> {
	List<Transacion> findTransacionByEstado(boolean estado);
	Optional<Transacion> findTransacByIdAndEstado(int id, boolean estado);
	Optional<Transacion> findTransacionByFechaAndEstado(Date fecham, boolean estado);
}
