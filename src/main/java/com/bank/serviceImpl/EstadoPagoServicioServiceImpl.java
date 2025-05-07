package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.bank.dto.EstadoPagoServicioDTO;
import com.bank.exception.BadRequestParam;
import com.bank.exception.ResourceNotFound;
import com.bank.model.EstadoPagoServicio;
import com.bank.repository.EstadoPagoServicioRepository;
import com.bank.service.EstadoPagoServicioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstadoPagoServicioServiceImpl implements EstadoPagoServicioService{
	private final EstadoPagoServicioRepository estadoPagoServicioRepository;
	
	@Override
	public List<EstadoPagoServicioDTO> list() {
		return EstadoPagoServicioDTO.listaEstadoPagoServicioToListaEstadoPagoServicioDTO(
				estadoPagoServicioRepository.findByEstadoTrue());
	}
	@Override
	public List<EstadoPagoServicioDTO> listByAll() {
		return EstadoPagoServicioDTO.listaEstadoPagoServicioToListaEstadoPagoServicioDTO(
				estadoPagoServicioRepository.findAll());
	}
	@Override
	public EstadoPagoServicioDTO find(int id) {
		return EstadoPagoServicioDTO.estadoPagoServicioToEstadoPagoServicioDTO(
				estadoPagoServicioRepository.findByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("Estado pago servicio no encontrado "  + id )));
	}
	@Override
	public EstadoPagoServicioDTO findByAll(int id) {
		return EstadoPagoServicioDTO.estadoPagoServicioToEstadoPagoServicioDTO(
				estadoPagoServicioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Estado pago servicio no encontrado "  + id )));
	}
	@Override
	public EstadoPagoServicioDTO save(EstadoPagoServicioDTO estadoPagoServicioDTO) {
		EstadoPagoServicio estadoPagoServicioTransformado = EstadoPagoServicioDTO.estadoPagoServicioDTOToEstadoPagoServicio(estadoPagoServicioDTO);
		estadoPagoServicioTransformado.setEstado(true);
		EstadoPagoServicio result = estadoPagoServicioRepository.save(estadoPagoServicioTransformado);
		return EstadoPagoServicioDTO.estadoPagoServicioToEstadoPagoServicioDTO(result);
	}
	@Override
	public EstadoPagoServicioDTO update(EstadoPagoServicioDTO estadoPagoServicioDTO) {
		if(Objects.isNull(estadoPagoServicioDTO.getId())) {
			throw new BadRequestParam("Falta el paremetro id");
		}
		EstadoPagoServicio estadoPagoServicioTransformado = EstadoPagoServicioDTO.estadoPagoServicioDTOToEstadoPagoServicio(estadoPagoServicioDTO);
		estadoPagoServicioTransformado.setEstado(true);
		EstadoPagoServicio result = estadoPagoServicioRepository.save(estadoPagoServicioTransformado);
		return EstadoPagoServicioDTO.estadoPagoServicioToEstadoPagoServicioDTO(result);
	}
	@Override
	public String delete(int id) {
		EstadoPagoServicio result = estadoPagoServicioRepository.findByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("Estado pago servicio no encontrado "  + id ));
		result.setEstado(false);
		estadoPagoServicioRepository.save(result);
		return "Estado pago servicio eliminado";
	}
}
