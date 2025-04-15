package com.bank.service;

import java.util.List;

import com.bank.dto.TransacionDTO;


public interface TransaccionService {
	public List<TransacionDTO> listByAll();
	public List<TransacionDTO> list();
	public TransacionDTO find(int id);
	public TransacionDTO findByAll(int id);
	public TransacionDTO save(TransacionDTO transacionDTO);
	public TransacionDTO update(TransacionDTO transacionDTO);
	public String delete(int id);
}
