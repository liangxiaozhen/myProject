package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.RoleUserMapper;
import com.ptpl.model.AdminUser;
import com.ptpl.model.RoleUser;
import com.ptpl.service.RoleUserServiceI;

public class RoleUserServiceImpl implements RoleUserServiceI{
	@Autowired
	private RoleUserMapper RoleUserMapper;
	
	@Override
	public int insert(RoleUser roleUser) {
 		return RoleUserMapper.insert(roleUser);
	}

	@Override
	public int insertSelective(RoleUser roleUser) {
 		return RoleUserMapper.insertSelective(roleUser);
	}

	@Override
	public int deleteByRoleIdAndUserId(RoleUser roleUser) {
 		return RoleUserMapper.deleteByRoleIdAndUserId(roleUser);
	}

	@Override
	public int deleteByRoleId(RoleUser roleUser) {
 		return RoleUserMapper.deleteByRoleId(roleUser);
	}

 }
