package com.ptpl.mapper;

import com.ptpl.model.RiskGuarantyMoney;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author:liuqh
 * @date:2016年07月14日 15:09:51
 * @description:标的风险保证金设置
 */
public interface RiskGuarantyMoneyMapper extends BaseMapper<RiskGuarantyMoney>{
	
	/**
	 * 根据主键ID查询标的风险保证金设置详情
	 * @param id
	 * @return
	 */
	RiskGuarantyMoney selectByPrimaryKey(BigDecimal id);
	
	//根据tid查询信息
	List<RiskGuarantyMoney> selectRiskBytid(BigDecimal tid);

	 List<String> selectGradebyTid(BigDecimal tid);
}