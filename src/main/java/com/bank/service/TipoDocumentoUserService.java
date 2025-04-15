package com.bank.service;

import java.util.List;

import com.bank.dto.TipoDocumentoUserDTO;

public interface TipoDocumentoUserService {
	public List<TipoDocumentoUserDTO> listByAll();
	public List<TipoDocumentoUserDTO> list();
	public TipoDocumentoUserDTO find(int id);
	public TipoDocumentoUserDTO findByAll(int id);
	public TipoDocumentoUserDTO save(TipoDocumentoUserDTO tipoDocumentoUserDTO);
	public TipoDocumentoUserDTO update(TipoDocumentoUserDTO tipoDocumentoUserDTO);
	public String delete(int id);
}
