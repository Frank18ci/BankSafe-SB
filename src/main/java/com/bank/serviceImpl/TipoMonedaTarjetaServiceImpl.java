package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.TipoMonedaTarjetaDTO;
import com.bank.dto.UserDTO;
import com.bank.exception.ResourceNotFound;
import com.bank.model.TipoMonedaTarjeta;
import com.bank.model.User;
import com.bank.repository.TipoMonedaTarjetaRepository;
import com.bank.service.TipoMonedaTarjetaService;

@Service
public class TipoMonedaTarjetaServiceImpl implements TipoMonedaTarjetaService {
	
	@Autowired
	private TipoMonedaTarjetaRepository tipoMonedaTarjetaRepository;
	
	private TipoMonedaTarjeta TipoMonedaTarjetaDTOToTipoMonedaTarjeta(TipoMonedaTarjetaDTO tipoMonedaTarjetaDTO) {
		return TipoMonedaTarjeta
				.builder()
				.id(tipoMonedaTarjetaDTO.getId())
				.nombre(tipoMonedaTarjetaDTO.getNombre())
				.simbolo(tipoMonedaTarjetaDTO.getSimbolo())
				.tipo(tipoMonedaTarjetaDTO.getTipo())
				.build();
	}
	private TipoMonedaTarjetaDTO TipoMonedaTarjetaToTipoMonedaTarjetaDTO(TipoMonedaTarjeta tipoMonedaTarjeta) {
		return TipoMonedaTarjetaDTO
				.builder()
				.id(tipoMonedaTarjeta.getId())
				.nombre(tipoMonedaTarjeta.getNombre())
				.simbolo(tipoMonedaTarjeta.getSimbolo())
				.tipo(tipoMonedaTarjeta.getTipo())
				.build();
	}
	@Override
	public List<TipoMonedaTarjetaDTO> list() {
		List<TipoMonedaTarjetaDTO> tipoMoneda = tipoMonedaTarjetaRepository.findAll().stream()
				.map(ue -> TipoMonedaTarjetaToTipoMonedaTarjetaDTO(ue))
				.collect(Collectors.toList());
				
		return tipoMoneda;
	}

	@Override
	public TipoMonedaTarjetaDTO save(TipoMonedaTarjetaDTO tipoMonedaTarjetaDTO) {
		if(Objects.isNull(tipoMonedaTarjetaDTO.getId())) {
			//crear funcion para controlar las excepciones
		}
		TipoMonedaTarjeta userTransformado = TipoMonedaTarjetaDTOToTipoMonedaTarjeta(tipoMonedaTarjetaDTO);
		TipoMonedaTarjeta result = tipoMonedaTarjetaRepository.save(Objects.requireNonNull(userTransformado));
		return TipoMonedaTarjetaToTipoMonedaTarjetaDTO(result);
	}

	@Override
	public TipoMonedaTarjetaDTO find(int id) {
		Optional<TipoMonedaTarjeta> result = tipoMonedaTarjetaRepository.findById(id);
		if(result.isEmpty()) {
			  throw new ResourceNotFound("Usuario no encontrado "  + id );
		}
		TipoMonedaTarjeta u = result.get();
		return TipoMonedaTarjetaToTipoMonedaTarjetaDTO(u);
	}

	@Override
	public TipoMonedaTarjetaDTO update(TipoMonedaTarjetaDTO tipoMonedaTarjetaDTO) {
		if(Objects.isNull(tipoMonedaTarjetaDTO.getId())) {
			//crear funcion para controlar las excepciones
		}
		TipoMonedaTarjeta roleUserTransformado = TipoMonedaTarjetaDTOToTipoMonedaTarjeta(tipoMonedaTarjetaDTO);
		TipoMonedaTarjeta result = tipoMonedaTarjetaRepository.save(Objects.requireNonNull(roleUserTransformado));
		return TipoMonedaTarjetaToTipoMonedaTarjetaDTO(result);
	}

	@Override
	public void delete(int id) {
		tipoMonedaTarjetaRepository.deleteById(id);
	}

}
