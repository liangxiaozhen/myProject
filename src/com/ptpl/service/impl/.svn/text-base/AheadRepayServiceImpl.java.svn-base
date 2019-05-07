package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.AheadRepayMapper;
import com.ptpl.model.AheadRepay;
import com.ptpl.service.AheadRepayServiceI;

/**
 * @author:liuqh
 * @date:2016年07月12日 17:31:29
 * @description:标的提前还款设置
 */
public class AheadRepayServiceImpl extends BaseServiceImpl<AheadRepay> implements AheadRepayServiceI {
	
	@Autowired
	private AheadRepayMapper mapper;
	
	@Override
	public List<AheadRepay> selectAhpayBytid(BigDecimal tid) {
		return mapper.selectAhpayBytid(tid);
	}
}
