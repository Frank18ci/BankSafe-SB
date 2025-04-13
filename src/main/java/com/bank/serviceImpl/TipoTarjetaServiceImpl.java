package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dto.TipoTarjetaDTO;
import com.bank.exception.BadRequestParam;
import com.bank.exception.ResourceNotFound;
import com.bank.model.TipoTarjeta;

import com.bank.repository.TipoTarjetaRepository;
import com.bank.service.TipoTarjetaService;

@Service
public class TipoTarjetaServiceImpl implements TipoTarjetaService {

	@Autowired
	private TipoTarjetaRepository tipoTarjetaRepository;
	
	@Override
	public List<TipoTarjetaDTO> list() {
		List<TipoTarjetaDTO> tarjeta = TipoTarjetaDTO.listTipoTarjetaToListTipoTarjetaDTO(tipoTarjetaRepository.findTipoTarjetaByEstado(true));
		return tarjeta;
	}
	@Override
	public List<TipoTarjetaDTO> listByAll() {
		List<TipoTarjetaDTO> tarjeta = TipoTarjetaDTO.listTipoTarjetaToListTipoTarjetaDTO(tipoTarjetaRepository.findAll());
		return tarjeta;
	}

	@Override
	public TipoTarjetaDTO find(int id) {
		TipoTarjeta result = tipoTarjetaRepository.findTipoTarjetaByIdAndEstado(id, true)
				.orElseThrow(() -> new ResourceNotFound("Tipo tarjeta no encontrado "  + id ));
		return TipoTarjetaDTO.tipoTarjetaToTipoTarjetaDTO(result);
	}
	@Override
	public TipoTarjetaDTO findByAll(int id) {
		TipoTarjeta result = tipoTarjetaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Tipo tarjeta no encontrado "  + id ));
		return TipoTarjetaDTO.tipoTarjetaToTipoTarjetaDTO(result);
	}
	
	@Override
	public TipoTarjetaDTO save(TipoTarjetaDTO tipoTarjetaDTO) {
		TipoTarjeta tarjetaTransformado = TipoTarjetaDTO.tipoTarjetaDTOToTipoTarjeta(tipoTarjetaDTO);
		TipoTarjeta result = tipoTarjetaRepository.save(Objects.requireNonNull(tarjetaTransformado));
		return TipoTarjetaDTO.tipoTarjetaToTipoTarjetaDTO(result);
	}

	

	@Override
	public TipoTarjetaDTO update(TipoTarjetaDTO tipoTarjetaDTO) {
		if(Objects.isNull(tipoTarjetaDTO.getId())) {
			throw new BadRequestParam("Falta el paremetro id");
		}
		TipoTarjeta roleUserTransformado = TipoTarjetaDTO.tipoTarjetaDTOToTipoTarjeta(tipoTarjetaDTO);
		TipoTarjeta result = tipoTarjetaRepository.save(Objects.requireNonNull(roleUserTransformado));
		return TipoTarjetaDTO.tipoTarjetaToTipoTarjetaDTO(result);
	}

	@Override
	public String delete(int id) {
		TipoTarjeta result = tipoTarjetaRepository.findTipoTarjetaByIdAndEstado(id, true)
				.orElseThrow(() -> new ResourceNotFound("Tipo tarjeta no encontrado "  + id ));
		result.setEstado(false);
		tipoTarjetaRepository.save(result);
		return "Tipo Tarjeta eliminado correctamente";
	}

}
