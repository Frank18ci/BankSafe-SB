package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.model.RoleUser;
import com.bank.dto.RoleUserDTO;
import com.bank.exception.BadRequestParam;
import com.bank.exception.ResourceNotFound;
import com.bank.repository.RoleUserRepository;
import com.bank.service.RoleUserService;
@Service
public class RoleUserServiceImpl implements RoleUserService{
	@Autowired
	private RoleUserRepository roleUserRepository;

	@Override
	public List<RoleUserDTO> listByAll() {
		List<RoleUserDTO> roleUsers = RoleUserDTO.listRoleUserToListUserDTO(roleUserRepository.findAll());
		return roleUsers;
	}

	@Override
	public List<RoleUserDTO> list() {
		List<RoleUserDTO> roleUsers = RoleUserDTO.listRoleUserToListUserDTO(roleUserRepository.findRolUserByEstadoTrue());
		return roleUsers;
	}

	@Override
	public RoleUserDTO find(int id) {
		RoleUser roleUser = roleUserRepository.findRolUserByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("RolUsuario no encontrado "  + id ));
		return RoleUserDTO.rolUserToRolUserDTO(roleUser);
	}

	@Override
	public RoleUserDTO findByAll(int id) {
		RoleUser roleUser = roleUserRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("RolUsuario no encontrado "  + id ));;
		return RoleUserDTO.rolUserToRolUserDTO(roleUser);
	}

	@Override
	public RoleUserDTO save(RoleUserDTO userRoleDTO) {
		RoleUser rolUserTransformado = RoleUserDTO.rolUserDTOToRolUser(userRoleDTO);
		rolUserTransformado.setEstado(true);
		RoleUser result = roleUserRepository.save(Objects.requireNonNull(rolUserTransformado));
		return RoleUserDTO.rolUserToRolUserDTO(result);
	}

	@Override
	public RoleUserDTO update(RoleUserDTO userRoleDTO) {
		if(Objects.isNull(userRoleDTO.getId())) {
			throw new BadRequestParam("Falta el paremetro id");
		}
		RoleUser rolUserTransformado = RoleUserDTO.rolUserDTOToRolUser(userRoleDTO);
		RoleUser result = roleUserRepository.save(Objects.requireNonNull(rolUserTransformado));
		return RoleUserDTO.rolUserToRolUserDTO(result);
	}

	@Override
	public String delete(int id) {
		RoleUser u = roleUserRepository.findRolUserByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("Rol Usuario no encontrado "  + id ));
		u.setEstado(false);
		roleUserRepository.save(u);
		return "Usuario eliminado correctamente";
	}

	
	
	
}
