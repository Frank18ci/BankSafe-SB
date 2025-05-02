package com.bank.service;

import java.util.List;


import com.bank.dto.RoleUserDTO;

public interface RoleUserService {
	public List<RoleUserDTO> listByAll();
	public List<RoleUserDTO> list();
	public RoleUserDTO find(int id);
	public RoleUserDTO findByAll(int id);
	public RoleUserDTO save(RoleUserDTO userRoleDTO);
	public RoleUserDTO update(RoleUserDTO userRoleDTO);
	public String delete(int id);
}
