package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.bank.dto.EstadoPrestamoDTO;
import com.bank.exception.BadRequestParam;
import com.bank.exception.ResourceNotFound;
import com.bank.model.EstadoPrestamo;
import com.bank.repository.EstadoPrestamoRepository;
import com.bank.service.EstadoPrestamoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstadoPrestamoServiceImpl implements EstadoPrestamoService{
	private final EstadoPrestamoRepository estadoPrestamoRepository;
	
	@Override
	public List<EstadoPrestamoDTO> list() {
		return EstadoPrestamoDTO
				.listEstadoPrestamoToListEstadoPrestamoDTO(
						estadoPrestamoRepository
						.findByEstadoTrue());
	}
	@Override
	public List<EstadoPrestamoDTO> listByAll() {
		return EstadoPrestamoDTO
				.listEstadoPrestamoToListEstadoPrestamoDTO(
						estadoPrestamoRepository.findAll());
	}
	@Override
	public EstadoPrestamoDTO find(int id) {
		return EstadoPrestamoDTO.estadoPrestamoToEstadoPrestamoDTO(
				estadoPrestamoRepository.findByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("Estado Prestamo no encontrado "  + id )));
	}
	@Override
	public EstadoPrestamoDTO findByAll(int id) {
		return EstadoPrestamoDTO.estadoPrestamoToEstadoPrestamoDTO(
				estadoPrestamoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Estado Prestamo no encontrado "  + id )));
	}
	@Override
	public EstadoPrestamoDTO save(EstadoPrestamoDTO estadoPrestamoDTO) {
		EstadoPrestamo estadoPrestamoTransformado = EstadoPrestamoDTO.estadoPrestamoDTOToEstadoPrestamo(estadoPrestamoDTO);
		estadoPrestamoTransformado.setEstado(true);
		EstadoPrestamo result = estadoPrestamoRepository.save(estadoPrestamoTransformado);
		return EstadoPrestamoDTO.estadoPrestamoToEstadoPrestamoDTO(result);
	}
	@Override
	public EstadoPrestamoDTO update(EstadoPrestamoDTO estadoPrestamoDTO) {
		if(Objects.isNull(estadoPrestamoDTO.getId())) {
			throw new BadRequestParam("Falta el paremetro id");
		}
		EstadoPrestamo estadoPrestamoTransformado = EstadoPrestamoDTO.estadoPrestamoDTOToEstadoPrestamo(estadoPrestamoDTO);
		estadoPrestamoTransformado.setEstado(true);
		EstadoPrestamo result = estadoPrestamoRepository.save(estadoPrestamoTransformado);
		return EstadoPrestamoDTO.estadoPrestamoToEstadoPrestamoDTO(result);
	}
	@Override
	public String delete(int id) {
		EstadoPrestamo result = estadoPrestamoRepository.findByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("Estado Prestamo no encontrado "  + id ));
		result.setEstado(false);
		estadoPrestamoRepository.save(result);
		return "Estado prestamo eliminado";
	}
	
}
