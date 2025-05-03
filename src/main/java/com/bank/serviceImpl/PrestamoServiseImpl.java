package com.bank.serviceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
import com.bank.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrestamoServiseImpl implements PrestamoService {
	@Autowired
	private PrestamoRepository prestamoRepository;
	private final UserService userService;

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
		BigDecimal montoPrestamo = BigDecimal
				.valueOf(prestamoDTO.getMonto() * Math.pow(tasaInteresEfectiva, prestamoDTO.getPlazos()))
				.setScale(2, RoundingMode.HALF_UP);
		prestamoDTO.setMontoPrestamo(montoPrestamo.doubleValue());

		BigDecimal montoPorPlazo = montoPrestamo.divide(BigDecimal.valueOf(prestamoDTO.getPlazos()), 2,
				RoundingMode.HALF_UP);
		prestamoDTO.setMontoPorPlazo(montoPorPlazo.doubleValue());

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
		prestamoDTO = calcularPrestamo(prestamoDTO);
		prestamoDTO.setUser(userService.find(prestamoDTO.getId()));
		Prestamo prestamoTransformado = PrestamoDTO.prestamoDTOToPrestamo(prestamoDTO);
		prestamoTransformado.setEstado(true);
		Prestamo result = prestamoRepository.save(Objects.requireNonNull(prestamoTransformado));
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

}
