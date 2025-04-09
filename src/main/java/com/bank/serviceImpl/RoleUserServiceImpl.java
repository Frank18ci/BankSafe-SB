package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.model.RoleUser;
import com.bank.dto.RoleUserDTO;
import com.bank.repository.RoleUserRepository;
import com.bank.service.RoleUserService;
@Service
public class RoleUserServiceImpl implements RoleUserService{
	@Autowired
	private RoleUserRepository roleUserRepository;
	
	private RoleUserDTO roleUserToRoleUserDTO(RoleUser roleUser) {
		return RoleUserDTO
				.builder()
				.id(roleUser.getId())
				.tipo(roleUser.getTipo())
				.build();
	}
	private RoleUser roleUserDTOToRoleUser(RoleUserDTO roleUserDTO) {
		return RoleUser
				.builder()
				.id(roleUserDTO.getId())
				.tipo(roleUserDTO.getTipo())
				.build();
	}
	
	@Override
	public List<RoleUserDTO> list() {
		List<RoleUserDTO> list = roleUserRepository.findAll().stream()
				.map(roleUserEntity -> roleUserToRoleUserDTO(roleUserEntity))
				.collect(Collectors.toList());
		return list;
	}
	@Override
	public RoleUserDTO find(int id) {
		Optional<RoleUser> result = roleUserRepository.findById(id);
		if(result.isEmpty()) {
			  //crear funcion para controlar las excepciones
		}
		RoleUser rl = result.get();
		return roleUserToRoleUserDTO(rl);
	}

	@Override
	public RoleUserDTO save(RoleUserDTO userRoleDTO) {
		if(Objects.isNull(userRoleDTO.getId())) {
			//crear funcion para controlar las excepciones
		}
		RoleUser roleUserTransformado = roleUserDTOToRoleUser(userRoleDTO);
		RoleUser result = roleUserRepository.save(Objects.requireNonNull(roleUserTransformado));
		return roleUserToRoleUserDTO(result);
	}
	@Override
	public RoleUserDTO update(RoleUserDTO userRoleDTO) {
		if(Objects.isNull(userRoleDTO.getId())) {
			//crear funcion para controlar las excepciones
		}
		RoleUser roleUserTransformado = roleUserDTOToRoleUser(userRoleDTO);
		RoleUser result = roleUserRepository.save(Objects.requireNonNull(roleUserTransformado));
		return roleUserToRoleUserDTO(result);
	}
	
	@Override
	public void delete(int id) {
		roleUserRepository.deleteById(id);
	}
}
