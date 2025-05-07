package com.bank.service;

import java.util.List;

import com.bank.dto.TipoEmpresaDTO;

public interface TipoEmpresaService {
	public List<TipoEmpresaDTO> listByAll();
	public List<TipoEmpresaDTO> list();
	public TipoEmpresaDTO find(int id);
	public TipoEmpresaDTO findByAll(int id);
	public TipoEmpresaDTO save(TipoEmpresaDTO tipoEmpresaDTO);
	public TipoEmpresaDTO update(TipoEmpresaDTO tipoEmpresaDTO);
	public String delete(int id);
}
