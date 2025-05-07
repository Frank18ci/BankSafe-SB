package com.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.TipoEmpresa;

@Repository
public interface TipoEmpresaRepository extends JpaRepository<TipoEmpresa, Integer>{
	List<TipoEmpresa> findByEstadoTrue();
	Optional<TipoEmpresa> findByIdAndEstadoTrue(int id);
}
