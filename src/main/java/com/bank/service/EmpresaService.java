package com.bank.service;

import java.util.List;

import com.bank.dto.EmpresaDTO;

public interface EmpresaService {
	public List<EmpresaDTO> listByAll();
	public List<EmpresaDTO> list();
	public EmpresaDTO find(int id);
	public EmpresaDTO findByAll(int id);
	public EmpresaDTO save(EmpresaDTO empresaDTO);
	public EmpresaDTO update(EmpresaDTO empresaDTO);
	public String delete(int id);
}
