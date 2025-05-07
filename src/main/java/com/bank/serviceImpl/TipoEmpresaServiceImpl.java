package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.bank.dto.TipoEmpresaDTO;
import com.bank.exception.BadRequestParam;
import com.bank.exception.ResourceNotFound;
import com.bank.model.TipoEmpresa;
import com.bank.repository.TipoEmpresaRepository;
import com.bank.service.TipoEmpresaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipoEmpresaServiceImpl implements TipoEmpresaService{
	private final TipoEmpresaRepository tipoEmpresaRepository;
	
	@Override
	public List<TipoEmpresaDTO> list() {
		return TipoEmpresaDTO.listTipoEmpresaToListTipoEmpresaDTO(
				tipoEmpresaRepository.findByEstadoTrue());
	}
	@Override
	public List<TipoEmpresaDTO> listByAll() {
		return TipoEmpresaDTO.listTipoEmpresaToListTipoEmpresaDTO(
				tipoEmpresaRepository.findAll());
	}
	@Override
	public TipoEmpresaDTO find(int id) {
		return TipoEmpresaDTO.tipoEmpresaToTipoEmpresaDTO(
				tipoEmpresaRepository.findByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("Tipo empresa no encontrado "  + id )));
	}
	@Override
	public TipoEmpresaDTO findByAll(int id) {
		return TipoEmpresaDTO.tipoEmpresaToTipoEmpresaDTO(
				tipoEmpresaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Tipo empresa no encontrado "  + id )));
	}
	@Override
	public TipoEmpresaDTO save(TipoEmpresaDTO tipoEmpresaDTO) {
		TipoEmpresa tipoEmpresaTransformado = TipoEmpresaDTO.tipoEmpresaDTOToTipoEmpresa(tipoEmpresaDTO);
		tipoEmpresaTransformado.setEstado(true);
		TipoEmpresa result = tipoEmpresaRepository.save(tipoEmpresaTransformado);
		return TipoEmpresaDTO.tipoEmpresaToTipoEmpresaDTO(result);
	}
	@Override
	public TipoEmpresaDTO update(TipoEmpresaDTO tipoEmpresaDTO) {
		if(Objects.isNull(tipoEmpresaDTO.getId())) {
			throw new BadRequestParam("Falta el paremetro id");
		}
		TipoEmpresa tipoEmpresaTransformado = TipoEmpresaDTO.tipoEmpresaDTOToTipoEmpresa(tipoEmpresaDTO);
		tipoEmpresaTransformado.setEstado(true);
		TipoEmpresa result = tipoEmpresaRepository.save(tipoEmpresaTransformado);
		return TipoEmpresaDTO.tipoEmpresaToTipoEmpresaDTO(result);
	}
	@Override
	public String delete(int id) {
		TipoEmpresa result = tipoEmpresaRepository.findByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("Tipo empresa no encontrado "  + id ));
		result.setEstado(false);
		tipoEmpresaRepository.save(result);
		return "Tipo empresa eliminado";
	}
}
