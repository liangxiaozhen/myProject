package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ptpl.model.DebtAttorn;
import com.ptpl.model.UserTender;

/**
 * @author:liuqh
 * @date:2016年07月12日 19:17:23
 * @description:标的债权转让设置
 */

public interface DebtAttornMapper extends BaseMapper<DebtAttorn>{
	/**
	 * 根据Tid查询债转表记录,主要针对手续费
	 * @param @param tid
	 * @param @return
	 * @return DebtAttorn
	 * @author jiangxueyou
	 */
	DebtAttorn selectByTid(BigDecimal tid);
	/**
	 * 获取相关的判断条件 
	 * @param @param condition
	 * @param @return
	 * @return DebtAttorn
	 * @author jiangxueyou
	 */
	DebtAttorn getCondition(Map<String, Object> condition);
	
}