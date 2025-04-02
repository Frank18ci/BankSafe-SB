package com.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.dto.RoleUserDTO;
@Service
public interface RoleUserService {
	public List<RoleUserDTO> listUserRols();
	public RoleUserDTO saveUserRole(RoleUserDTO userRoleDTO);
	public RoleUserDTO findUserRole(int id);
	public RoleUserDTO updateUserRole(RoleUserDTO userRoleDTO);
	public void deleteUserRole(int id);
}
