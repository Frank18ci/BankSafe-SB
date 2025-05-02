package com.bank.service;

import java.util.List;

import com.bank.dto.TipoPlazoDTO;

public interface TipoPlazoService {
	public List<TipoPlazoDTO> list();
	public List<TipoPlazoDTO> listByAll();
	public TipoPlazoDTO find(int id);
	public TipoPlazoDTO findByAll(int id);
	public TipoPlazoDTO save(TipoPlazoDTO tipoPrestamoDTO);
	public TipoPlazoDTO update(TipoPlazoDTO tipoPrestamoDTO);
	public String delete(int id);
}
