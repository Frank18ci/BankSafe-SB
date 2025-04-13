package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dto.PrestamoDTO;
import com.bank.exception.BadRequestParam;
import com.bank.exception.ResourceNotFound;
import com.bank.model.Prestamo;
import com.bank.repository.PrestamoRepository;
import com.bank.service.PrestamoService;
@Service
public class PrestamoServiseImpl implements PrestamoService {
	@Autowired
	private PrestamoRepository prestamoRepository;
	@Override
	public List<PrestamoDTO> list() {
		List<PrestamoDTO> prestamos = PrestamoDTO.listPrestamoToListPrestamoDTO(prestamoRepository.findPrestamoByEstado(true));
		return prestamos;
	}
	@Override
	public List<PrestamoDTO> listByAll() {
		List<PrestamoDTO> prestamos = PrestamoDTO.listPrestamoToListPrestamoDTO(prestamoRepository.findAll());
		return prestamos;
	}


	@Override
	public PrestamoDTO find(int id) {
		Prestamo result = prestamoRepository.findPrestamoByIdAndEstado(id, true)
				.orElseThrow(() -> new ResourceNotFound("Prestamo no encontrado "  + id ));
		return PrestamoDTO.prestamoToPrestamoDTO(result);
	}
	@Override
	public PrestamoDTO findByAll(int id) {
		Prestamo result = prestamoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Prestamo no encontrado "  + id ));
		return PrestamoDTO.prestamoToPrestamoDTO(result);
	}

	@Override
	public PrestamoDTO save(PrestamoDTO prestamoDTO) {
		Prestamo prestamoTransformado = PrestamoDTO.prestamoDTOToPrestamo(prestamoDTO);
		Prestamo result = prestamoRepository.save(Objects.requireNonNull(prestamoTransformado));
		return PrestamoDTO.prestamoToPrestamoDTO(result);
	}

	@Override
	public PrestamoDTO update(PrestamoDTO prestamoDTO) {
		if(Objects.isNull(prestamoDTO.getId())) {
			throw new BadRequestParam("Falta el paremetro id");
		}
		Prestamo prestamoTransformado = PrestamoDTO.prestamoDTOToPrestamo(prestamoDTO);
		Prestamo result = prestamoRepository.save(Objects.requireNonNull(prestamoTransformado));
		return PrestamoDTO.prestamoToPrestamoDTO(result);
	}

	@Override
	public String delete(int id) {
		Prestamo result = prestamoRepository.findPrestamoByIdAndEstado(id, true)
				.orElseThrow(() -> new ResourceNotFound("Prestamo no encontrado "  + id ));
		result.setEstado(false);
		prestamoRepository.save(result);
		return "Prestamo eliminado correctamente";
	}

}
