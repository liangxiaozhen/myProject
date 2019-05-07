package com.ptpl.service.impl;

import com.ptpl.mapper.InterestExpenseMapper;
import com.ptpl.mapper.UserGradeMapper;
import com.ptpl.model.InterestExpense;
import com.ptpl.service.InterestExpenseServiceI;
import com.ptpl.web.util.PublicUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author:liuqh
 * @date:2016年07月12日 23:13:12
 * @description:标的利息管理费设置
 */
public class InterestExpenseServiceImpl extends BaseServiceImpl<InterestExpense> implements InterestExpenseServiceI {
	@Autowired
	private UserGradeMapper userGrade;
	@Autowired
	private InterestExpenseMapper mapper;


	@SuppressWarnings("unchecked")
	@Override
	public List<InterestExpense> selectByConditionAndDecorateUgrade(InterestExpense condtion) throws Exception{
		return PublicUtil.decorateList(selectByCondition(condtion),userGrade,null,null);
	}

	@Override
	public List<InterestExpense> selectExpensebytid(BigDecimal tid) throws Exception {
		return mapper.selectExpensebytid(tid);
	}

	@Override
	public List<String> selectGradebyTid(BigDecimal tid) {
		return mapper.selectGradebyTid(tid);
	}

	public int insertSelective(InterestExpense interestExpense){
		return mapper.insertSelective(interestExpense);
	}

	public int updateById(InterestExpense interestExpense){
		return mapper.updateById(interestExpense);
	}

	public InterestExpense selectInterestExpenseById(BigDecimal id){
		return mapper.selectInterestExpenseById(id);
	}
}
