package com.ptpl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.AccInExRecordMapper;
import com.ptpl.model.AccInExRecord;
import com.ptpl.service.AccInExRecordServiceI;

public class AccInExRecordServiceImpl implements AccInExRecordServiceI {
	
	@Autowired
	AccInExRecordMapper accInExRecordMapper;
	
	@Override
	public int insert(AccInExRecord record) {
		// TODO Auto-generated method stub
		return accInExRecordMapper.insert(record);
	}

	@Override
	public int insertSelective(AccInExRecord record) {
		// TODO Auto-generated method stub
		return accInExRecordMapper.insertSelective(record);
	}

	@Override
	public List<AccInExRecord> queryAllUserAccInExRecord(Map map) {
		// TODO Auto-generated method stub
		return accInExRecordMapper.queryAllUserAccInExRecord(map);
	}

	@Override
	public AccInExRecord queryAccInExRecord(AccInExRecord record) {
		// TODO Auto-generated method stub
		return accInExRecordMapper.queryAccInExRecord(record);
	}

	@Override
	public List<AccInExRecord> queryAllUser(AccInExRecord record)
	{
		// TODO Auto-generated method stub
		return accInExRecordMapper.queryAllUser(record);
	}

	@Override
	public AccInExRecord getAccInExRecordBydescription(String record) {
		// TODO Auto-generated method stub
		return accInExRecordMapper.getAccInExRecordBydescription(record);
	}

	@Override
	public List<AccInExRecord> findAccInExRecordByBorderno(String borderno) {
 		return accInExRecordMapper.findAccInExRecordByBorderno(borderno);
	}
}
