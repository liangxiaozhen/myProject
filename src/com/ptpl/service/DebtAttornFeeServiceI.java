package com.ptpl.service;

import com.ptpl.model.DebtAttornFee;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author liuj
 * 标的债全转让手续费设置service层
 */
public interface DebtAttornFeeServiceI extends BaseService<DebtAttornFee>{
	/**
	 * 根据tid查询手续费收取情况
	 * @param @param tid
	 * @param @return
	 * @return List<DebtAttornFee>
	 * @author jiangxueyou
	 */
    List<DebtAttornFee> selectByTid(BigDecimal tid);

	//通过tid查找已设置的等级集合
	public List<String> selectGradeByTid(BigDecimal tid);

}
