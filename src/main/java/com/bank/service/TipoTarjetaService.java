package com.bank.service;

import java.util.List;

import com.bank.dto.TipoTarjetaDTO;

public interface TipoTarjetaService {
	public List<TipoTarjetaDTO> listByAll();
	public List<TipoTarjetaDTO> list();
	public TipoTarjetaDTO save(TipoTarjetaDTO tipoTarjetaDTO);
	public TipoTarjetaDTO find(int id);
	public TipoTarjetaDTO findByAll(int id);
	public TipoTarjetaDTO update(TipoTarjetaDTO tipoTarjetaDTO);
	public String delete(int id);
}
