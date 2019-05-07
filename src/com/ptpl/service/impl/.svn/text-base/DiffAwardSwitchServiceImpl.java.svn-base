package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.model.DiffAwardSwitch;
import com.ptpl.mapper.DiffAwardSwitchMapper;
import com.ptpl.service.DiffAwardSwitchServiceI;

public class DiffAwardSwitchServiceImpl implements DiffAwardSwitchServiceI {

	@Autowired
	DiffAwardSwitchMapper diffAwardSwitchMapper;
	
	/*条件查询*/
	public List<DiffAwardSwitch> selectByCondition(DiffAwardSwitch das) {
		return diffAwardSwitchMapper.selectByCondition(das);
	}

	
	public int insertSelective(DiffAwardSwitch das) {
		return diffAwardSwitchMapper.insertSelective(das);
	}


	/*id查询*/
	public DiffAwardSwitch getDiffAwardSwitchById(BigDecimal id) {
		return diffAwardSwitchMapper.getDiffAwardSwitchById(id);
	}


	/*id  修改*/
	public int updateDiffAwardSwitchById(DiffAwardSwitch das) {
		return diffAwardSwitchMapper.updateDiffAwardSwitchById(das);
	}


	/*属性 查询*/
	public DiffAwardSwitch getDiffAwardSwitchByAttribute(Short attribute) {
		return diffAwardSwitchMapper.getDiffAwardSwitchByAttribute(attribute);
	}

}
