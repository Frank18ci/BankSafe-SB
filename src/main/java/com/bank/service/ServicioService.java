package com.bank.service;

import java.util.List;

import com.bank.dto.ServicioDTO;

public interface ServicioService {
	public List<ServicioDTO> listByAll();
	public List<ServicioDTO> list();
	public ServicioDTO find(int id);
	public ServicioDTO findByAll(int id);
	public ServicioDTO save(ServicioDTO servicioDTO);
	public ServicioDTO update(ServicioDTO servicioDTO);
	public String delete(int id);
}
