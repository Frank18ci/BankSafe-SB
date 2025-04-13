package com.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.Tarjeta;
import com.bank.model.TipoMonedaTarjeta;
import com.bank.model.TipoTarjeta;
import com.bank.model.User;




@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Integer> {
	List<Tarjeta> findByEstado(boolean estado);
	Optional<Tarjeta> findTarjetaByIdAndEstado(int id, boolean estado);
	Optional<Tarjeta> findTarjetaByNumeroTarjetaAndEstado(String numeroTarjeta, boolean estado);
	Optional<Tarjeta> findTarjetaByTipoMonedaTarjetaAndEstado(TipoMonedaTarjeta tipoMonedaTarjeta, boolean estado);
	Optional<Tarjeta> findTarjetaByTipoTarjetaAndEstado(TipoTarjeta tipoTarjeta, boolean estado);
	Optional<Tarjeta> findTarjetaByUserAndEstado(User user, boolean estado);
	
}
