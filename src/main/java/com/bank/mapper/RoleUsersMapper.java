package com.bank.mapper;
import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import com.bank.dto.RoleUserDTO;
import com.bank.model.RoleUser;

@Mapper(componentModel = "spring")
public interface RoleUsersMapper  extends Converter<List<RoleUser>, List<RoleUserDTO>>{
	@Override
	List<RoleUserDTO> convert(List<RoleUser> source);
}
