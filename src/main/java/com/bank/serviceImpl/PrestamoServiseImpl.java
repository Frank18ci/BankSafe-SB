package com.bank.serviceImpl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dto.PrestamoDTO;
import com.bank.dto.TarjetaDTO;
import com.bank.exception.BadRequestParam;
import com.bank.exception.ResourceNotFound;
import com.bank.model.EstadoPrestamo;
import com.bank.model.Prestamo;
import com.bank.repository.PrestamoRepository;
import com.bank.service.PrestamoService;
import com.bank.service.TarjetaService;
import com.bank.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrestamoServiseImpl implements PrestamoService {
	@Autowired
	private PrestamoRepository prestamoRepository;
	private final UserService userService;
	private final TarjetaService tarjetaService;

	@Override
	public PrestamoDTO calcularPrestamo(PrestamoDTO prestamoDTO) {
		double interes = 0;
		if (prestamoDTO.getMonto() >= 10000 && prestamoDTO.getPlazos() >= 12) {
			interes = 0.10;
		} else {
			interes = 0.14;
		}
		double tasaInteresEfectiva = 1 + (interes / prestamoDTO.getTipoPlazo().getValorAnual());
		prestamoDTO.setInteresAnual(interes);
		double montoPrestamo = prestamoDTO.getMonto() * Math.pow(tasaInteresEfectiva, prestamoDTO.getPlazos()); 
				
		prestamoDTO.setMontoPrestamo(montoPrestamo);

		double montoPorPlazo = montoPrestamo / prestamoDTO.getPlazos();
		prestamoDTO.setMontoPorPlazo(montoPorPlazo);

		LocalDate fechaInicio = prestamoDTO.getFechaInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int mesesPorPlazo = 12 / prestamoDTO.getTipoPlazo().getValorAnual();
		LocalDate fechaFin = fechaInicio.plusMonths(prestamoDTO.getPlazos() * mesesPorPlazo);
		prestamoDTO.setFechaFin(Date.from(fechaFin.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		return prestamoDTO;
	}

	@Override
	public List<PrestamoDTO> list() {
		List<PrestamoDTO> prestamos = PrestamoDTO
				.listPrestamoToListPrestamoDTO(prestamoRepository.findPrestamoByEstadoTrue());
		return prestamos;
	}

	@Override
	public List<PrestamoDTO> listByAll() {
		List<PrestamoDTO> prestamos = PrestamoDTO.listPrestamoToListPrestamoDTO(prestamoRepository.findAll());
		return prestamos;
	}

	@Override
	public PrestamoDTO find(int id) {
		Prestamo result = prestamoRepository.findPrestamoByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("Prestamo no encontrado " + id));
		return PrestamoDTO.prestamoToPrestamoDTO(result);
	}

	@Override
	public List<PrestamoDTO> findByUsuarioId(int idUsuario) {
		return PrestamoDTO
				.listPrestamoToListPrestamoDTO(prestamoRepository.findPrestamoByUser_IdAndEstadoTrue(idUsuario));
	}

	@Override
	public PrestamoDTO findByAll(int id) {
		Prestamo result = prestamoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Prestamo no encontrado " + id));
		return PrestamoDTO.prestamoToPrestamoDTO(result);
	}

	@Override
	public PrestamoDTO save(PrestamoDTO prestamoDTO) {
		prestamoDTO.setUser(userService.find(prestamoDTO.getUser().getId()));
		prestamoDTO = calcularPrestamo(prestamoDTO);
		Prestamo prestamoTransformado = PrestamoDTO.prestamoDTOToPrestamo(prestamoDTO);
		prestamoTransformado.setEstado(true);
		prestamoTransformado.setEstadoPrestamo(EstadoPrestamo.builder().id(1).build());
		Prestamo result = prestamoRepository.save(Objects.requireNonNull(prestamoTransformado));
		
		//Actualizacion de tarjeta
		TarjetaDTO tarjeta = tarjetaService.find(result.getTarjetaRecepcion().getId());
		tarjeta.setMonto(tarjeta.getMonto() + result.getMonto());
		tarjetaService.update(tarjeta);
		
		return PrestamoDTO.prestamoToPrestamoDTO(result);
	}

	@Override
	public PrestamoDTO update(PrestamoDTO prestamoDTO) {
		if (Objects.isNull(prestamoDTO.getId())) {
			throw new BadRequestParam("Falta el paremetro id");
		}
		Prestamo prestamoTransformado = PrestamoDTO.prestamoDTOToPrestamo(prestamoDTO);
		prestamoTransformado.setEstado(true);
		Prestamo result = prestamoRepository.save(Objects.requireNonNull(prestamoTransformado));
		return PrestamoDTO.prestamoToPrestamoDTO(result);
	}

	@Override
	public String delete(int id) {
		Prestamo result = prestamoRepository.findPrestamoByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("Prestamo no encontrado " + id));
		result.setEstado(false);
		prestamoRepository.save(result);
		return "Prestamo eliminado correctamente";
	}
	@Override
	public PrestamoDTO realizarPago(int id) {
		Prestamo result = prestamoRepository.findPrestamoByIdAndEstadoTrue(id)
				.orElseThrow(() -> new ResourceNotFound("Prestamo no encontrado " + id));
		
		TarjetaDTO tarjeta = tarjetaService.find(result.getTarjetaRecepcion().getId());
		tarjeta.setMonto(tarjeta.getMonto() - result.getMontoPorPlazo());
		tarjetaService.update(tarjeta);
		
		result.setMontoPagado(result.getMontoPagado()+ result.getMontoPorPlazo());
		if(result.getMontoPagado() == result.getMontoPrestamo()) {
			result.setEstadoPrestamo(EstadoPrestamo.builder().id(2).build());
		}
		Prestamo resultS = prestamoRepository.save(result);
		
		
		
		return PrestamoDTO.prestamoToPrestamoDTO(resultS);
	}

}
