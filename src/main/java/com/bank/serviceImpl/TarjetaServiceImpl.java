package com.bank.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bank.dto.TarjetaDTO;
import com.bank.model.Tarjeta;
import com.bank.repository.TarjetaRepository;
import com.bank.service.TarjetaService;

@Service
public class TarjetaServiceImpl implements TarjetaService, UserDetailsService{
	@Autowired
	private TarjetaRepository tarjetaRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Tarjeta tarjeta = tarjetaRepository.findTarjetaByNumeroTarjeta(username)
		//.orElseThrow(() -> new UsernameNotFoundException("Tarjeta no encontrado " + username));
		Tarjeta tarjeta = new Tarjeta();
		return User.builder()
				.username(tarjeta.getNumeroTarjeta())
				.password(tarjeta.getClaveInternet())
				.authorities(List.of(new SimpleGrantedAuthority("USUARIO")))
				.build();
	}
	@Override
	public List<TarjetaDTO> listTarjetas() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public TarjetaDTO findTarjeta(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public TarjetaDTO updateTarjeta(TarjetaDTO tarjetaDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void deleteTarjeta(int id) {
		// TODO Auto-generated method stub
		
	}
}
