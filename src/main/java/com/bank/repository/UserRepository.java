package com.bank.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findUserByEstadoTrue();
	Optional<User> findUserByIdAndEstadoTrue(int id);
}
