package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.GlobalSettingMapper;
import com.ptpl.model.GlobalSetting;
import com.ptpl.service.GlobalSettingServiceI;

public class GlobalSrttingServiceImpl implements GlobalSettingServiceI {
	
	@Autowired
	private GlobalSettingMapper globalSettingMapper;

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		
		return globalSettingMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(GlobalSetting record) {
		
		return globalSettingMapper.insert(record);
	}

	@Override
	public GlobalSetting selectByPrimaryKey(BigDecimal id) {
		return globalSettingMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<GlobalSetting> allData() {
		
		return globalSettingMapper.allData();
	}

	@Override
	public int updateByPrimaryKeySelective(GlobalSetting record) {
		return globalSettingMapper.updateByPrimaryKeySelective(record);
	}

	 
	
	
	
}
