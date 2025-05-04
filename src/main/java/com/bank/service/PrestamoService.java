package com.bank.service;

import java.util.List;

import com.bank.dto.PrestamoDTO;

public interface PrestamoService {
	public List<PrestamoDTO> listByAll();
	public List<PrestamoDTO> list();
	public PrestamoDTO find(int id);
	public PrestamoDTO findByAll(int id);
	public PrestamoDTO save(PrestamoDTO prestamoDTO);
	public PrestamoDTO update(PrestamoDTO prestamoDTO);
	public PrestamoDTO calcularPrestamo(PrestamoDTO prestamoDTO);
	public PrestamoDTO realizarPago(int id);
	public String delete(int id);
	public List<PrestamoDTO> findByUsuarioId(int idUsuario);
}
