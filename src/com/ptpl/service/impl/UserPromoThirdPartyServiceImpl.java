package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.UserPromoThirdPartyMapper;
import com.ptpl.model.UserPromoThirdParty;
import com.ptpl.service.UserPromoThirdPartyServiceI;
/**
 * 用户推广第三方公司设置 ServiceImpl
 * 
 * @author xiaoy
 *
 * @version 2016年9月30日 上午10:23:43
 */
public class UserPromoThirdPartyServiceImpl implements UserPromoThirdPartyServiceI {
	@Autowired
	UserPromoThirdPartyMapper userPromoThirdPartyMapper;

	@Override
	public int deleteByPrimaryKey(BigDecimal id)
	{
		// TODO Auto-generated method stub
		return userPromoThirdPartyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(UserPromoThirdParty record)
	{
		// TODO Auto-generated method stub
		return userPromoThirdPartyMapper.insertSelective(record);
	}

	@Override
	public UserPromoThirdParty selectByPrimaryKey(BigDecimal id)
	{
		// TODO Auto-generated method stub
		return userPromoThirdPartyMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserPromoThirdParty record)
	{
		// TODO Auto-generated method stub
		return userPromoThirdPartyMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<UserPromoThirdParty> selective(UserPromoThirdParty userPromoThirdParty)
	{
		// TODO Auto-generated method stub
		return userPromoThirdPartyMapper.selective(userPromoThirdParty);
	}

	@Override
	public int deleteByThirdPartyName(String thirdPartyName)
	{
		// TODO Auto-generated method stub
		return userPromoThirdPartyMapper.deleteByThirdPartyName(thirdPartyName);
	}

	@Override
	public int updateByUPID(BigDecimal upid)
	{
		// TODO Auto-generated method stub
		return userPromoThirdPartyMapper.updateByUPID(upid);
	}

	@Override
	public int updateNameAndCode(String oldName, String code, String newName)
	{
		// TODO Auto-generated method stub
		return userPromoThirdPartyMapper.updateNameAndCode(oldName, code, newName);
	}
}
