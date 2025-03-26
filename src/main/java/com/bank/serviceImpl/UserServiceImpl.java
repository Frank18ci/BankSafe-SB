package com.bank.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.bank.dto.UserDTO;
import com.bank.repository.UserRepository;
import com.bank.service.UserService;

public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
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
