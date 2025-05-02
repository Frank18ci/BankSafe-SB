package com.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.Tarjeta;
import com.bank.model.TipoMonedaTarjeta;
import com.bank.model.TipoTarjeta;
import com.bank.model.User;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Integer> {
	List<Tarjeta> findTarjetaByEstadoTrue();
	Page<Tarjeta> findTarjetaByNumeroTarjetaIgnoreCaseContainingAndTipoMonedaTarjeta_tipoAndEstadoTrue(String numeroTarjeta, String tipoMonedaTarjeta, Pageable pageable);
	Optional<Tarjeta> findTarjetaByIdAndEstadoTrue(int id);
	Optional<Tarjeta> findTarjetaByNumeroTarjetaAndEstadoTrue(String numeroTarjeta);
	Optional<Tarjeta> findTarjetaByTipoMonedaTarjetaAndEstadoTrue(TipoMonedaTarjeta tipoMonedaTarjeta);
	Optional<Tarjeta> findTarjetaByTipoTarjetaAndEstadoTrue(TipoTarjeta tipoTarjeta);
	Optional<Tarjeta> findTarjetaByUserAndEstadoTrue(User user);
	
}
