package com.bank.service;

import java.util.List;

import com.bank.dto.UserDTO;
import com.bank.dto.UserIDTO;


public interface UserService {
	public List<UserDTO> listByAll();
	public List<UserDTO> list();
	public List<UserIDTO> listI();
	public UserIDTO findI(int id);
	public UserDTO find(int id);
	public UserDTO findByAll(int id);
	public UserDTO save(UserDTO userDTO);
	public UserDTO update(UserDTO userDTO);
	public String delete(int id);
}
