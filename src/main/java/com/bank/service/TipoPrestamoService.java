package com.bank.service;

import java.util.List;

import com.bank.dto.TipoPrestamoDTO;

public interface TipoPrestamoService {
	public List<TipoPrestamoDTO> list();
	public TipoPrestamoDTO save(TipoPrestamoDTO tipoPrestamoDTO);
	public TipoPrestamoDTO find(int id);
	public TipoPrestamoDTO update(TipoPrestamoDTO tipoPrestamoDTO);
	public void delete(int id);
}
