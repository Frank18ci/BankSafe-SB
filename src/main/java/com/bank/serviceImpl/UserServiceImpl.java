package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dto.RoleUserDTO;
import com.bank.dto.TipoDocumentoUserDTO;
import com.bank.dto.UserDTO;
import com.bank.exception.ResourceNotFound;
import com.bank.model.RoleUser;
import com.bank.model.TipoDocumentoUser;
import com.bank.model.User;
import com.bank.repository.UserRepository;
import com.bank.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	private UserDTO UserToUserDTO(User user) {
		return UserDTO
				.builder()
				.id(user.getId())
				.numeroDocumento(user.getNumeroDocumento())
				.nombres(user.getApellidos())
				.apellidos(user.getApellidos())
				.fechaNacimiento(user.getFechaNacimiento())
				.imagePath(user.getImagePath())
				.roleUserDTO(RoleUserDTO.builder()
						.id(user.getRoleUser().getId())
						.tipo(user.getRoleUser().getTipo())
						.build())
				.tipoDocumentoUserDTO(TipoDocumentoUserDTO.builder()
						.id(user.getTipoDocumentoUser().getId())
						.tipo(user.getTipoDocumentoUser().getTipo())
						.build())
				.build();
	}
	private User UserDTOToUser(UserDTO userDTO)
	{
		return User
				.builder()
				.id(userDTO.getId())
				.numeroDocumento(userDTO.getNumeroDocumento())
				.nombres(userDTO.getApellidos())
				.apellidos(userDTO.getApellidos())
				.fechaNacimiento(userDTO.getFechaNacimiento())
				.imagePath(userDTO.getImagePath())
				.roleUser(RoleUser.builder()
						.id(userDTO.getRoleUserDTO().getId())
						.tipo(userDTO.getRoleUserDTO().getTipo())
						.build())
				.tipoDocumentoUser(TipoDocumentoUser.builder()
						.id(userDTO.getTipoDocumentoUserDTO().getId())
						.tipo(userDTO.getTipoDocumentoUserDTO().getTipo())
						.build())
				.build();
	}
	@Override
	public List<UserDTO> list() {
		List<UserDTO> users = userRepository.findAll().stream()
				.map(ue -> UserToUserDTO(ue))
				.collect(Collectors.toList());
				
		return users;
	}
	@Override
	public UserDTO find(int id) {
		Optional<User> result = userRepository.findById(id);
		if(result.isEmpty()) {
			  throw new ResourceNotFound("Usuario no encontrado "  + id );
		}
		User u = result.get();
		return UserToUserDTO(u);
		
	}
	@Override
	public UserDTO save(UserDTO userDTO) {
		if(Objects.isNull(userDTO.getId())) {
			//crear funcion para controlar las excepciones
		}
		User userTransformado = UserDTOToUser(userDTO);
		User result = userRepository.save(Objects.requireNonNull(userTransformado));
		return UserToUserDTO(result);
	}
	@Override
	public UserDTO update(UserDTO userDTO) {
		if(Objects.isNull(userDTO.getId())) {
			//crear funcion para controlar las excepciones
		}
		User roleUserTransformado = UserDTOToUser(userDTO);
		User result = userRepository.save(Objects.requireNonNull(roleUserTransformado));
		return UserToUserDTO(result);
	}
	@Override
	public void delete(int id) {
		userRepository.deleteById(id);
	}
	
	//Metodos para convertir 
	
}
