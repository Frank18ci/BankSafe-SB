package com.bank.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bank.dto.UserDTO;


public interface UserService {
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	public List<UserDTO> listUsers();
	public UserDTO findUser(int id);
	public UserDTO updateUser(UserDTO userDTO);
	public void deleteUser(int id);
}
