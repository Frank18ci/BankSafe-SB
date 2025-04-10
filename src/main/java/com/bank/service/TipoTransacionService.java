package com.bank.service;

import java.util.List;

import com.bank.dto.TipoTransacionDTO;



public interface TipoTransacionService {
	public List<TipoTransacionDTO> list();
	public TipoTransacionDTO save(TipoTransacionDTO transacionDTO);
	public TipoTransacionDTO find(int id);
	public TipoTransacionDTO update(TipoTransacionDTO transacionDTO);
	public void delete(int id);
}
