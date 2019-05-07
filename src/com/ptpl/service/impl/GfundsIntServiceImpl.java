package com.ptpl.service.impl;

import com.ptpl.mapper.GfundsIntMapper;
import com.ptpl.mapper.UserGradeMapper;
import com.ptpl.model.GfundsInt;
import com.ptpl.service.GfundsIntServiceI;
import com.ptpl.web.util.PublicUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;



public class GfundsIntServiceImpl extends BaseServiceImpl<GfundsInt> implements GfundsIntServiceI {
	@Autowired
	private UserGradeMapper userGrade;
	
	@Autowired
	private GfundsIntMapper gfmapper;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GfundsInt> selectByConditionAndDecorateUgrade(GfundsInt condition) {
		return PublicUtil.decorateList(selectByCondition(condition), userGrade,null,null);
	}
	
	//根据tid查询信息
	@Override
	public List<GfundsInt> selectGfundBytid(BigDecimal tid) {
		return gfmapper.selectGfundBytid(tid);
	}
	//根据tid查询等级信息
	@Override
	public List<String> selectGradeByTid(BigDecimal tid) {
		return gfmapper.selectGradeByTid(tid);
	}
}

