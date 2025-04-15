package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dto.RoleUserDTO;
import com.bank.dto.UserDTO;
import com.bank.exception.BadRequestParam;
import com.bank.exception.ResourceNotFound;
import com.bank.model.User;
import com.bank.repository.RoleUserRepository;
import com.bank.repository.UserRepository;
import com.bank.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleUserRepository roleUserRepository;
	
	
	@Override
	public List<UserDTO> list() {
		List<UserDTO> users = UserDTO.listUserToUserDTO(userRepository.findUserByEstado(true));		
		return users;
	}
	@Override
	public List<UserDTO> listByAll() {
		List<UserDTO> users = UserDTO.listUserToUserDTO(userRepository.findAll());		
		return users;
	}
	
	@Override
	public UserDTO find(int id) {
		User result = userRepository.findUserByIdAndEstado(id, true)
				.orElseThrow(() -> new ResourceNotFound("Usuario no encontrado "  + id ));
		return UserDTO.userToUserDTO(result);
		
	}
	
	@Override
	public UserDTO findByAll(int id) {
		User result = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Usuario no encontrado "  + id ));;
		return UserDTO.userToUserDTO(result);
	}
	
	@Override
	public UserDTO save(UserDTO userDTO) {
		
		//Agree the default value to role register for first time
		userDTO.setRoleUser(RoleUserDTO.builder()
				.id(roleUserRepository.findRoleUserByTipoAndEstado("USUARIO", true).get().getId())
				.build());
		//
		User userTransformado = UserDTO.userDTOToUser(userDTO);
		userTransformado.setEstado(true);
		User result = userRepository.save(Objects.requireNonNull(userTransformado));
		return UserDTO.userToUserDTO(result);
	}
	@Override
	public UserDTO update(UserDTO userDTO) {
		if(Objects.isNull(userDTO.getId())) {
			throw new BadRequestParam("Falta el paremetro id");
		}
		User roleUserTransformado = UserDTO.userDTOToUser(userDTO);
		User result = userRepository.save(Objects.requireNonNull(roleUserTransformado));
		return UserDTO.userToUserDTO(result);
	}
	@Override
	public String delete(int id) {
		User u = userRepository.findUserByIdAndEstado(id, true)
				.orElseThrow(() -> new ResourceNotFound("Usuario no encontrado "  + id ));
		u.setEstado(false);
		userRepository.save(u);
		return "Usuario eliminado correctamente";
	}
	
	
	
}
