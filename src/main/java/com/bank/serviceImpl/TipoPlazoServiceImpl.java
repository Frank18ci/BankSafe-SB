package com.bank.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dto.TipoPlazoDTO;
import com.bank.exception.BadRequestParam;
import com.bank.exception.ResourceNotFound;
import com.bank.model.TipoPlazo;
import com.bank.repository.TipoPlazoRepository;
import com.bank.service.TipoPlazoService;
@Service
public class TipoPlazoServiceImpl implements TipoPlazoService {

	@Autowired
	private TipoPlazoRepository tipoPrestamoRepository;
	
	@Override
	public List<TipoPlazoDTO> list() {
		List<TipoPlazoDTO> tipoPrestamos = TipoPlazoDTO
				.listTipoPretamoToListTipoPrestamoDTO(tipoPrestamoRepository.findTipoPlazoByEstadoTrue());
		return tipoPrestamos;
	}
	@Override
	public List<TipoPlazoDTO> listByAll() {
		List<TipoPlazoDTO> tipoPrestamos = TipoPlazoDTO.listTipoPretamoToListTipoPrestamoDTO(tipoPrestamoRepository.findAll());
		return tipoPrestamos;
	}

	@Override
	public TipoPlazoDTO find(int id) {
		TipoPlazo result = tipoPrestamoRepository.findTipoPlazoByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("Tipo Prestamo no encontrado "  + id ));
		return TipoPlazoDTO.tipoPlazoToTipoPlazoDTO(result);
	}
	@Override
	public TipoPlazoDTO findByAll(int id) {
		TipoPlazo result = tipoPrestamoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Tipo Prestamo no encontrado "  + id ));
		return TipoPlazoDTO.tipoPlazoToTipoPlazoDTO(result);
	}
	
	@Override
	public TipoPlazoDTO save(TipoPlazoDTO tipoPrestamoDTO) {
		TipoPlazo tipoPrestamoTransformado = TipoPlazoDTO.tipoPlazoDTOToTipoPlazo(tipoPrestamoDTO);
		tipoPrestamoTransformado.setEstado(true);
		TipoPlazo result = tipoPrestamoRepository.save(Objects.requireNonNull(tipoPrestamoTransformado));
		return TipoPlazoDTO.tipoPlazoToTipoPlazoDTO(result);
	}

	

	@Override
	public TipoPlazoDTO update(TipoPlazoDTO tipoPrestamoDTO) {
		if(Objects.isNull(tipoPrestamoDTO.getId())) {
			throw new BadRequestParam("Falta el paremetro id");
		}
		TipoPlazo tipoPrestamoTransformado = TipoPlazoDTO.tipoPlazoDTOToTipoPlazo(tipoPrestamoDTO);
		TipoPlazo result = tipoPrestamoRepository.save(Objects.requireNonNull(tipoPrestamoTransformado));
		return TipoPlazoDTO.tipoPlazoToTipoPlazoDTO(result);
	}

	@Override
	public String delete(int id) {
		TipoPlazo result = tipoPrestamoRepository.findTipoPlazoByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("Tipo Prestamo no encontrado "  + id ));
		result.setEstado(false);
		tipoPrestamoRepository.save(result);
		return "Tipo Prestamo eliminado correctamente";
	}

}
