package com.ptpl.service;

import java.math.BigDecimal;

import com.ptpl.model.DebtAttornRecord;

public interface DebtAttornRecordServiceI {
	
	 	int deleteByPrimaryKey(BigDecimal id);

	    int insert(DebtAttornRecord record);

	    int insertSelective(DebtAttornRecord record);

	    DebtAttornRecord selectByPrimaryKey(BigDecimal id);

	    int updateByPrimaryKeySelective(DebtAttornRecord record);

	    int updateByPrimaryKey(DebtAttornRecord record);

}
