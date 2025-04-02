package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.RoleUser;

@Repository
public interface RoleUserRepository extends JpaRepository<RoleUser, Integer> {

}
