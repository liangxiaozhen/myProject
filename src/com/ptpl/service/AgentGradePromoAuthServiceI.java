package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.AgentGradePromoAuth;

public interface AgentGradePromoAuthServiceI {
	int deleteByPrimaryKey(BigDecimal id);

	int insertSelective(AgentGradePromoAuth record);

	AgentGradePromoAuth selectByPrimaryKey(BigDecimal id);

	int updateByPrimaryKeySelective(AgentGradePromoAuth record);
	
	List<AgentGradePromoAuth> selective(AgentGradePromoAuth record);
}
