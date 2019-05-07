package com.ptpl.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.DataDescMapper;
import com.ptpl.mapper.DebtAttornRecordMapper;
import com.ptpl.model.DebtAttornRecord;
import com.ptpl.service.DebtAttornRecordServiceI;

public class DebtAttornRecordServiceImpl implements DebtAttornRecordServiceI {
	@Autowired
	DebtAttornRecordMapper debtattornrecordmapper;
	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return debtattornrecordmapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(DebtAttornRecord record) {
		return debtattornrecordmapper.insert(record);
	}

	@Override
	public int insertSelective(DebtAttornRecord record) {
		return debtattornrecordmapper.insertSelective(record);
	}

	@Override
	public DebtAttornRecord selectByPrimaryKey(BigDecimal id) {
		return debtattornrecordmapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(DebtAttornRecord record) {
		return debtattornrecordmapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(DebtAttornRecord record) {
		return debtattornrecordmapper.updateByPrimaryKey(record);
	}

}
