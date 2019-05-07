package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.WithdrawsCashRstr;
/**
 * 提现限制设置Service
 * @author xiaoy
 * @date 2016年12月14日 下午6:24:46 
 * @Version 1.0
 */
public interface WithdrawsCashRstrServiceI {
	int deleteByPrimaryKey(BigDecimal id);

	int insertSelective(WithdrawsCashRstr record);

	WithdrawsCashRstr selectByPrimaryKey(BigDecimal id);

	int updateByPrimaryKeySelective(WithdrawsCashRstr record);

	List<WithdrawsCashRstr> selective(WithdrawsCashRstr record);
	
	int getCount();
	
}
