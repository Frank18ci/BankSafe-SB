package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.bank.dto.EmpresaDTO;
import com.bank.exception.BadRequestParam;
import com.bank.exception.ResourceNotFound;
import com.bank.model.Empresa;
import com.bank.repository.EmpresaRepository;
import com.bank.service.EmpresaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaService{
	private final EmpresaRepository empresaRepository;
	
	@Override
	public List<EmpresaDTO> list() {
		return EmpresaDTO.listaEmpresaToListaEmpresaDTO(
				empresaRepository.findByEstadoTrue());
	}
	@Override
	public List<EmpresaDTO> listByAll() {
		return EmpresaDTO.listaEmpresaToListaEmpresaDTO(
				empresaRepository.findAll());
	}
	@Override
	public List<EmpresaDTO> buscarPorNombreAndipoEmpresaDescripcion(String nombre, String tipoEmpresaDescripcion) {
		return EmpresaDTO.listaEmpresaToListaEmpresaDTO(
				empresaRepository.findByNombreContainingAndTipoEmpresa_descripcionContainingAndEstadoTrue(nombre, tipoEmpresaDescripcion));
	}
	@Override
	public EmpresaDTO find(int id) {
		return EmpresaDTO.empresaToEmpresaDTO(
				empresaRepository.findByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("Empresa no encontrado "  + id )));
	}
	@Override
	public EmpresaDTO findByAll(int id) {
		return EmpresaDTO.empresaToEmpresaDTO(
				empresaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Empresa no encontrado "  + id )));
	}
	@Override
	public EmpresaDTO save(EmpresaDTO empresaDTO) {
		Empresa empresaTransformado = EmpresaDTO.empresaDTOToEmpresa(empresaDTO);
		empresaTransformado.setEstado(true);
		Empresa result = empresaRepository.save(empresaTransformado);
		return EmpresaDTO.empresaToEmpresaDTO(result);
	}
	@Override
	public EmpresaDTO update(EmpresaDTO empresaDTO) {
		if(Objects.isNull(empresaDTO.getId())) {
			throw new BadRequestParam("Falta el paremetro id");
		}
		Empresa empresaTransformado = EmpresaDTO.empresaDTOToEmpresa(empresaDTO);
		empresaTransformado.setEstado(true);
		Empresa result = empresaRepository.save(empresaTransformado);
		return EmpresaDTO.empresaToEmpresaDTO(result);
	}
	@Override
	public String delete(int id) {
		Empresa result = empresaRepository.findByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("Empresa no encontrado "  + id ));
		result.setEstado(false);
		empresaRepository.save(result);
		return "Empresa eliminada";
	}
}
