package com.ptpl.mapper;

import com.ptpl.model.InterestExpense;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author:liuqh
 * @date:2016年07月12日 23:13:12
 * @description:标的利息管理费设置
 */
public interface InterestExpenseMapper extends BaseMapper<InterestExpense>{
	 //通过tid获取利息管理费集合
	 public List<InterestExpense> selectExpensebytid(BigDecimal tid) throws Exception;

    List<String> selectGradebyTid(BigDecimal tid);
    
    public int insertSelective(InterestExpense interestExpense); 
    
    public int updateById(InterestExpense interestExpense); 
    
    public InterestExpense selectInterestExpenseById(BigDecimal id);
}