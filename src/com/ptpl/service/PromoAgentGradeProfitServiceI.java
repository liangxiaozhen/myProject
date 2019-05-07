package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.PromoAgentGradeProfit;
import com.ptpl.model.UserPromoThirdParty;
/**
 * 推广代理资质等级分润 设置 Service
 * 
 * @author xiaoy
 * 
 * @date 2016年10月1日 上午10:45:24
 */
public interface PromoAgentGradeProfitServiceI {
	    int deleteByPrimaryKey(BigDecimal id);

	    int insertSelective(PromoAgentGradeProfit record);

	    PromoAgentGradeProfit selectByPrimaryKey(BigDecimal id);

	    int updateByPrimaryKeySelective(PromoAgentGradeProfit record);
	    
	    List<PromoAgentGradeProfit> selective(PromoAgentGradeProfit record);
	    
	    List<PromoAgentGradeProfit> getGradeName();
}
