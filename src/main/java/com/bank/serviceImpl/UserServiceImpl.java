package com.bank.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bank.dto.RoleUserDTO;
import com.bank.dto.TipoDocumentoUserDTO;
import com.bank.dto.UserDTO;
import com.bank.model.User;
import com.bank.repository.UserRepository;
import com.bank.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
		
	@Override
	public List<UserDTO> list() {
		
		List<UserDTO> users = userRepository.findAll().stream()
				.map(ue -> UserDTO.builder()
						.id(ue.getId())
						.numeroDocumento(ue.getNumeroDocumento())
						.nombres(ue.getApellidos())
						.apellidos(ue.getApellidos())
						.fechaNacimiento(ue.getFechaNacimiento())
						.imagePath(ue.getImagePath())
						.roleUserDTO(RoleUserDTO.builder()
								.id(ue.getRoleUser().getId())
								.tipo(ue.getRoleUser().getTipo())
								.build())
						.tipoDocumentoUserDTO(TipoDocumentoUserDTO.builder()
								.id(ue.getTipoDocumento().getId())
								.tipo(ue.getTipoDocumento().getTipo())
								.build())
						.build())
				.collect(Collectors.toList());
				
		return users;
	}
	@Override
	public UserDTO find(int id) {
		return null;
	}
	@Override
	public UserDTO create(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public UserDTO update(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
	//Metodos para convertir 
	
}
