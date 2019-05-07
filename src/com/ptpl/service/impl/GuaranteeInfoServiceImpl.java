package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.GuaranteeInfoMapper;
import com.ptpl.model.GuaranteeInfo;
import com.ptpl.service.GuaranteeInfoServiceI;

public class GuaranteeInfoServiceImpl implements GuaranteeInfoServiceI {
	
	@Autowired
	GuaranteeInfoMapper guaranteeInfoMapper;

	@Override
	public int insertSelective(GuaranteeInfo record) {
		// TODO Auto-generated method stub
		return guaranteeInfoMapper.insertSelective(record);
	}

	@Override
	public GuaranteeInfo selectByPrimaryKey(BigDecimal id) {
		// TODO Auto-generated method stub
		return guaranteeInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<GuaranteeInfo> findGuaranteeList(GuaranteeInfo guarantee) {
		// TODO Auto-generated method stub
		return guaranteeInfoMapper.findGuaranteeList(guarantee);
	}

	@Override
	public int updateByPrimaryKeySelective(GuaranteeInfo record) {
		// TODO Auto-generated method stub
		return guaranteeInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		// TODO Auto-generated method stub
		return guaranteeInfoMapper.deleteByPrimaryKey(id);
	}

}
