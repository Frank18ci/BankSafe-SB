package com.bank.service;

import java.util.List;

import com.bank.dto.TipoDocumentoUserDTO;

public interface TipoDocumentoUserService {
	public List<TipoDocumentoUserDTO> list();
	public TipoDocumentoUserDTO save(TipoDocumentoUserDTO tipoDocumentoUserDTO);
	public TipoDocumentoUserDTO find(int id);
	public TipoDocumentoUserDTO update(TipoDocumentoUserDTO tipoDocumentoUserDTO);
	public void delete(int id);
}
