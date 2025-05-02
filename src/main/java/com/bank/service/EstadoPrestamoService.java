package com.bank.service;

import java.util.List;

import com.bank.dto.EstadoPrestamoDTO;

public interface EstadoPrestamoService {
	public List<EstadoPrestamoDTO> listByAll();
	public List<EstadoPrestamoDTO> list();
	public EstadoPrestamoDTO find(int id);
	public EstadoPrestamoDTO findByAll(int id);
	public EstadoPrestamoDTO save(EstadoPrestamoDTO estadoPrestamoDTO);
	public EstadoPrestamoDTO update(EstadoPrestamoDTO estadoPrestamoDTO);
	public String delete(int id);
}
