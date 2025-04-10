package com.bank.service;

import java.util.List;

import com.bank.dto.PrestamoDTO;

public interface PrestamoService {
	public List<PrestamoDTO> list();
	public PrestamoDTO save(PrestamoDTO prestamoDTO);
	public PrestamoDTO find(int id);
	public PrestamoDTO update(PrestamoDTO prestamoDTO);
	public void delete(int id);
}
