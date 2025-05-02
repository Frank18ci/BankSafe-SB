package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dto.TransacionDTO;
import com.bank.exception.BadRequestParam;
import com.bank.exception.ResourceNotFound;
import com.bank.model.Transacion;
import com.bank.repository.TransacionRepository;
import com.bank.service.TransaccionService;
@Service
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
		Transacion transacionTransformado = TransacionDTO.transacionDTOToTransacion(transacionDTO);
		transacionTransformado.setEstado(true);
		Transacion result = transaccionRepository.save(Objects.requireNonNull(transacionTransformado));
		return TransacionDTO.transacionToTransacionDTO(result);
	}

	@Override
	public TransacionDTO update(TransacionDTO transacionDTO) {
		if(Objects.isNull(transacionDTO.getId())) {
			throw new BadRequestParam("Falta el paremetro id");
		}
		Transacion transacionTransformado = TransacionDTO.transacionDTOToTransacion(transacionDTO);
		Transacion result = transaccionRepository.save(Objects.requireNonNull(transacionTransformado));
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
