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
import org.springframework.transaction.annotation.Transactional;

import com.bank.dto.RoleUserDTO;
import com.bank.dto.TarjetaDTO;
import com.bank.dto.UserDTO;
import com.bank.exception.BadRequestParam;
import com.bank.exception.ResourceNotFound;
import com.bank.model.RoleUser;
import com.bank.model.Tarjeta;
import com.bank.repository.RoleUserRepository;
import com.bank.repository.TarjetaRepository;
import com.bank.repository.UserRepository;
import com.bank.service.TarjetaService;

@Service
public class TarjetaServiceImpl implements TarjetaService, UserDetailsService{
	@Autowired
	private TarjetaRepository tarjetaRepository;
	@Autowired
	private RoleUserRepository roleUserRepository;
	@Autowired
	private UserRepository userRepository;
	
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
	public List<TarjetaDTO> listByAll() {
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
	@Transactional
	@Override
	public TarjetaDTO save(TarjetaDTO tarjetaDTO) {
		UserDTO userDTO = tarjetaDTO.getUser();
		 RoleUser role = roleUserRepository
			        .findRoleUserByTipoAndEstado("USUARIO", true)
			        .orElseThrow(() -> new ResourceNotFound("Rol USUARIO no encontrado"));
		 RoleUserDTO roleDTO = RoleUserDTO.rolUserToRolUserDTO(role);
		userDTO.setRoleUser(roleDTO); 
		tarjetaDTO.setUser(userDTO);
		
		Tarjeta tarjetaTransformado = TarjetaDTO.tarjetaDTOTotarjeta(tarjetaDTO);
		tarjetaTransformado.setEstado(true);
		
		com.bank.model.User user;
		if(userDTO.getId() <= 0) {
			System.out.println(userDTO.getId() <= 0);
			user = tarjetaTransformado.getUser();
		      user.setRoleUser(role);
		      user.setEstado(true);
		      userRepository.save(user);
		} else {
			user = userRepository.findUserByIdAndEstado(userDTO.getId(), true)
				    .orElseThrow(() -> new ResourceNotFound("Usuario no encontrado con id: " + userDTO.getId()));
		}
		Tarjeta result = tarjetaRepository.save(Objects.requireNonNull(tarjetaTransformado));
		return TarjetaDTO.tarjetaToTarjetaDTO(result);
	}
	@Override
	public TarjetaDTO update(TarjetaDTO tarjetaDTO) {
		if(Objects.isNull(tarjetaDTO.getId())) {
			throw new BadRequestParam("Faltan paramentros tarjeta");
		}
		Tarjeta tarjetaTransformado = TarjetaDTO.tarjetaDTOTotarjeta(tarjetaDTO);
		Tarjeta result = tarjetaRepository.save(Objects.requireNonNull(tarjetaTransformado));
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
