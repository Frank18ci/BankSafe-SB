package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dto.TipoTransacionDTO;
import com.bank.exception.BadRequestParam;
import com.bank.exception.ResourceNotFound;
import com.bank.model.TipoTransacion;
import com.bank.repository.TipoTransaccionRepository;
import com.bank.service.TipoTransacionService;
@Service
public class TipoTransacionServiceImpl implements TipoTransacionService {
	
	@Autowired
	private TipoTransaccionRepository tipoTransaccionRepository;
	@Override
	public List<TipoTransacionDTO> listByAll() {
		List<TipoTransacionDTO> tipoTransaciones = TipoTransacionDTO
				.listTipoTransacionToListTipoTransacionDTO(tipoTransaccionRepository.findAll());
		return tipoTransaciones;
	}

	@Override
	public List<TipoTransacionDTO> list() {
		List<TipoTransacionDTO> tipoTransaciones = TipoTransacionDTO
				.listTipoTransacionToListTipoTransacionDTO(tipoTransaccionRepository.findTipoTransacionByEstadoTrue());
		return tipoTransaciones;
	}

	@Override
	public TipoTransacionDTO find(int id) {
		TipoTransacion result = tipoTransaccionRepository.findTipoTransacionByIdAndEstadoTrue(id)
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
		TipoTransacion tipoTransacionTransformado = TipoTransacionDTO.tipoTransacionDTOToTipoTransacion(transacionDTO);
		tipoTransacionTransformado.setEstado(true);
		TipoTransacion result = tipoTransaccionRepository.save(Objects.requireNonNull(tipoTransacionTransformado));
		return TipoTransacionDTO.tipoTransacionToTipoTransacionDTO(result);
	}

	@Override
	public TipoTransacionDTO update(TipoTransacionDTO transacionDTO) {
		if(Objects.isNull(transacionDTO.getId())) {
			throw new BadRequestParam("Falta el paremetro id");
		}
		TipoTransacion tipoTransacionTransformado = TipoTransacionDTO.tipoTransacionDTOToTipoTransacion(transacionDTO);
		TipoTransacion result = tipoTransaccionRepository.save(Objects.requireNonNull(tipoTransacionTransformado));
		return TipoTransacionDTO.tipoTransacionToTipoTransacionDTO(result);
	}

	@Override
	public String delete(int id) {
		TipoTransacion u = tipoTransaccionRepository.findTipoTransacionByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("Tipo Transaccion no encontrado "  + id ));
		u.setEstado(false);
		tipoTransaccionRepository.save(u);
		return "Tipo Transaccion eliminado correctamente";
	}

	

	
	
}
