package com.bank.service;

import java.util.List;


import com.bank.dto.RoleUserDTO;

public interface RoleUserService {
	public List<RoleUserDTO> list();
	public RoleUserDTO save(RoleUserDTO userRoleDTO);
	public RoleUserDTO find(int id);
	public RoleUserDTO update(RoleUserDTO userRoleDTO);
	public void delete(int id);
}
