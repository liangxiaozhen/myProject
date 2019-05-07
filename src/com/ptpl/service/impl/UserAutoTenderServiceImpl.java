package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.UserAutoTenderMapper;
import com.ptpl.model.UserAutoTender;
import com.ptpl.service.UserAutoTenderServiceI;

public class UserAutoTenderServiceImpl implements UserAutoTenderServiceI {
	
	@Autowired
	UserAutoTenderMapper userAutoTenderMapper;

	@Override
	public int insertSelective(UserAutoTender record) {
		return userAutoTenderMapper.insertSelective(record);
	}

	@Override
	public UserAutoTender selectByPrimaryKey(BigDecimal id) {
		return userAutoTenderMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByBaseIdSelective(UserAutoTender record) {
		return userAutoTenderMapper.updateByBaseIdSelective(record);
	}

	@Override
	public List<UserAutoTender> selectAll(UserAutoTender userAutoTender) {
		return userAutoTenderMapper.selectAll(userAutoTender);
	}

	@Override
	public UserAutoTender selectTenderPlanByBaseId(BigDecimal baseid) {
		return userAutoTenderMapper.selectTenderPlanByBaseId(baseid);
	}

}
