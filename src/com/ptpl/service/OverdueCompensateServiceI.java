package com.ptpl.service;

import com.ptpl.model.OverdueCompensate;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author:liuqh
 * @date:2016年07月14日 14:13:02
 * @description:标的逾期代偿设置
 */
public interface OverdueCompensateServiceI extends BaseService<OverdueCompensate>{
	public List<OverdueCompensate> selectByConditionAndDecorateUgrade(OverdueCompensate condtion);
	
	//根据tid查询信息
	public List<OverdueCompensate> selectoverPenBytid(BigDecimal tid);

    List<String> selectGradeByTid(BigDecimal tid);
}
