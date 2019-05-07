package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.DebtAttornMapper;
import com.ptpl.mapper.RemoveNameMapper;
import com.ptpl.mapper.UserGradeMapper;
import com.ptpl.model.DebtAttorn;
import com.ptpl.model.UserTender;
import com.ptpl.service.DebtAttornServiceI;
import com.ptpl.web.util.PublicUtil;

/**
 * @author:liuqh
 * @date:2016年07月12日 19:17:23
 * @description:标的债权转让设置
 */
public class DebtAttornServiceImpl extends BaseServiceImpl<DebtAttorn> implements DebtAttornServiceI {
	@Autowired
	private UserGradeMapper userGrade;
	@Autowired
	private RemoveNameMapper removeName;
	@Autowired
	private DebtAttornMapper debtAttornMapper;
	@SuppressWarnings("unchecked")
	@Override
	public List<DebtAttorn> selectByConditionAndDecorateUgrade(DebtAttorn condition) {
		return PublicUtil.decorateList(selectByCondition(condition), userGrade, removeName,null);
	}
	/*public List<DebtAttorn> getAll(BigDecimal baseid) {
	  
		return debtAttornMapper.getAll(baseid);
	}*/
/*	public DebtAttorn getRocord(Map<String, Object> condition) {
		return debtAttornMapper.getRocord(condition);
	}*/
	@Override
	public DebtAttorn selectByTid(BigDecimal tid) {
		return debtAttornMapper.selectByTid(tid);
	}
	/*@Override
	public DebtAttorn getById(BigDecimal id) {
		return debtAttornMapper.getById(id);
	}*/
	@Override
	public DebtAttorn getCondition(Map<String, Object> condition) {
		return debtAttornMapper.getCondition(condition);
	}
}
