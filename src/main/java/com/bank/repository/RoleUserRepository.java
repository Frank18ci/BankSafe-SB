package com.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.Prestamo;
import com.bank.model.RoleUser;
import java.util.List;


@Repository
public interface RoleUserRepository extends JpaRepository<RoleUser, Integer> {
	Optional<RoleUser> findRoleUserByTipo(String tipo);
	Optional<RoleUser> findRoleUserById(int id);
}
