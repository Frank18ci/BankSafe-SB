package com.bank.service;

import java.util.List;

import com.bank.dto.TipoPrestamoDTO;

public interface TipoPrestamoService {
	public List<TipoPrestamoDTO> list();
	public List<TipoPrestamoDTO> listByAll();
	public TipoPrestamoDTO save(TipoPrestamoDTO tipoPrestamoDTO);
	public TipoPrestamoDTO find(int id);
	public TipoPrestamoDTO findByAll(int id);
	public TipoPrestamoDTO update(TipoPrestamoDTO tipoPrestamoDTO);
	public String delete(int id);
}
