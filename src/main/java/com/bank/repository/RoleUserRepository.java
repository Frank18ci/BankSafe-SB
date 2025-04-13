package com.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.RoleUser;


@Repository
public interface RoleUserRepository extends JpaRepository<RoleUser, Integer> {
	Optional<RoleUser> findRoleUserByTipo(String tipo);
	Optional<RoleUser> findRoleUserById(int id);
}
