package com.ptpl.service.impl;

import com.ptpl.mapper.FailTCompensateMapper;
import com.ptpl.mapper.UserGradeMapper;
import com.ptpl.model.FailTCompensate;
import com.ptpl.service.FailTCompensateServiceI;
import com.ptpl.web.util.PublicUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author:liuqh
 * @date:2016年07月09日 20:06:39
 * @description:流标补偿设置
 */
public class FailTCompensateServiceImpl extends BaseServiceImpl<FailTCompensate> implements FailTCompensateServiceI {
	@Autowired
	private UserGradeMapper userGrade;
	@Autowired
	private FailTCompensateMapper failmapper;
	
	@Override
	public List<FailTCompensate> selectByConditionAndDecorateUgrade(FailTCompensate condtion) {
		return PublicUtil.decorateList(selectByCondition(condtion),userGrade,null,null);
	}
	
	@Override
	public List<FailTCompensate> selectefatlpenBytid(BigDecimal tid) {
		return failmapper.selectefatlpenBytid(tid);
	}

	@Override
	public List<String> selectGradeByTid(BigDecimal tid) {
		return failmapper.selectGradeByTid(tid);
	}
}
