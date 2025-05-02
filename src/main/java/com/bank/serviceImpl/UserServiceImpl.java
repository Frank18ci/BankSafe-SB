package com.bank.serviceImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dto.RoleUserDTO;
import com.bank.dto.UserDTO;
import com.bank.dto.UserIDTO;
import com.bank.dto.UserWithImg;
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
		List<UserDTO> users = UserDTO.listUserToUserDTO(userRepository.findUserByEstadoTrue());		
		return users;
	}
	@Override
	public List<UserDTO> listByAll() {
		List<UserDTO> users = UserDTO.listUserToUserDTO(userRepository.findAll());		
		return users;
	}
	@Override
	public List<UserIDTO> listI() {
		List<UserIDTO> users = UserIDTO.listUserToListUserIDTO(userRepository.findAll());
		return users;
	}
	
	@Override
	public UserDTO find(int id) {
		User result = userRepository.findUserByIdAndEstadoTrue(id)
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
	public UserIDTO findI(int id) {
		User result = userRepository.findUserByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("Usuario no encontrado "  + id ));
		return UserIDTO.userToUserIDTO(result);
	}
	
	@Override
	public UserDTO save(UserDTO userDTO) {
		
		//Agree the default value to role register for first time
		userDTO.setRoleUser(RoleUserDTO.builder()
				.id(roleUserRepository.findRoleUserByTipoAndEstadoTrue("USUARIO").get().getId())
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
		User u = userRepository.findUserByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("Usuario no encontrado "  + id ));
		u.setEstado(false);
		userRepository.save(u);
		return "Usuario eliminado correctamente";
	}
	
	@Override
	public UserDTO actualizarUser(UserWithImg userWithImg) {
		if(userWithImg.getImagen().isEmpty())
			throw new BadRequestParam("No hay imagen");
		User user = userRepository.findUserByIdAndEstadoTrue(userWithImg.getId())
				.orElseThrow(() -> new ResourceNotFound("Usuario no encontrado "  + userWithImg.getId()));
		try {
			
			//Asigna datos recibidos
			user.setNombres(userWithImg.getNombres());
			user.setApellidos(userWithImg.getApellidos());
			user.setFechaNacimiento(userWithImg.getFechaNacimiento());
			user.setCorreo(userWithImg.getCorreo());
			user.setNumeroDocumento(userWithImg.getNumeroDocumento());
			
			//Realiza el guardado de la imagen
			Path directorioImagen = Paths.get("src","main","resources","static","uploads");
			String rutaAbsoluta = directorioImagen.toFile().getAbsolutePath();
			byte[] byteImg = userWithImg.getImagen().getBytes();
			String pathnuevo = String.valueOf(user.getId()) + userWithImg.getImagen().getOriginalFilename().substring(userWithImg.getImagen().getOriginalFilename().lastIndexOf("."));
			Path rutaCompleta = Paths.get(rutaAbsoluta, pathnuevo);
			Files.write(rutaCompleta, byteImg);
			//Asigna el path del id
			user.setImagePath(pathnuevo);
			//Realiza el guardado
			User usuarioSaved = userRepository.save(user);
			return UserDTO.userToUserDTO(usuarioSaved);
		} catch (Exception e) {
			throw new BadRequestParam("Error al guardar");
		}
	}
	
	
}
