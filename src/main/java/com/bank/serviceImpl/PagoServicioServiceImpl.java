package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.bank.dto.PagoServicioDTO;
import com.bank.dto.TarjetaDTO;
import com.bank.exception.BadRequestParam;
import com.bank.exception.ResourceNotFound;
import com.bank.model.EstadoPagoServicio;
import com.bank.model.PagoServicio;
import com.bank.repository.PagoServicioRepository;
import com.bank.service.PagoServicioService;
import com.bank.service.TarjetaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagoServicioServiceImpl implements PagoServicioService{
	private final PagoServicioRepository pagoServicioRepository;
	private final TarjetaService tarjetaService;
	
	@Override
	public List<PagoServicioDTO> list() {
		return PagoServicioDTO.listaPagoServicioToListaPagoServicioDTO(
					pagoServicioRepository.findByEstadoTrue());
	}
	@Override
	public List<PagoServicioDTO> listByAll() {
		return PagoServicioDTO.listaPagoServicioToListaPagoServicioDTO(
				pagoServicioRepository.findAll());
	}
	@Override
	public PagoServicioDTO find(int id) {
		return PagoServicioDTO.pagoServicioToPagoServicioDTO(
				pagoServicioRepository.findByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("Pago Servicio no encontrado "  + id )));
	}
	@Override
	public PagoServicioDTO findByAll(int id) {
		return PagoServicioDTO.pagoServicioToPagoServicioDTO(
				pagoServicioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Pago Servicio no encontrado "  + id )));
	}
	@Override
	public PagoServicioDTO save(PagoServicioDTO pagoServicioDTO) {
		PagoServicio pagoServicioTransformado = PagoServicioDTO.pagoServicioDTOToPagoServicio(pagoServicioDTO);
		pagoServicioTransformado.setEstado(true);
		PagoServicio result = pagoServicioRepository.save(pagoServicioTransformado);
		return PagoServicioDTO.pagoServicioToPagoServicioDTO(result);
	}
	@Override
	public PagoServicioDTO realizarPago(PagoServicioDTO pagoServicio, int idTarjeta) {
		PagoServicio pagoServicioEncontrado = PagoServicioDTO.pagoServicioDTOToPagoServicio(find(pagoServicio.getId()));
		pagoServicioEncontrado.setEstado(true);
		pagoServicioEncontrado.setEstadoPagoServicio(EstadoPagoServicio.builder().id(1).build());
		
		TarjetaDTO tarjeta = tarjetaService.find(idTarjeta);
		double montoDisminuido = tarjeta.getMonto() - pagoServicioEncontrado.getMontoPago();
		if(montoDisminuido < 0)
			throw new BadRequestParam("No cuentas con el dinero suficiente");
		tarjeta.setMonto(montoDisminuido);
		tarjetaService.save(tarjeta);
		PagoServicio result = pagoServicioRepository.save(pagoServicioEncontrado);
		return PagoServicioDTO.pagoServicioToPagoServicioDTO(result);	
		}
	@Override
	public PagoServicioDTO update(PagoServicioDTO pagoServicioDTO) {
		if(Objects.isNull(pagoServicioDTO.getId())) {
			throw new BadRequestParam("Falta el paremetro id");
		}
		PagoServicio pagoServicioTransformado = PagoServicioDTO.pagoServicioDTOToPagoServicio(pagoServicioDTO);
		pagoServicioTransformado.setEstado(true);
		PagoServicio result = pagoServicioRepository.save(pagoServicioTransformado);
		return PagoServicioDTO.pagoServicioToPagoServicioDTO(result);
	}
	@Override
	public String delete(int id) {
		PagoServicio result = pagoServicioRepository.findByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("Pago Servicio no encontrado "  + id ));
		result.setEstado(false);
		pagoServicioRepository.save(result);
		return "Pago Servicio eliminado";
	}
	@Override
	public List<PagoServicioDTO> buscarPorCodigoAndEstado(String codigo, String estado) {
		return PagoServicioDTO.listaPagoServicioToListaPagoServicioDTO(
				pagoServicioRepository.findByServicio_CodigoAndEstadoPagoServicio_estadoServicioAndEstadoTrue(codigo, estado));
	}
}
