package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.AdminUserMapper;
import com.ptpl.model.AdminUser;
import com.ptpl.service.AdminUserServiceI;

public class AdminUserServiceImpl implements AdminUserServiceI{

	@Autowired
	private AdminUserMapper adminUserMapper;
	
	@Override
	public int insert(AdminUser adminUser) {
 		return adminUserMapper.insert(adminUser);
	}

	@Override
	public int insertSelective(AdminUser adminUser) {
 		return adminUserMapper.insertSelective(adminUser);
	}

	@Override
	public AdminUser findAdminUserPSWByUsName(AdminUser adminUser) {
 		return adminUserMapper.findAdminUserPSWByUsName(adminUser);
	}

	@Override
	public int updateAdminUser(AdminUser adminUser) {
 		return adminUserMapper.updateAdminUser(adminUser);
	}

	@Override
	public List<AdminUser> findAllAdminUserByForBid(AdminUser adminUser) {
 		return adminUserMapper.findAllAdminUserByForBid(adminUser);
	}

	@Override
	public AdminUser findAdminUserByEamil(AdminUser adminUser) {
 		return adminUserMapper.findAdminUserByEamil(adminUser);
	}

	@Override
	public List<AdminUser> findAdminUsers(AdminUser adminUser) {
 		return adminUserMapper.findAdminUsers(adminUser);
	}

	@Override
	public AdminUser findRoleByAdminUserId(Integer id) {
 		return adminUserMapper.findRoleByAdminUserId(id);
	}

	@Override
	public List<AdminUser> findUserByRoleId(Integer id) {
 		return adminUserMapper.findUserByRoleId(id);
	}

	@Override
	public AdminUser getAdminUserById(BigDecimal bigDecimal) {
		return adminUserMapper.getAdminUserById(bigDecimal);
	}
 
}
