package com.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer>{
	List<Empresa> findByEstadoTrue();
	List<Empresa> findByNombreContainingOrTipoEmpresa_descripcionContainingAndEstadoTrue(String nombre, String tipoEmpresaDescripcion);
	Optional<Empresa> findByIdAndEstadoTrue(int id);
}
