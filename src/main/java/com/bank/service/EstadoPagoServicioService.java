package com.bank.service;

import java.util.List;

import com.bank.dto.EstadoPagoServicioDTO;

public interface EstadoPagoServicioService {
	public List<EstadoPagoServicioDTO> listByAll();
	public List<EstadoPagoServicioDTO> list();
	public EstadoPagoServicioDTO find(int id);
	public EstadoPagoServicioDTO findByAll(int id);
	public EstadoPagoServicioDTO save(EstadoPagoServicioDTO estadoPagoServicioDTO);
	public EstadoPagoServicioDTO update(EstadoPagoServicioDTO estadoPagoServicioDTO);
	public String delete(int id);
}
