package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.RoleResourceMapper;
import com.ptpl.model.RoleResource;
import com.ptpl.model.RoleUser;
import com.ptpl.model.SystemResource;
import com.ptpl.service.RoleResourceServiceI;

public class RoleResourceServiceImpl implements RoleResourceServiceI{

	@Autowired
	private RoleResourceMapper roleResourceMapper;
	@Override
	public int insert(RoleResource roleResource) {
 		return roleResourceMapper.insert(roleResource);
	}

	@Override
	public int insertSelective(RoleResource roleResource) {
 		return roleResourceMapper.insertSelective(roleResource);
	}

	@Override
	public int deleteByRoleId(BigDecimal roleId) {
 		return roleResourceMapper.deleteByRoleId(roleId);
	}

	@Override
	public RoleResource findResourcesByRoleId(BigDecimal roleId) {
 		return roleResourceMapper.findResourcesByRoleId(roleId);
	}

 }
