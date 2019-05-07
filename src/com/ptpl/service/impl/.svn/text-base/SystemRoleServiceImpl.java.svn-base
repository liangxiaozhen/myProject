package com.ptpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.SystemRoleMapper;
import com.ptpl.model.SystemRole;
import com.ptpl.service.SystemRoleServiceI;

public class SystemRoleServiceImpl implements SystemRoleServiceI {

	@Autowired
	private SystemRoleMapper systemRoleMapper;

	@Override
	public int insert(SystemRole systemRole) {
		return systemRoleMapper.insert(systemRole);
	}

	@Override
	public int insertSelective(SystemRole systemRole) {
		return systemRoleMapper.insertSelective(systemRole);
	}

	@Override
	public int deleteById(SystemRole systemRole) {
		return systemRoleMapper.deleteById(systemRole);
	}

	@Override
	public int updateById(SystemRole systemRole) {
		return systemRoleMapper.updateById(systemRole);
	}

	@Override
	public List<SystemRole> findSystemRoles() {
		return systemRoleMapper.findSystemRoles();
	}

	@Override
	public SystemRole findSystemRoleById(Integer id) {
 		return systemRoleMapper.findSystemRoleById(id);
	}

}
