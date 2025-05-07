package com.bank.service;

import java.util.List;

import com.bank.dto.PagoServicioDTO;

public interface PagoServicioService {
	public List<PagoServicioDTO> listByAll();
	public List<PagoServicioDTO> list();
	public List<PagoServicioDTO> buscarPorCodigoAndEstado(String codigo, String estado);
	public PagoServicioDTO find(int id);
	public PagoServicioDTO findByAll(int id);
	public PagoServicioDTO save(PagoServicioDTO pagoServicioDTO);
	public PagoServicioDTO update(PagoServicioDTO pagoServicioDTO);
	public String delete(int id);
}
