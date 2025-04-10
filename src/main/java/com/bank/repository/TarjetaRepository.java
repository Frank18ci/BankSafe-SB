package com.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.bank.model.Tarjeta;
import com.bank.model.TipoMonedaTarjeta;
import com.bank.model.TipoTarjeta;
import com.bank.model.User;




@EnableJpaRepositories
@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Integer> {
	Optional<Tarjeta> findTarjetaByNumeroTarjeta(String numeroTarjeta);
	Optional<Tarjeta> findTarjetaByTipoMonedaTarjeta(TipoMonedaTarjeta tipoMonedaTarjeta);
	Optional<Tarjeta> findTarjetaByTipoTarjeta(TipoTarjeta tipoTarjeta);
	Optional<Tarjeta> findTarjetaByUser(User user);
	
}
