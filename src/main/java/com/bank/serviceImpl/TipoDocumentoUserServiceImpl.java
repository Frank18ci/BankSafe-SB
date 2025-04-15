package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dto.TipoDocumentoUserDTO;
import com.bank.dto.UserDTO;
import com.bank.exception.BadRequestParam;
import com.bank.exception.ResourceNotFound;
import com.bank.model.TipoDocumentoUser;
import com.bank.model.User;
import com.bank.repository.TipoDocumentoUserRepository;
import com.bank.service.TipoDocumentoUserService;
@Service
public class TipoDocumentoUserServiceImpl implements TipoDocumentoUserService {
	@Autowired
	private TipoDocumentoUserRepository tipoDocumentoUserRepository;

	@Override
	public List<TipoDocumentoUserDTO> listByAll() {
		List<TipoDocumentoUserDTO> rpta = TipoDocumentoUserDTO.listTipoDocumentoUserToListTipoDocumentoUserDTO(tipoDocumentoUserRepository.findAll());		
		return rpta;
	}

	@Override
	public List<TipoDocumentoUserDTO> list() {
		List<TipoDocumentoUserDTO> rpta = TipoDocumentoUserDTO.listTipoDocumentoUserToListTipoDocumentoUserDTO(tipoDocumentoUserRepository.findTipoDocumentoByEstado(true));		
		return rpta;
	}

	@Override
	public TipoDocumentoUserDTO find(int id) {
		TipoDocumentoUser result = tipoDocumentoUserRepository.findTipoDocumentoByIdAndEstado(id, true)
				.orElseThrow(() -> new ResourceNotFound("Tipo Documento User no encontrado "  + id ));
		return TipoDocumentoUserDTO.tipoDocumentoUserToTipoDocumentoUserDTO(result);
	}

	@Override
	public TipoDocumentoUserDTO findByAll(int id) {
		TipoDocumentoUser result = tipoDocumentoUserRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Tipo Documento User no encontrado "  + id ));;
		return TipoDocumentoUserDTO.tipoDocumentoUserToTipoDocumentoUserDTO(result);
	}

	@Override
	public TipoDocumentoUserDTO save(TipoDocumentoUserDTO tipoDocumentoUserDTO) {
		TipoDocumentoUser userTransformado = TipoDocumentoUserDTO.tipoDocumentoUserDTOToTipoDocumentoUser(tipoDocumentoUserDTO);
		TipoDocumentoUser result = tipoDocumentoUserRepository.save(Objects.requireNonNull(userTransformado));
		return TipoDocumentoUserDTO.tipoDocumentoUserToTipoDocumentoUserDTO(result);
	}

	@Override
	public TipoDocumentoUserDTO update(TipoDocumentoUserDTO tipoDocumentoUserDTO) {
		if(Objects.isNull(tipoDocumentoUserDTO.getId())) {
			throw new BadRequestParam("Falta el paremetro id");
		}
		TipoDocumentoUser roleUserTransformado = TipoDocumentoUserDTO.tipoDocumentoUserDTOToTipoDocumentoUser(tipoDocumentoUserDTO);
		TipoDocumentoUser result = tipoDocumentoUserRepository.save(Objects.requireNonNull(roleUserTransformado));
		return TipoDocumentoUserDTO.tipoDocumentoUserToTipoDocumentoUserDTO(result);
	}

	@Override
	public String delete(int id) {
		TipoDocumentoUser u = tipoDocumentoUserRepository.findTipoDocumentoByIdAndEstado(id, true)
				.orElseThrow(() -> new ResourceNotFound("Usuario no encontrado "  + id ));
		u.setEstado(false);
		tipoDocumentoUserRepository.save(u);
		return "Tipo Documento User eliminado correctamente";
	}
	
	
}
