package com.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.Prestamo;
import com.bank.model.TipoPrestamo;


@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer>{
	List<Prestamo> findPrestamoByEstado(boolean estado);
	Optional<Prestamo> findPrestamoByIdAndEstado(int id, boolean estado);
	Optional<Prestamo> findPrestamoByEstadoPrestamoAndEstado(String estadoPrestamo, boolean estado); 
	Optional<Prestamo> findPrestamoByTipoPrestamoAndEstado(TipoPrestamo tipoPrestamo, boolean estado);
}
