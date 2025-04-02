package com.bank.mapper;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import com.bank.dto.RoleUserDTO;
import com.bank.model.RoleUser;

@Mapper(componentModel = "spring")
public interface RoleUserMapper extends Converter<RoleUser, RoleUserDTO>{
	RoleUserDTO convert(RoleUser source);
}
