package com.ptpl.service.impl;

import com.ptpl.mapper.RiskGuarantyMoneyMapper;
import com.ptpl.mapper.UserGradeMapper;
import com.ptpl.model.RiskGuarantyMoney;
import com.ptpl.service.RiskGuarantyMoneyServiceI;
import com.ptpl.web.util.PublicUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author:liuqh
 * @date:2016年07月14日 15:09:51
 * @description:标的风险保证金设置
 */
public class RiskGuarantyMoneyServiceImpl extends BaseServiceImpl<RiskGuarantyMoney> implements RiskGuarantyMoneyServiceI {
	
		@Autowired
		private UserGradeMapper userGrade;
		
		@Autowired
		RiskGuarantyMoneyMapper riskGuarantyMoneyMapper;
		
		public List<RiskGuarantyMoney> selectByConditionAndDecorateUgrade(RiskGuarantyMoney condtion) {
			List<RiskGuarantyMoney> riskGuarantyMoneyList =selectByCondition(condtion);
			 List  list = PublicUtil.decorateList(riskGuarantyMoneyList,userGrade,null,null);
			 return list;
		}

		@Override
		public RiskGuarantyMoney selectByPrimaryKey(BigDecimal id) {
			return riskGuarantyMoneyMapper.selectByPrimaryKey(id);
		}

	    @Override
	    public List<RiskGuarantyMoney> selectRiskBytid(BigDecimal tid) {
	    return riskGuarantyMoneyMapper.selectRiskBytid(tid);
	    }

	@Override
	public List<String> selectGradebyTid(BigDecimal tid) {
		return riskGuarantyMoneyMapper.selectGradebyTid(tid);
	}
}
