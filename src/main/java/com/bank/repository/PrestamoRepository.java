package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.model.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer>{

}
