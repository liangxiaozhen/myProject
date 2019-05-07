package com.ptpl.service.impl;

import com.ptpl.mapper.OverdueCompensateMapper;
import com.ptpl.mapper.UserGradeMapper;
import com.ptpl.model.OverdueCompensate;
import com.ptpl.service.OverdueCompensateServiceI;
import com.ptpl.web.util.PublicUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author:liuqh
 * @date:2016年07月14日 14:13:02
 * @description:标的逾期代偿设置
 */
public class OverdueCompensateServiceImpl extends BaseServiceImpl<OverdueCompensate> implements OverdueCompensateServiceI {
	@Autowired
	private UserGradeMapper userGrade;
	
	@Autowired
	private OverdueCompensateMapper compensateMapper;
	
	public List<OverdueCompensate> selectByConditionAndDecorateUgrade(OverdueCompensate condtion) {
		List<OverdueCompensate> overdueCompensateList =selectByCondition(condtion);
		 List  list = PublicUtil.decorateList(overdueCompensateList,userGrade,null,null);
		 return list;
	}
	
	@Override
	public List<OverdueCompensate> selectoverPenBytid(BigDecimal tid) {
		return compensateMapper.selectoverPenBytid(tid);
	}

	@Override
	public List<String> selectGradeByTid(BigDecimal tid) {
		return compensateMapper.selectGradeByTid(tid);
	}
}
