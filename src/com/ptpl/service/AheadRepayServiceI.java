package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.AheadRepay;

/**
 * @author:liuqh
 * @date:2016年07月12日 17:31:29
 * @description:标的提前还款设置
 */
public interface AheadRepayServiceI extends BaseService<AheadRepay>{
	    //根据tid查询信息
		List<AheadRepay> selectAhpayBytid(BigDecimal tid);
}
