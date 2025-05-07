package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.bank.dto.ServicioDTO;
import com.bank.exception.BadRequestParam;
import com.bank.exception.ResourceNotFound;
import com.bank.model.Servicio;
import com.bank.repository.ServicioRepository;
import com.bank.service.ServicioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicioServiceImpl implements ServicioService{
	private final ServicioRepository servicioRepository;

	@Override
	public List<ServicioDTO> list() {
		return ServicioDTO.listaServicioToListaServicioDTO(
				servicioRepository.findByEstadoTrue());
	}
	@Override
	public List<ServicioDTO> listByAll() {
		return ServicioDTO.listaServicioToListaServicioDTO(
				servicioRepository.findAll());
	}
	@Override
	public ServicioDTO find(int id) {
		return ServicioDTO.servicioToServicioDTO(
				servicioRepository.findByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("Servicio no encontrado "  + id )));
	}
	@Override
	public ServicioDTO findByAll(int id) {
		return ServicioDTO.servicioToServicioDTO(
				servicioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Servicio no encontrado "  + id )));
	}
	@Override
	public ServicioDTO save(ServicioDTO servicioDTO) {
		Servicio servicioTransformado = ServicioDTO.servicioDTOToServicio(servicioDTO);
		servicioTransformado.setEstado(true);
		Servicio result = servicioRepository.save(servicioTransformado);
		return ServicioDTO.servicioToServicioDTO(result);
	}
	@Override
	public ServicioDTO update(ServicioDTO servicioDTO) {
		if(Objects.isNull(servicioDTO.getId())) {
			throw new BadRequestParam("Falta el paremetro id");
		}
		Servicio servicioTransformado = ServicioDTO.servicioDTOToServicio(servicioDTO);
		servicioTransformado.setEstado(true);
		Servicio result = servicioRepository.save(servicioTransformado);
		return ServicioDTO.servicioToServicioDTO(result);
	}
	@Override
	public String delete(int id) {
		Servicio result = servicioRepository.findByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("Servicio no encontrado "  + id ));
		result.setEstado(false);
		servicioRepository.save(result);
		return "Servicio eliminado";
	}
	
}
