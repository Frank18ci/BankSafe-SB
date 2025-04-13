package com.bank.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dto.PrestamoDTO;
import com.bank.dto.UserDTO;
import com.bank.model.Prestamo;
import com.bank.repository.PrestamoRepository;
import com.bank.service.PrestamoService;
@Service
public class PrestamoServiseImpl implements PrestamoService {
	@Autowired
	private PrestamoRepository prestamoRepository;
	@Override
	public List<PrestamoDTO> list() {
		return null;
	}
	@Override
	public List<PrestamoDTO> listByAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public PrestamoDTO find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PrestamoDTO findByAll(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrestamoDTO save(PrestamoDTO prestamoDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrestamoDTO update(PrestamoDTO prestamoDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
