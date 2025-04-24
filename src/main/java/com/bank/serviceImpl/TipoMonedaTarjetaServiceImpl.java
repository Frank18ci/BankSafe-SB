package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dto.TipoMonedaTarjetaDTO;
import com.bank.exception.BadRequestParam;
import com.bank.exception.ResourceNotFound;
import com.bank.model.TipoMonedaTarjeta;
import com.bank.repository.TipoMonedaTarjetaRepository;
import com.bank.service.TipoMonedaTarjetaService;

@Service
public class TipoMonedaTarjetaServiceImpl implements TipoMonedaTarjetaService {
	
	@Autowired
	private TipoMonedaTarjetaRepository tipoMonedaTarjetaRepository;
	
	@Override
	public List<TipoMonedaTarjetaDTO> list() {
		List<TipoMonedaTarjetaDTO> tipoMonedas = TipoMonedaTarjetaDTO
				.listTipoMonedaTarjetaToListTipoMonedaTarjetaDTO(tipoMonedaTarjetaRepository.findTipoMonedaTarjetaByEstadoTrue());
		return tipoMonedas;
	}
	@Override
	public List<TipoMonedaTarjetaDTO> listByAll() {
		List<TipoMonedaTarjetaDTO> tipoMonedas = TipoMonedaTarjetaDTO
				.listTipoMonedaTarjetaToListTipoMonedaTarjetaDTO(tipoMonedaTarjetaRepository.findAll());
		return tipoMonedas;
	}

	@Override
	public TipoMonedaTarjetaDTO find(int id) {
		TipoMonedaTarjeta result = tipoMonedaTarjetaRepository.findTipoMonedaTarjetaByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("Tipo Moneda Tarjeta no encontrado "  + id ));
		return TipoMonedaTarjetaDTO.tipoMonedaTarjetaToTipoMonedaTarjetaDTO(result);
	}
	@Override
	public TipoMonedaTarjetaDTO findByAll(int id) {
		TipoMonedaTarjeta result = tipoMonedaTarjetaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Tipo Moneda Tarjeta no encontrado "  + id ));
		return TipoMonedaTarjetaDTO.tipoMonedaTarjetaToTipoMonedaTarjetaDTO(result);
	}
	
	@Override
	public TipoMonedaTarjetaDTO save(TipoMonedaTarjetaDTO tipoMonedaTarjetaDTO) {
		
		TipoMonedaTarjeta tipoMonedaTarjetaTransformado = TipoMonedaTarjetaDTO.tipoMonedaTarjetaDTOToTipoMonedaTarjeta(tipoMonedaTarjetaDTO);
		tipoMonedaTarjetaTransformado.setEstado(true);
		TipoMonedaTarjeta result = tipoMonedaTarjetaRepository.save(Objects.requireNonNull(tipoMonedaTarjetaTransformado));
		return TipoMonedaTarjetaDTO.tipoMonedaTarjetaToTipoMonedaTarjetaDTO(result);
	}


	@Override
	public TipoMonedaTarjetaDTO update(TipoMonedaTarjetaDTO tipoMonedaTarjetaDTO) {
		if(Objects.isNull(tipoMonedaTarjetaDTO.getId())) {
			throw new BadRequestParam("Falta el paremetro id");
		}
		TipoMonedaTarjeta tipoMonedaTarjetaTransformado = TipoMonedaTarjetaDTO.tipoMonedaTarjetaDTOToTipoMonedaTarjeta(tipoMonedaTarjetaDTO);
		TipoMonedaTarjeta result = tipoMonedaTarjetaRepository.save(Objects.requireNonNull(tipoMonedaTarjetaTransformado));
		return TipoMonedaTarjetaDTO.tipoMonedaTarjetaToTipoMonedaTarjetaDTO(result);
	}

	@Override
	public String delete(int id) {
		TipoMonedaTarjeta result = tipoMonedaTarjetaRepository.findTipoMonedaTarjetaByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("Tipo Moneda Tarjeta no encontrado "  + id ));
		result.setEstado(false);
		tipoMonedaTarjetaRepository.save(result);
		return "Tipo Moneda Tarjeta eliminado correctamente";
	}

}
