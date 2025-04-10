package com.bank.service;

import java.util.List;

import com.bank.dto.TipoMonedaTarjetaDTO;

public interface TipoMonedaTarjetaService {

	public List<TipoMonedaTarjetaDTO> list();
	public TipoMonedaTarjetaDTO save(TipoMonedaTarjetaDTO tipoMonedaTarjetaDTO);
	public TipoMonedaTarjetaDTO find(int id);
	public TipoMonedaTarjetaDTO update(TipoMonedaTarjetaDTO tipoMonedaTarjetaDTO);
	public void delete(int id);
}
