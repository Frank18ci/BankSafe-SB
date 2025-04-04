package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.TipoDocumentoUser;


@Repository
public interface TipoDocumentoUserRepository extends JpaRepository<TipoDocumentoUser, Integer> {

}
