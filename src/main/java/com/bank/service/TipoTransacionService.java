package com.bank.service;

import java.util.List;

import com.bank.dto.TipoTransacionDTO;



public interface TipoTransacionService {
	public List<TipoTransacionDTO> listByAll();
	public List<TipoTransacionDTO> list();
	public TipoTransacionDTO find(int id);
	public TipoTransacionDTO findByAll(int id);
	public TipoTransacionDTO save(TipoTransacionDTO transacionDTO);
	public TipoTransacionDTO update(TipoTransacionDTO transacionDTO);
	public String delete(int id);
}
