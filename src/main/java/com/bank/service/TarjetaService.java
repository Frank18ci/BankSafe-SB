package com.bank.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bank.dto.TarjetaDTO;


public interface TarjetaService {
	public List<TarjetaDTO> list();
	public TarjetaDTO save(TarjetaDTO tarjetaDTO);
	public TarjetaDTO find(int id);
	public TarjetaDTO update(TarjetaDTO tarjetaDTO);
	public void delete(int id);
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	public TarjetaDTO findByNumeroTarjeta(String numeroTarjeta);
}
