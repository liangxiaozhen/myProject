package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.FailTAwardCompensateMapper;
import com.ptpl.mapper.UserGradeMapper;
import com.ptpl.model.FailTAwardCompensate;
import com.ptpl.service.FailTAwardCompensateServiceI;
import com.ptpl.web.util.PublicUtil;

public class FailTAwardCompensateServiceImpl extends BaseServiceImpl<FailTAwardCompensate> implements FailTAwardCompensateServiceI {
	@Autowired
	private UserGradeMapper userGrade;
	@Autowired
	private FailTAwardCompensateMapper mapper;

	@Override
	public List<FailTAwardCompensate> selectByConditionAndDecorateUgrade(FailTAwardCompensate condtion) {
		return PublicUtil.decorateList(selectByCondition(condtion),userGrade,null,null);
	}

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(FailTAwardCompensate record) {
		return mapper.insertSelective(record);
	}

	@Override
	public FailTAwardCompensate selectByPrimaryKey(BigDecimal id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(FailTAwardCompensate record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(FailTAwardCompensate record) {
		return mapper.updateByPrimaryKey(record);
	}
    
	@Override
	public List<FailTAwardCompensate> selectfailAwardBytid(BigDecimal tid) {
		return mapper.selectfailAwardBytid(tid);
	}

	@Override
	public List<String> selectAugradesByid(BigDecimal tid) {
		return mapper.selectAugradesByid(tid);
	}

}
