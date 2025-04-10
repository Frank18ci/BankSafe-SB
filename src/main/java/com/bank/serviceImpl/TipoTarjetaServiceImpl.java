package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dto.TipoTarjetaDTO;
import com.bank.dto.UserDTO;
import com.bank.exception.ResourceNotFound;
import com.bank.model.TipoTarjeta;
import com.bank.model.User;
import com.bank.repository.TipoTarjetaRepository;
import com.bank.service.TipoTarjetaService;

@Service
public class TipoTarjetaServiceImpl implements TipoTarjetaService {

	@Autowired
	private TipoTarjetaRepository tipoTarjetaRepository;
	private TipoTarjetaDTO TipoTarjetaToTipoTarjetaDTO(TipoTarjeta tipoTarjeta)
	{
		return TipoTarjetaDTO
				.builder()
				.id(tipoTarjeta.getId())
				.tipo(tipoTarjeta.getTipo())
				.build();
	}
	private TipoTarjeta TipoTarjetaDTOToTipoTarjeta(TipoTarjetaDTO tipoTarjetaDTO)
	{
		return TipoTarjeta
				.builder()
				.id(tipoTarjetaDTO.getId())
				.tipo(tipoTarjetaDTO.getTipo())
				.build();
	}
	@Override
	public List<TipoTarjetaDTO> list() {
		List<TipoTarjetaDTO> tarjeta = tipoTarjetaRepository.findAll().stream()
				.map(ue -> TipoTarjetaToTipoTarjetaDTO(ue))
				.collect(Collectors.toList());
		return tarjeta;
	}

	@Override
	public TipoTarjetaDTO save(TipoTarjetaDTO tipoTarjetaDTO) {
		if(Objects.isNull(tipoTarjetaDTO.getId())) {
			//crear funcion para controlar las excepciones
		}
		TipoTarjeta tarjetaTransformado = TipoTarjetaDTOToTipoTarjeta(tipoTarjetaDTO);
		TipoTarjeta result = tipoTarjetaRepository.save(Objects.requireNonNull(tarjetaTransformado));
		return TipoTarjetaToTipoTarjetaDTO(result);
	}

	@Override
	public TipoTarjetaDTO find(int id) {
		Optional<TipoTarjeta> result = tipoTarjetaRepository.findById(id);
		if(result.isEmpty()) {
			  throw new ResourceNotFound("Tarjeta no encontrado "  + id );
		}
		TipoTarjeta u = result.get();
		return TipoTarjetaToTipoTarjetaDTO(u);
	}

	@Override
	public TipoTarjetaDTO update(TipoTarjetaDTO tipoTarjetaDTO) {
		if(Objects.isNull(tipoTarjetaDTO.getId())) {
			//crear funcion para controlar las excepciones
		}
		TipoTarjeta roleUserTransformado = TipoTarjetaDTOToTipoTarjeta(tipoTarjetaDTO);
		TipoTarjeta result = tipoTarjetaRepository.save(Objects.requireNonNull(roleUserTransformado));
		return TipoTarjetaToTipoTarjetaDTO(result);
	}

	@Override
	public void delete(int id) {
		tipoTarjetaRepository.deleteById(id);
	}

}
