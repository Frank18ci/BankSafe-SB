package com.bank.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bank.dto.TarjetaDTO;


public interface TarjetaService {
	public List<TarjetaDTO> list();
	public List<TarjetaDTO> listByAll();
	public TarjetaDTO find(int id);
	public TarjetaDTO findByAll(int id);
	public TarjetaDTO findByNumeroTarjeta(String numeroTarjeta);
	public TarjetaDTO save(TarjetaDTO tarjetaDTO);
	public TarjetaDTO update(TarjetaDTO tarjetaDTO);
	public String delete(int id);
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
