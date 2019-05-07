package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptpl.mapper.UserNameRuleModuleMapper;
import com.ptpl.model.UserNameRuleModule;
import com.ptpl.service.UserNameRuleModuleServiceI;

public class UserNameRuleModuleServiceImpl implements UserNameRuleModuleServiceI {

	@Autowired
	private UserNameRuleModuleMapper userNameRuleModuleMapper;
	@Override
	public int updateByPrimaryKeySelective(UserNameRuleModule record)
	{
		// TODO Auto-generated method stub
		return userNameRuleModuleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public UserNameRuleModule selectByPrimaryKey(BigDecimal id)
	{
		UserNameRuleModule unrm=userNameRuleModuleMapper.selectByPrimaryKey(id);
		setRegisterStr(unrm);
		return unrm;
	}
	/**
	 * 设置注册方式String
	 * @param unrm
	 */
	private void setRegisterStr(UserNameRuleModule unrm)
	{
		switch(unrm.getRegistertype())
		{
			case 1:
				unrm.setRegisterStr("用户名");
				break;
			case 2:
				unrm.setRegisterStr("用户名+手机号");
				break;
			case 3:
				unrm.setRegisterStr("手机号");
				break;
			case 4:
				unrm.setRegisterStr("邮箱");
				break;
			case 5:
				unrm.setRegisterStr("第三方");
				break;
		}
	}

	@Override
	public List<UserNameRuleModule> selective(UserNameRuleModule record)
	{
		List<UserNameRuleModule> list=userNameRuleModuleMapper.selective(record);
		for(UserNameRuleModule unrm:list){
			setRegisterStr(unrm);
		}
		return list;
	}
	@Override
	public int insertSelective(UserNameRuleModule record)
	{
		// TODO Auto-generated method stub
		return userNameRuleModuleMapper.insertSelective(record);
	}
	@Override
	public int updateByPrimaryKey(UserNameRuleModule record)
	{
		// TODO Auto-generated method stub
		return userNameRuleModuleMapper.updateByPrimaryKey(record);
	}
}
