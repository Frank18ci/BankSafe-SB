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
public class UserRoleServiceImpl implements RoleUserService{
	@Autowired
	private RoleUserRepository roleUserRepository;
	
	@Override
	public List<RoleUserDTO> listUserRols() {
		List<RoleUserDTO> list = roleUserRepository.findAll().stream()
				.map(roleUserEntity -> RoleUserDTO
						.builder()
						.id(roleUserEntity.getId())
						.tipo(roleUserEntity.getTipo())
						.build())
				.collect(Collectors.toList());
		return list;
	}
	@Override
	public RoleUserDTO findUserRole(int id) {
		Optional<RoleUser> result = roleUserRepository.findById(id);
		if(result.isEmpty()) {
			  //crear funcion para controlar las excepciones
		}
		RoleUser rl = result.get();
		return RoleUserDTO
				.builder()
				.id(rl.getId())
				.tipo(rl.getTipo())
				.build();
	}
	@Override
	public RoleUserDTO saveUserRole(RoleUserDTO userRoleDTO) {
		if(Objects.isNull(userRoleDTO.getId())) {
			//crear funcion para controlar las excepciones
		}
		
		RoleUser roleUserTransformado = RoleUser
											.builder()
											.id(userRoleDTO.getId())
											.tipo(userRoleDTO.getTipo())
											.build();
		RoleUser result = roleUserRepository.save(Objects.requireNonNull(roleUserTransformado));
		
		return RoleUserDTO
				.builder()
				.id(result.getId())
				.tipo(result.getTipo())
				.build();
	}
	@Override
	public RoleUserDTO updateUserRole(RoleUserDTO userRoleDTO) {
		if(Objects.isNull(userRoleDTO.getId())) {
			//crear funcion para controlar las excepciones
		}
		
		RoleUser roleUserTransformado = RoleUser
				.builder()
				.id(userRoleDTO.getId())
				.tipo(userRoleDTO.getTipo())
				.build();
		RoleUser result = roleUserRepository.save(Objects.requireNonNull(roleUserTransformado));
		return RoleUserDTO
				.builder()
				.id(result.getId())
				.tipo(result.getTipo())
				.build();
	}
	
	@Override
	public void deleteUserRole(int id) {
		roleUserRepository.deleteById(id);
	}
}
