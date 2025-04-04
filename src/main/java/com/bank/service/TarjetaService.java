package com.bank.service;

import java.util.List;


import com.bank.dto.TarjetaDTO;


public interface TarjetaService {
	public List<TarjetaDTO> listTarjetas();
	public TarjetaDTO findTarjeta(int id);
	public TarjetaDTO updateTarjeta(TarjetaDTO tarjetaDTO);
	public void deleteTarjeta(int id);
}
