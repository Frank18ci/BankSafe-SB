package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dto.TipoPrestamoDTO;
import com.bank.exception.BadRequestParam;
import com.bank.exception.ResourceNotFound;
import com.bank.model.TipoPrestamo;
import com.bank.repository.TipoPrestamoRepository;
import com.bank.service.TipoPrestamoService;
@Service
public class TipoPrestamoServiceImpl implements TipoPrestamoService {

	@Autowired
	private TipoPrestamoRepository tipoPrestamoRepository;
	
	@Override
	public List<TipoPrestamoDTO> list() {
		List<TipoPrestamoDTO> tipoPrestamos = TipoPrestamoDTO.listTipoPretamoToListTipoPrestamoDTO(tipoPrestamoRepository.findTipoPrestamoByEstado(true));
		return tipoPrestamos;
	}
	@Override
	public List<TipoPrestamoDTO> listByAll() {
		List<TipoPrestamoDTO> tipoPrestamos = TipoPrestamoDTO.listTipoPretamoToListTipoPrestamoDTO(tipoPrestamoRepository.findAll());
		return tipoPrestamos;
	}

	@Override
	public TipoPrestamoDTO find(int id) {
		TipoPrestamo result = tipoPrestamoRepository.findTipoPrestamoByIdAndEstado(id, true)
				.orElseThrow(() -> new ResourceNotFound("Tipo Prestamo no encontrado "  + id ));
		return TipoPrestamoDTO.tipoPrestamoToTipoPrestamoDTO(result);
	}
	@Override
	public TipoPrestamoDTO findByAll(int id) {
		TipoPrestamo result = tipoPrestamoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Tipo Prestamo no encontrado "  + id ));
		return TipoPrestamoDTO.tipoPrestamoToTipoPrestamoDTO(result);
	}
	
	@Override
	public TipoPrestamoDTO save(TipoPrestamoDTO tipoPrestamoDTO) {
		TipoPrestamo tipoPrestamoTransformado = TipoPrestamoDTO.tipoPrestamoDTOToTipoPrestamo(tipoPrestamoDTO);
		tipoPrestamoTransformado.setEstado(true);
		TipoPrestamo result = tipoPrestamoRepository.save(Objects.requireNonNull(tipoPrestamoTransformado));
		return TipoPrestamoDTO.tipoPrestamoToTipoPrestamoDTO(result);
	}

	

	@Override
	public TipoPrestamoDTO update(TipoPrestamoDTO tipoPrestamoDTO) {
		if(Objects.isNull(tipoPrestamoDTO.getId())) {
			throw new BadRequestParam("Falta el paremetro id");
		}
		TipoPrestamo tipoPrestamoTransformado = TipoPrestamoDTO.tipoPrestamoDTOToTipoPrestamo(tipoPrestamoDTO);
		TipoPrestamo result = tipoPrestamoRepository.save(Objects.requireNonNull(tipoPrestamoTransformado));
		return TipoPrestamoDTO.tipoPrestamoToTipoPrestamoDTO(result);
	}

	@Override
	public String delete(int id) {
		TipoPrestamo result = tipoPrestamoRepository.findTipoPrestamoByIdAndEstado(id, true)
				.orElseThrow(() -> new ResourceNotFound("Tipo Prestamo no encontrado "  + id ));
		result.setEstado(false);
		tipoPrestamoRepository.save(result);
		return "Tipo Prestamo eliminado correctamente";
	}

}
