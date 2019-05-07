package com.ptpl.mapper;

import com.ptpl.model.FailTCompensate;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author:liuqh
 * @date:2016年07月11日 10:21:52
 * @description:流标补偿设置接口
 */
public interface FailTCompensateMapper extends BaseMapper<FailTCompensate>{
	//根据tid查询信息
	List<FailTCompensate> selectefatlpenBytid(BigDecimal tid);
	//根据tid查询等级信息，去重复
	public List<String> selectGradeByTid(BigDecimal tid);
}