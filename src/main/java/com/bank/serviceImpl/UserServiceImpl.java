package com.bank.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bank.dto.UserDTO;
import com.bank.model.User;
import com.bank.repository.UserRepository;
import com.bank.service.UserService;
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	
	//modificar los permisos de acuerdo a la tabla user role
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado " + username));
		return org.springframework.security.core.userdetails.User.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.authorities(List.of(new SimpleGrantedAuthority("USUARIO")))
				.build();
	}
	
	@Override
	public List<UserDTO> listUsers() {
		List<UserDTO> users = userRepository.findAll().stream()
				.map(userEntity -> UserDTO.builder()
						.id(userEntity.getId())
						.documento(userEntity.getDocumento())
						.nombres(userEntity.getApellidos())
						.apellidos(userEntity.getApellidos())
						.edad(userEntity.getEdad())
						.password(userEntity.getApellidos())
						.fechaNacimiento(userEntity.getFechaNacimiento())
						.build())
				.collect(Collectors.toList());
		return users;
	}
	@Override
	public UserDTO findUser(int id) {
		return null;
	}
	@Override
	public UserDTO updateUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		
	}
}
