package com.ptpl.service;

import com.ptpl.model.FailTCompensate;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author:liuqh
 * @date:2016年07月09日 19:58:12
 * @description:流标补偿设置
 */
public interface FailTCompensateServiceI extends BaseService<FailTCompensate>{
	public List<FailTCompensate> selectByConditionAndDecorateUgrade(FailTCompensate condtion);
	
	//根据tid查询信息
	List<FailTCompensate> selectefatlpenBytid(BigDecimal tid);
	//根据tid查询等级信息，去重复
	public List<String> selectGradeByTid(BigDecimal tid);
}
