package com.bank.service;

import java.util.List;

import com.bank.dto.TipoMonedaTarjetaDTO;

public interface TipoMonedaTarjetaService {

	public List<TipoMonedaTarjetaDTO> list();
	public List<TipoMonedaTarjetaDTO> listByAll();
	public TipoMonedaTarjetaDTO save(TipoMonedaTarjetaDTO tipoMonedaTarjetaDTO);
	public TipoMonedaTarjetaDTO find(int id);
	public TipoMonedaTarjetaDTO findByAll(int id);
	public TipoMonedaTarjetaDTO update(TipoMonedaTarjetaDTO tipoMonedaTarjetaDTO);
	public String delete(int id);
}
