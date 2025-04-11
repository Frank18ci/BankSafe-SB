package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bank.dto.RoleUserDTO;
import com.bank.dto.TarjetaDTO;
import com.bank.dto.TipoDocumentoUserDTO;
import com.bank.dto.TipoMonedaTarjetaDTO;
import com.bank.dto.TipoTarjetaDTO;
import com.bank.dto.UserDTO;
import com.bank.exception.ResourceNotFound;
import com.bank.model.RoleUser;
import com.bank.model.Tarjeta;
import com.bank.model.TipoDocumentoUser;
import com.bank.model.TipoMonedaTarjeta;
import com.bank.model.TipoTarjeta;
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
		return tarjetaToTarjetaDTO(tarjetaRepository.findTarjetaByNumeroTarjeta(numeroTarjeta).get());
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Tarjeta tarjeta = tarjetaRepository.findTarjetaByNumeroTarjeta(username)
		.orElseThrow(() -> new UsernameNotFoundException("Tarjeta no encontrado " + username));
		return User.builder()
				.username(tarjeta.getNumeroTarjeta())
				.password(tarjeta.getClaveInternet())
				.authorities(List.of(new SimpleGrantedAuthority("USUARIO")))
				.build();
	}
	public TarjetaDTO tarjetaToTarjetaDTO(Tarjeta tarjeta) {
		return TarjetaDTO
				.builder()
				.id(tarjeta.getId())
				.cvv(tarjeta.getCvv())
				.fechaVencimiento(tarjeta.getFechaVencimiento())
				.numeroTarjeta(tarjeta.getNumeroTarjeta())
				.claveInternet(tarjeta.getClaveInternet())
				.monto(tarjeta.getMonto())
				.user(UserDTO.builder()
						.id(tarjeta.getUser().getId())
						.documento(tarjeta.getUser().getDocumento())
						.nombres(tarjeta.getUser().getNumeroDocumento())
						.apellidos(tarjeta.getUser().getApellidos())
						.fechaNacimiento(tarjeta.getUser().getFechaNacimiento())
						.imagePath(tarjeta.getUser().getImagePath())
						.roleUser(RoleUserDTO.builder()
								.id(tarjeta.getUser().getRoleUser().getId())
								.tipo(tarjeta.getUser().getRoleUser().getTipo())
								.build())
						.tipoDocumentoUser(TipoDocumentoUserDTO.builder()
								.id(tarjeta.getUser().getTipoDocumentoUser().getId())
								.tipo(tarjeta.getUser().getTipoDocumentoUser().getTipo())
								.build())
						.build())
				.tipoTarjeta(TipoTarjetaDTO.builder()
						.id(tarjeta.getTipoTarjeta().getId())
						.tipo(tarjeta.getTipoTarjeta().getTipo())
						.build())
				.tipoMonedaTarjeta(TipoMonedaTarjetaDTO.builder()
						.id(tarjeta.getTipoMonedaTarjeta().getId())
						.nombre(tarjeta.getTipoMonedaTarjeta().getNombre())
						.simbolo(tarjeta.getTipoMonedaTarjeta().getSimbolo())
						.tipo(tarjeta.getTipoMonedaTarjeta().getTipo())
						.build())
				.build();
	}
	public Tarjeta tarjetaDTOTotarjeta(TarjetaDTO tarjetaDTO) {
		return Tarjeta
				.builder()
				.id(tarjetaDTO.getId())
				.cvv(tarjetaDTO.getCvv())
				.fechaVencimiento(tarjetaDTO.getFechaVencimiento())
				.numeroTarjeta(tarjetaDTO.getNumeroTarjeta())
				.claveInternet(tarjetaDTO.getClaveInternet())
				.monto(tarjetaDTO.getMonto())
				.user(com.bank.model.User.builder()
						.id(tarjetaDTO.getUser().getId())
						.documento(tarjetaDTO.getUser().getDocumento())
						.nombres(tarjetaDTO.getUser().getNumeroDocumento())
						.apellidos(tarjetaDTO.getUser().getApellidos())
						.fechaNacimiento(tarjetaDTO.getUser().getFechaNacimiento())
						.imagePath(tarjetaDTO.getUser().getImagePath())
						.roleUser(RoleUser.builder()
								.id(tarjetaDTO.getUser().getRoleUser().getId())
								.tipo(tarjetaDTO.getUser().getRoleUser().getTipo())
								.build())
						.tipoDocumentoUser(TipoDocumentoUser.builder()
								.id(tarjetaDTO.getUser().getTipoDocumentoUser().getId())
								.tipo(tarjetaDTO.getUser().getTipoDocumentoUser().getTipo())
								.build())
						.build())
				.tipoTarjeta(TipoTarjeta.builder()
						.id(tarjetaDTO.getTipoTarjeta().getId())
						.tipo(tarjetaDTO.getTipoTarjeta().getTipo())
						.build())
				.tipoMonedaTarjeta(TipoMonedaTarjeta.builder()
						.id(tarjetaDTO.getTipoMonedaTarjeta().getId())
						.nombre(tarjetaDTO.getTipoMonedaTarjeta().getNombre())
						.simbolo(tarjetaDTO.getTipoMonedaTarjeta().getSimbolo())
						.tipo(tarjetaDTO.getTipoMonedaTarjeta().getTipo())
						.build())
				.build();
	}
	@Override
	public List<TarjetaDTO> list() {
		List<TarjetaDTO> users = tarjetaRepository.findAll().stream()
				.map(ue -> tarjetaToTarjetaDTO(ue))
				.collect(Collectors.toList());
				
		return users;
	}
	@Override
	public TarjetaDTO find(int id) {
		Optional<Tarjeta> result = tarjetaRepository.findById(id);
		if(result.isEmpty()) {
			  throw new ResourceNotFound("Usuario no encontrado "  + id );
		}
		Tarjeta u = result.get();
		return tarjetaToTarjetaDTO(u);
	}
	@Override
	public TarjetaDTO update(TarjetaDTO tarjetaDTO) {
		if(Objects.isNull(tarjetaDTO.getId())) {
			//crear funcion para controlar las excepciones
		}
		Tarjeta userTransformado = tarjetaDTOTotarjeta(tarjetaDTO);
		Tarjeta result = tarjetaRepository.save(Objects.requireNonNull(userTransformado));
		return tarjetaToTarjetaDTO(result);
	}
	@Override
	public void delete(int id) {
		tarjetaRepository.deleteById(id);
		
	}
	
	@Override
	public TarjetaDTO save(TarjetaDTO tarjetaDTO) {
		if(Objects.isNull(tarjetaDTO.getId())) {
			//crear funcion para controlar las excepciones
		}
		UserDTO user = tarjetaDTO.getUser();
		user.setRoleUser(RoleUserDTO.builder()
				.id(roleUserRepository.findRoleUserByTipo("USUARIO").get().getId())
				.build());
		tarjetaDTO.setUser(user);
		
		Tarjeta userTransformado = tarjetaDTOTotarjeta(tarjetaDTO);
		Tarjeta result = tarjetaRepository.save(Objects.requireNonNull(userTransformado));
		return tarjetaToTarjetaDTO(result);
	}
}
