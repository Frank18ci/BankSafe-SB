package com.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.RoleUser;
import com.bank.model.TipoDocumentoUser;




@Repository
public interface TipoDocumentoUserRepository extends JpaRepository<TipoDocumentoUser, Integer> {
	Optional<RoleUser> findTipoDocumentoUserById(int id);
	Optional<RoleUser> findTipoDocumentoUserByTipo(String tipo);
}
