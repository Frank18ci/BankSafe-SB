package com.bank.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dto.TarjetaDTO;
import com.bank.dto.TransaccionConversionMonedaDTO;
import com.bank.dto.TransacionDTO;
import com.bank.exception.BadRequestParam;
import com.bank.exception.ResourceNotFound;
import com.bank.model.Tarjeta;
import com.bank.model.TipoTransacion;
import com.bank.model.Transacion;
import com.bank.repository.TransacionRepository;
import com.bank.service.TarjetaService;
import com.bank.service.TransaccionService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class TransaccionServiceimpl implements TransaccionService {
	
	private final TransacionRepository transaccionRepository;
	
	private final TarjetaService tarjetaService;
	@Override
	public List<TransacionDTO> listByAll() {
		List<TransacionDTO> transaciones = TransacionDTO.listTransacionToListTransacionDTO(transaccionRepository.findAll());		
		return transaciones;
	}

	@Override
	public List<TransacionDTO> list() {
		List<TransacionDTO> transaciones = TransacionDTO.listTransacionToListTransacionDTO(transaccionRepository.findTransacionByEstadoTrue());		
		return transaciones;
	}

	@Override
	public TransacionDTO find(int id) {
		Transacion result = transaccionRepository.findTransacByIdAndEstadoTrue(id)
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
		Transacion u = transaccionRepository.findTransacByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("Transacion no encontrado "  + id ));
		u.setEstado(false);
		transaccionRepository.save(u);
		return "Transacion eliminada correctamente";
	}
	@Override
	public TransacionDTO realizarConversionyTransferencia(TransaccionConversionMonedaDTO transaccionConversionMonedaDTO) {
		TarjetaDTO tarjetaO = tarjetaService.find(transaccionConversionMonedaDTO.getTarjetaOrigen().getId());
		tarjetaO.setMonto(tarjetaO.getMonto() - Double.parseDouble(transaccionConversionMonedaDTO.getMontoTarjetaOrigen().toString()));
		tarjetaService.save(tarjetaO);
		TarjetaDTO tarjetaD = tarjetaService.find(transaccionConversionMonedaDTO.getTarjetaDestino().getId());
		tarjetaD.setMonto(tarjetaD.getMonto() + Double.parseDouble(transaccionConversionMonedaDTO.getMontoTarjetaDestino().toString()));
		tarjetaService.save(tarjetaD);
		
		Transacion t = Transacion.builder()
				.monto(transaccionConversionMonedaDTO.getMontoTarjetaDestino())
				.fecha(new Date())
				.estado(true)
				.tipoTransacion(TipoTransacion.builder().id(1).build())
				.tarjetaOrigen(TarjetaDTO.tarjetaDTOTotarjeta(tarjetaO))
				.tarjetaDestino(TarjetaDTO.tarjetaDTOTotarjeta(tarjetaD))
				.build();
		Transacion ts = transaccionRepository.save(t);
		return TransacionDTO.transacionToTransacionDTO(ts);
	}
	

}
