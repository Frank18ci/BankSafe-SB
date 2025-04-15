package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import com.bank.dto.TipoTransacionDTO;
import com.bank.dto.UserDTO;
import com.bank.exception.BadRequestParam;
import com.bank.exception.ResourceNotFound;
import com.bank.model.TipoTransacion;
import com.bank.model.User;
import com.bank.repository.TipoTransaccionRepository;
import com.bank.service.TipoTransacionService;

public class TipoTransacionServiceImpl implements TipoTransacionService {
	
	@Autowired
	private TipoTransaccionRepository tipoTransaccionRepository;
	@Override
	public List<TipoTransacionDTO> listByAll() {
		List<TipoTransacionDTO> tipoTransaciones = TipoTransacionDTO.listTipoTransacionToListTipoTransacionDTO(tipoTransaccionRepository.findAll());
		return tipoTransaciones;
	}

	@Override
	public List<TipoTransacionDTO> list() {
		List<TipoTransacionDTO> tipoTransaciones = TipoTransacionDTO.listTipoTransacionToListTipoTransacionDTO(tipoTransaccionRepository.findTipoTransacionByEstado(true));
		return tipoTransaciones;
	}

	@Override
	public TipoTransacionDTO find(int id) {
		TipoTransacion result = tipoTransaccionRepository.findTipoTransacionByIdAndEstado(id, true)
				.orElseThrow(() -> new ResourceNotFound("Tipo Transacion no encontrado "  + id ));
		return TipoTransacionDTO.tipoTransacionToTipoTransacionDTO(result);
	}

	@Override
	public TipoTransacionDTO findByAll(int id) {
		TipoTransacion result = tipoTransaccionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Tipo Transacion no encontrado "  + id ));;
		return TipoTransacionDTO.tipoTransacionToTipoTransacionDTO(result);
	}

	@Override
	public TipoTransacionDTO save(TipoTransacionDTO transacionDTO) {
		TipoTransacion Transformado = TipoTransacionDTO.tipoTransacionDTOToTipoTransacion(transacionDTO);
		TipoTransacion result = tipoTransaccionRepository.save(Objects.requireNonNull(Transformado));
		return TipoTransacionDTO.tipoTransacionToTipoTransacionDTO(result);
	}

	@Override
	public TipoTransacionDTO update(TipoTransacionDTO transacionDTO) {
		if(Objects.isNull(transacionDTO.getId())) {
			throw new BadRequestParam("Falta el paremetro id");
		}
		TipoTransacion roleUserTransformado = TipoTransacionDTO.tipoTransacionDTOToTipoTransacion(transacionDTO);
		TipoTransacion result = tipoTransaccionRepository.save(Objects.requireNonNull(roleUserTransformado));
		return TipoTransacionDTO.tipoTransacionToTipoTransacionDTO(result);
	}

	@Override
	public String delete(int id) {
		TipoTransacion u = tipoTransaccionRepository.findTipoTransacionByIdAndEstado(id, true)
				.orElseThrow(() -> new ResourceNotFound("Tipo Transaccion no encontrado "  + id ));
		u.setEstado(false);
		tipoTransaccionRepository.save(u);
		return "Tipo Transaccion eliminado correctamente";
	}

	

	
	
}
