package com.ptpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.DataDescMapper;
import com.ptpl.model.DataDesc;
import com.ptpl.service.DataDescServiceI;

public class DataDescServiceImpl implements DataDescServiceI {
	@Autowired
	DataDescMapper dataDescMapper;
	
	@Override
	public List<DataDesc> getDataDesc(String type) {
		// TODO Auto-generated method stub
		return dataDescMapper.getDataDesc(type);
	}

}
