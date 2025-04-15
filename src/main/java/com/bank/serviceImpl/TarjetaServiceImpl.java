package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bank.dto.RoleUserDTO;
import com.bank.dto.TarjetaDTO;
import com.bank.dto.UserDTO;
import com.bank.exception.BadRequestParam;
import com.bank.exception.ResourceNotFound;
import com.bank.model.Tarjeta;
import com.bank.repository.RoleUserRepository;
import com.bank.repository.TarjetaRepository;
import com.bank.service.TarjetaService;

@Service
public class TarjetaServiceImpl implements TarjetaService, UserDetailsService{
	@Autowired
	private TarjetaRepository tarjetaRepository;
	@Autowired
	private RoleUserRepository roleUserRepository;
	
	@Override
	public TarjetaDTO findByNumeroTarjeta(String numeroTarjeta) {
		return TarjetaDTO.tarjetaToTarjetaDTO(tarjetaRepository.findTarjetaByNumeroTarjetaAndEstado(numeroTarjeta, true).get());
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Tarjeta tarjeta = tarjetaRepository.findTarjetaByNumeroTarjetaAndEstado(username, true)
		.orElseThrow(() -> new UsernameNotFoundException("Tarjeta no encontrado " + username));
		return User.builder()
				.username(tarjeta.getNumeroTarjeta())
				.password(tarjeta.getClaveInternet())
				.authorities(List.of(new SimpleGrantedAuthority("ROLE_" + tarjeta.getUser().getRoleUser().getTipo())))
				.build();
	}
	
	@Override
	public List<TarjetaDTO> list() {
		List<TarjetaDTO> users = TarjetaDTO.listTarjetaToTarjetaDTO(tarjetaRepository.findTarjetaByEstado(true));
		return users;
	}
	@Override
	public List<TarjetaDTO> listAll() {
		List<TarjetaDTO> users = TarjetaDTO.listTarjetaToTarjetaDTO(tarjetaRepository.findAll());
		return users;
	}
	@Override
	public TarjetaDTO find(int id) {
		Tarjeta t = tarjetaRepository.findTarjetaByIdAndEstado(id, true)
				.orElseThrow(() -> new ResourceNotFound("Tarjeta no encontrado"));
		return TarjetaDTO.tarjetaToTarjetaDTO(t);
	}
	@Override
	public TarjetaDTO findByAll(int id) {
		Tarjeta t = tarjetaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Tarjeta no encontrado"));
		return TarjetaDTO.tarjetaToTarjetaDTO(t);
	}
	@Override
	public TarjetaDTO save(TarjetaDTO tarjetaDTO) {
		UserDTO user = tarjetaDTO.getUser();
		user.setRoleUser(RoleUserDTO.builder()
				.id(roleUserRepository.findRoleUserByTipo("USUARIO").get().getId())
				.build());
		tarjetaDTO.setUser(user);
		
		Tarjeta userTransformado = TarjetaDTO.tarjetaDTOTotarjeta(tarjetaDTO);
		Tarjeta result = tarjetaRepository.save(Objects.requireNonNull(userTransformado));
		return TarjetaDTO.tarjetaToTarjetaDTO(result);
	}
	@Override
	public TarjetaDTO update(TarjetaDTO tarjetaDTO) {
		if(Objects.isNull(tarjetaDTO.getId())) {
			throw new BadRequestParam("Faltan paramentros tarjeta");
		}
		Tarjeta userTransformado = TarjetaDTO.tarjetaDTOTotarjeta(tarjetaDTO);
		Tarjeta result = tarjetaRepository.save(Objects.requireNonNull(userTransformado));
		return TarjetaDTO.tarjetaToTarjetaDTO(result);
	}
	@Override
	public String delete(int id) {
		Tarjeta t = tarjetaRepository.findTarjetaByIdAndEstado(id, true)
				.orElseThrow(() -> new ResourceNotFound("Tarjeta no encontrado"));
		t.setEstado(false);
		tarjetaRepository.save(t);
		return "Tarjeta eliminada";
	}
	
	
}
