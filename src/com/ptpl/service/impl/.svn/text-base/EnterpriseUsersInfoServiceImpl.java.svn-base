package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.EnterpriseUsersInfoMapper;
import com.ptpl.model.EnterpriseUsersInfo;
import com.ptpl.service.EnterpriseUsersInfoServiceI;

public class EnterpriseUsersInfoServiceImpl implements EnterpriseUsersInfoServiceI {
 
    @Autowired
    private EnterpriseUsersInfoMapper mapper;
    
	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(EnterpriseUsersInfo record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(EnterpriseUsersInfo record) {
		return mapper.insertSelective(record);
	}

	@Override
	public EnterpriseUsersInfo selectByPrimaryKey(BigDecimal id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(EnterpriseUsersInfo record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(EnterpriseUsersInfo record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<EnterpriseUsersInfo> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public List<EnterpriseUsersInfo> selectBybaseID(BigDecimal baseid) {
		return mapper.selectBybaseID(baseid);
	}

}
