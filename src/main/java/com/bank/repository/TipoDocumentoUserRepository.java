package com.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.RoleUser;
import com.bank.model.TipoDocumentoUser;
import java.util.List;





@Repository
public interface TipoDocumentoUserRepository extends JpaRepository<TipoDocumentoUser, Integer> {
	List<TipoDocumentoUser> findTipoDocumentoByEstadoTrue();
	Optional<TipoDocumentoUser> findTipoDocumentoByIdAndEstadoTrue(int id);
	Optional<RoleUser> findTipoDocumentoUserByTipoAndEstadoTrue(String tipo);
}
