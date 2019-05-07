package com.ptpl.service;

import com.ptpl.model.GfundsInt;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author:liuqh
 * @date:2016年07月09日 20:02:37
 * @description:流标补偿设置接口
 */
public interface GfundsIntServiceI extends BaseService<GfundsInt>{
	public List<GfundsInt> selectByConditionAndDecorateUgrade(GfundsInt condition);
	
	public List<GfundsInt> selectGfundBytid(BigDecimal tid);

	public List<String> selectGradeByTid(BigDecimal tid);
}
