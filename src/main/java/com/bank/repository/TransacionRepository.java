package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.Transacion;

import java.util.Optional;
import java.util.Date;
import java.util.List;



@Repository
public interface TransacionRepository extends JpaRepository<Transacion, Integer> {
	List<Transacion> findTransacionByEstadoTrue();
	Optional<Transacion> findTransacByIdAndEstadoTrue(int id);
	Optional<Transacion> findTransacionByFechaAndEstadoTrue(Date fecham);
	List<Transacion> findTransacionByFechaBetweenAndTarjetaOrigen_numeroTarjetaAndEstadoTrue(Date fechaI, Date fechaF, String numeroTarjeta);
}
