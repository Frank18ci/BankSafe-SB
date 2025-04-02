package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.bank.model.RoleUser;
import com.bank.dto.RoleUserDTO;
import com.bank.repository.RoleUserRepository;
import com.bank.service.RoleUserService;

public class UserRoleServiceImpl implements RoleUserService{
	@Autowired
	private RoleUserRepository roleUserRepository;
	
	@Autowired
	private ConversionService conversionService;
	
	@Override
	public List<RoleUserDTO> listUserRols() {
		List<RoleUserDTO> list = roleUserRepository.findAll().stream()
				.map(roleUserEntity -> conversionService.convert(roleUserEntity, RoleUserDTO.class))
				.collect(Collectors.toList());
		return list;
	}
	@Override
	public RoleUserDTO findUserRole(int id) {
		Optional<RoleUser> result = roleUserRepository.findById(id);
		if(result.isEmpty()) {
			  //crear funcion para controlar las excepciones
		}
		return conversionService.convert(result.get(), RoleUserDTO.class);
	}
	@Override
	public RoleUserDTO saveUserRole(RoleUserDTO userRoleDTO) {
		if(Objects.isNull(userRoleDTO.getId())) {
			//crear funcion para controlar las excepciones
		}
		
		RoleUser roleUserTransformado = conversionService.convert(userRoleDTO, RoleUser.class);
		RoleUser result = roleUserRepository.save(Objects.requireNonNull(roleUserTransformado));
		
		return conversionService.convert(result, RoleUserDTO.class);
	}
	@Override
	public RoleUserDTO updateUserRole(RoleUserDTO userRoleDTO) {
		if(Objects.isNull(userRoleDTO.getId())) {
			//crear funcion para controlar las excepciones
		}
		
		RoleUser roleUserTransformado = conversionService.convert(userRoleDTO, RoleUser.class);
		RoleUser result = roleUserRepository.save(Objects.requireNonNull(roleUserTransformado));
		return conversionService.convert(result, RoleUserDTO.class);
	}
	
	@Override
	public void deleteUserRole(int id) {
		roleUserRepository.deleteById(id);
	}
}
