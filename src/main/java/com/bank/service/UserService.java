package com.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.dto.UserDTO;

@Service
public interface UserService {
	public List<UserDTO> listUsers();
	public UserDTO findUser(int id);
	public UserDTO updateUser(UserDTO userDTO);
	public void deleteUser(int id);
}
