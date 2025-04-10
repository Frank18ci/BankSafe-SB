package com.bank.service;

import java.util.List;

import com.bank.dto.TransacionDTO;


public interface TransaccionService {
	public List<TransacionDTO> list();
	public TransacionDTO save(TransacionDTO transacionDTO);
	public TransacionDTO find(int id);
	public TransacionDTO update(TransacionDTO transacionDTO);
	public void delete(int id);
}
