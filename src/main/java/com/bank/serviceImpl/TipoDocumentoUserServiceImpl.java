package com.bank.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dto.TipoDocumentoUserDTO;
import com.bank.model.TipoDocumentoUser;
import com.bank.repository.TipoDocumentoUserRepository;
import com.bank.service.TipoDocumentoUserService;
@Service
public class TipoDocumentoUserServiceImpl implements TipoDocumentoUserService {
	@Autowired
	private TipoDocumentoUserRepository tipoDocumentoUserRepository;
	@Override
	public List<TipoDocumentoUserDTO> list() {
		List<TipoDocumentoUserDTO> lista = tipoDocumentoUserRepository.findAll().stream()
				.map(tdue -> TipoDocumentoUserDTO.builder()
						.id(tdue.getId())
						.tipo(tdue.getTipo())
						.build()).collect(Collectors.toList());
		return lista;
	}
	@Override
	public TipoDocumentoUserDTO find(int id) {
		Optional<TipoDocumentoUser> tipoDocumentoFind = tipoDocumentoUserRepository.findById(id);
		if(tipoDocumentoFind.isEmpty()) {
			//implementa metodo de no encontrado
		}
		TipoDocumentoUserDTO tipoDocumento = TipoDocumentoUserDTO.builder()
				.id(tipoDocumentoFind.get().getId())
				.tipo(tipoDocumentoFind.get().getTipo())
				.build();
		
		return tipoDocumento;
	}
	@Override
	public TipoDocumentoUserDTO save(TipoDocumentoUserDTO tipoDocumentoUserDTO) {
		TipoDocumentoUser tipoDocumentoUser = TipoDocumentoUser.builder()
				.id(tipoDocumentoUserDTO.getId())
				.tipo(tipoDocumentoUserDTO.getTipo())
				.build();
		TipoDocumentoUser tipoDocumentoUserSaved = tipoDocumentoUserRepository.save(tipoDocumentoUser);
				
		return TipoDocumentoUserDTO.builder()
				.id(tipoDocumentoUserSaved.getId())
				.tipo(tipoDocumentoUserSaved.getTipo())
				.build();
	}
	@Override
	public TipoDocumentoUserDTO update(TipoDocumentoUserDTO tipoDocumentoUserDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
}
