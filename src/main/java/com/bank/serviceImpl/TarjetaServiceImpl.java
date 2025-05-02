package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class TarjetaServiceImpl implements TarjetaService, UserDetailsService{
	
	private final TarjetaRepository tarjetaRepository;
	private final RoleUserRepository roleUserRepository;
	private final UserRepository userRepository;
	
	@Override
	public TarjetaDTO findByNumeroTarjeta(String numeroTarjeta) {
		return TarjetaDTO.tarjetaToTarjetaDTO(tarjetaRepository.findTarjetaByNumeroTarjetaAndEstadoTrue(numeroTarjeta).get());
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Tarjeta tarjeta = tarjetaRepository.findTarjetaByNumeroTarjetaAndEstadoTrue(username)
		.orElseThrow(() -> new UsernameNotFoundException("Tarjeta no encontrado " + username));
		return User.builder()
				.username(tarjeta.getNumeroTarjeta())
				.password(tarjeta.getClaveInternet())
				.authorities(List.of(new SimpleGrantedAuthority("ROLE_" + tarjeta.getUser().getRoleUser().getTipo())))
				.build();
	}
	
	@Override
	public List<TarjetaDTO> list() {
		List<TarjetaDTO> users = TarjetaDTO.listTarjetaToTarjetaDTO(tarjetaRepository.findTarjetaByEstadoTrue());
		return users;
	}
	@Override
	public List<TarjetaDTO> listByAll() {
		List<TarjetaDTO> users = TarjetaDTO.listTarjetaToTarjetaDTO(tarjetaRepository.findAll());
		return users;
	}
	@Override
	public Page<TarjetaDTO> listPage(int page, int size, String sortBy, String direction, String numeroTarjeta, String tipoMonedaTarjeta, String numeroTarjetaExcluida) {
		Direction sortDirection = Direction.ASC;
		if(direction != null && "desc".equalsIgnoreCase(direction.trim())) {
			sortDirection = Direction.DESC;
		}
		Sort sort = Sort.by(sortDirection, sortBy);
		Pageable pageable = PageRequest.of(page, size, sort);
		
		Page<Tarjeta> tarjetasPage = tarjetaRepository.findTarjetaByNumeroTarjetaIgnoreCaseContainingAndTipoMonedaTarjeta_tipoAndEstadoTrue(numeroTarjeta, tipoMonedaTarjeta, pageable);
		List<TarjetaDTO> tarjetas = tarjetasPage.stream()
				.map(TarjetaDTO::tarjetaToTarjetaDTO)
				.filter(t -> !Objects.equals(t.getNumeroTarjeta(), numeroTarjetaExcluida))
				.collect(Collectors.toList());
		
		return new PageImpl<>(tarjetas, pageable, tarjetasPage.getNumberOfElements());
	}
	@Override
	public TarjetaDTO find(int id) {
		Tarjeta t = tarjetaRepository.findTarjetaByIdAndEstadoTrue(id)
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
			        .findRoleUserByTipoAndEstadoTrue("USUARIO")
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
			user = userRepository.findUserByIdAndEstadoTrue(userDTO.getId())
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
		tarjetaTransformado.setEstado(true);
		Tarjeta result = tarjetaRepository.save(Objects.requireNonNull(tarjetaTransformado));
		return TarjetaDTO.tarjetaToTarjetaDTO(result);
	}
	@Override
	public String delete(int id) {
		Tarjeta t = tarjetaRepository.findTarjetaByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("Tarjeta no encontrado"));
		t.setEstado(false);
		tarjetaRepository.save(t);
		return "Tarjeta eliminada";
	}
	
	
}
