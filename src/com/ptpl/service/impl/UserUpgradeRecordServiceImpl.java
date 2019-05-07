package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.ptpl.mapper.UserUpgradeRecordMapper;
import com.ptpl.model.UserUpgradeRecord;
import com.ptpl.service.UserUpgradeRecordServiceI;

/**
 * 用户等级升级记录ServiceImpl
 * 
 * @author xiaoy
 *
 * @version 2016年11月28日 上午10:52:20
 */
public class UserUpgradeRecordServiceImpl implements UserUpgradeRecordServiceI {

	@Autowired
	private UserUpgradeRecordMapper mapper;
	@Override
	public int deleteByPrimaryKey(BigDecimal id)
	{
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserUpgradeRecord record)
	{
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(UserUpgradeRecord record)
	{
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public UserUpgradeRecord selectByPrimaryKey(BigDecimal id)
	{
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserUpgradeRecord record)
	{
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserUpgradeRecord record)
	{
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<UserUpgradeRecord> selective(UserUpgradeRecord record)
	{
		// TODO Auto-generated method stub
		return mapper.selective(record);
	}

	@Override
	public List<UserUpgradeRecord> selectByBaseID(BigDecimal baseid)
	{
		// TODO Auto-generated method stub
		return mapper.selectByBaseID(baseid);
	}

	@Override
	public List<UserUpgradeRecord> selectOlduGradeByBaseID(BigDecimal baseID)
	{
		// TODO Auto-generated method stub
		return mapper.selectOlduGradeByBaseID(baseID);
	}

}
