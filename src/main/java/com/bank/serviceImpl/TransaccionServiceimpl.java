package com.bank.serviceImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.bank.dto.TarjetaDTO;
import com.bank.dto.TipoTransacionDTO;
import com.bank.dto.TransaccionConversionMonedaDTO;
import com.bank.dto.TransacionDTO;
import com.bank.exception.BadRequestParam;
import com.bank.exception.ResourceNotFound;
import com.bank.model.Tarjeta;
import com.bank.model.TipoTransacion;
import com.bank.model.Transacion;
import com.bank.repository.TransacionRepository;
import com.bank.service.TarjetaService;
import com.bank.service.TipoTransacionService;
import com.bank.service.TransaccionService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class TransaccionServiceimpl implements TransaccionService {
	
	private final TransacionRepository transaccionRepository;
	
	private final TarjetaService tarjetaService;
	private final TipoTransacionService tipoTransacionService;
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
		Tarjeta tarjetaE = TarjetaDTO.tarjetaDTOTotarjeta(tarjetaService.find(transacionDTO.getTarjetaOrigen().getId()));
		Tarjeta tarjetaD = TarjetaDTO.tarjetaDTOTotarjeta(tarjetaService.find(transacionDTO.getTarjetaDestino().getId()));
		double montoDisminuido = tarjetaE.getMonto() - Double.parseDouble(transacionDTO.getMonto().toString());
		if(montoDisminuido < 0)
			throw new BadRequestParam("No cuentas con el dinero suficiente");
		tarjetaE.setMonto(montoDisminuido);
		tarjetaD.setMonto(tarjetaD.getMonto() + Double.parseDouble(transacionDTO.getMonto().toString()));
		tarjetaService.update(TarjetaDTO.tarjetaToTarjetaDTO(tarjetaE));
		tarjetaService.update(TarjetaDTO.tarjetaToTarjetaDTO(tarjetaD));
		Transacion transacionTransformado = Transacion.builder()
				.monto(transacionDTO.getMonto())
				.tarjetaOrigen(tarjetaE)
				.tarjetaDestino(tarjetaD)
				.fecha(new Date())
				.build();
		transacionTransformado.setTipoTransacion(TipoTransacionDTO.tipoTransacionDTOToTipoTransacion(tipoTransacionService.find(1)));
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
		double montoDisminuido = tarjetaO.getMonto() - Double.parseDouble(transaccionConversionMonedaDTO.getMontoTarjetaOrigen().toString());
		if(montoDisminuido < 0)
			throw new BadRequestParam("No cuentas con el dinero suficiente");
		tarjetaO.setMonto(montoDisminuido);
		
		TarjetaDTO tarjetaD = tarjetaService.find(transaccionConversionMonedaDTO.getTarjetaDestino().getId());
		tarjetaD.setMonto(tarjetaD.getMonto() + Double.parseDouble(transaccionConversionMonedaDTO.getMontoTarjetaDestino().toString()));
		tarjetaService.save(tarjetaO);
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
	
	@Override
	public List<TransacionDTO> listByFechaBetweenAndTarjetaOrigen_numeroTarjeta(Date fechaI, Date fechaF,
			String numeroTarjeta) {
		return TransacionDTO.listTransacionToListTransacionDTO(
				transaccionRepository.findTransacionByFechaBetweenAndTarjetaOrigen_numeroTarjetaAndEstadoTrue(fechaI, fechaF, numeroTarjeta));
	}
	@Override
	public List<TransacionDTO> listByTarjetaOrigen_numeroTarjetaActualMes(String numeroTarjeta) {
		Calendar calInicio = Calendar.getInstance();
		calInicio.set(Calendar.DAY_OF_MONTH, 1);
		calInicio.set(Calendar.HOUR_OF_DAY, 0);
		calInicio.set(Calendar.MINUTE, 0);
		calInicio.set(Calendar.SECOND, 0);
		calInicio.set(Calendar.MILLISECOND, 0);
		Date fechaMesInicio = calInicio.getTime();
		
		Calendar calFin = Calendar.getInstance();
		calFin.set(Calendar.DAY_OF_MONTH, calFin.getActualMaximum(Calendar.DAY_OF_MONTH));
		calInicio.add(Calendar.MONTH, -1);
		calFin.set(Calendar.HOUR_OF_DAY, 23);
		calFin.set(Calendar.MINUTE, 59);
		calFin.set(Calendar.SECOND, 59);
		calFin.set(Calendar.MILLISECOND, 999);
		Date fechaMesFin = calFin.getTime();
		
		
		return TransacionDTO.listTransacionToListTransacionDTO(
				transaccionRepository.findTransacionByFechaBetweenAndTarjetaOrigen_numeroTarjetaAndEstadoTrue(fechaMesInicio, fechaMesFin, numeroTarjeta));
	}
	@Override
	public List<TransacionDTO> listByTarjetaOrigen_numeroTarjetaUltimoMes(String numeroTarjeta) {
		Calendar calInicio = Calendar.getInstance();
		calInicio.add(Calendar.MONTH, -1);
		calInicio.set(Calendar.DAY_OF_MONTH, 1);
		calInicio.set(Calendar.HOUR_OF_DAY, 0);
		calInicio.set(Calendar.MINUTE, 0);
		calInicio.set(Calendar.SECOND, 0);
		calInicio.set(Calendar.MILLISECOND, 0);
		Date fechaMesInicio = calInicio.getTime();
		
		Calendar calFin = Calendar.getInstance();
		calFin.set(Calendar.DAY_OF_MONTH, calFin.getActualMaximum(Calendar.DAY_OF_MONTH));
		calFin.add(Calendar.MONTH, -1);
		calFin.set(Calendar.HOUR_OF_DAY, 23);
		calFin.set(Calendar.MINUTE, 59);
		calFin.set(Calendar.SECOND, 59);
		calFin.set(Calendar.MILLISECOND, 999);
		Date fechaMesFin = calFin.getTime();
		
		return TransacionDTO.listTransacionToListTransacionDTO(
				transaccionRepository.findTransacionByFechaBetweenAndTarjetaOrigen_numeroTarjetaAndEstadoTrue(fechaMesInicio, fechaMesFin, numeroTarjeta));
	}
	

}
