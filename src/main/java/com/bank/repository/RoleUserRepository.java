package com.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.RoleUser;
import java.util.List;



@Repository
public interface RoleUserRepository extends JpaRepository<RoleUser, Integer> {
	List<RoleUser> findRolUserByEstadoTrue();
	Optional<RoleUser> findRolUserByIdAndEstadoTrue(int id);
	Optional<RoleUser> findRoleUserByTipoAndEstadoTrue(String tipo);
}
