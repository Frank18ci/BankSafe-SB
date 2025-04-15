package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import com.bank.dto.TransacionDTO;
import com.bank.dto.UserDTO;
import com.bank.exception.BadRequestParam;
import com.bank.exception.ResourceNotFound;
import com.bank.model.Transacion;
import com.bank.model.User;
import com.bank.repository.RoleUserRepository;
import com.bank.repository.TipoTransaccionRepository;
import com.bank.repository.TransacionRepository;
import com.bank.service.TransaccionService;

public class TransaccionServiceimpl implements TransaccionService {
	@Autowired
	private TransacionRepository transaccionRepository;
	@Override
	public List<TransacionDTO> listByAll() {
		List<TransacionDTO> transaciones = TransacionDTO.listTransacionToListTransacionDTO(transaccionRepository.findAll());		
		return transaciones;
	}

	@Override
	public List<TransacionDTO> list() {
		List<TransacionDTO> transaciones = TransacionDTO.listTransacionToListTransacionDTO(transaccionRepository.findTransacionByEstado(true));		
		return transaciones;
	}

	@Override
	public TransacionDTO find(int id) {
		Transacion result = transaccionRepository.findTransacByIdAndEstado(id, true)
				.orElseThrow(() -> new ResourceNotFound("Transacion no encontrado "  + id ));
		return TransacionDTO.transacionToTransacionDTO(result);
		
	}

	@Override
	public TransacionDTO findByAll(int id) {
		Transacion result = transaccionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Transacion no encontrado "  + id ));;
		return TransacionDTO.transacionToTransacionDTO(result);
	}

	@Override
	public TransacionDTO save(TransacionDTO transacionDTO) {
		Transacion Transformado = TransacionDTO.transacionDTOToTransacion(transacionDTO);
		Transacion result = transaccionRepository.save(Objects.requireNonNull(Transformado));
		return TransacionDTO.transacionToTransacionDTO(result);
	}

	@Override
	public TransacionDTO update(TransacionDTO transacionDTO) {
		if(Objects.isNull(transacionDTO.getId())) {
			throw new BadRequestParam("Falta el paremetro id");
		}
		Transacion Transformado = TransacionDTO.transacionDTOToTransacion(transacionDTO);
		Transacion result = transaccionRepository.save(Objects.requireNonNull(Transformado));
		return TransacionDTO.transacionToTransacionDTO(result);
	}

	@Override
	public String delete(int id) {
		Transacion u = transaccionRepository.findTransacByIdAndEstado(id, true)
				.orElseThrow(() -> new ResourceNotFound("Transacion no encontrado "  + id ));
		u.setEstado(false);
		transaccionRepository.save(u);
		return "Transacion eliminada correctamente";
	}

	

}
