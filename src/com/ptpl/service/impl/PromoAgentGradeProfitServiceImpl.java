package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.PromoAgentGradeProfitMapper;
import com.ptpl.model.PromoAgentGradeProfit;
import com.ptpl.model.UserPromoThirdParty;
import com.ptpl.service.PromoAgentGradeProfitServiceI;
/**
 * 推广代理资质等级分润 设置 Impl
 * @author xiaoy
 *
 * @version 2016年10月1日 下午2:16:29
 */
public class PromoAgentGradeProfitServiceImpl implements PromoAgentGradeProfitServiceI {
	@Autowired
	PromoAgentGradeProfitMapper promoAgentGradeProfitMapper;
	@Override
	public int deleteByPrimaryKey(BigDecimal id)
	{
		return promoAgentGradeProfitMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(PromoAgentGradeProfit record)
	{
		return promoAgentGradeProfitMapper.insertSelective(record);
	}

	@Override
	public PromoAgentGradeProfit selectByPrimaryKey(BigDecimal id)
	{
		return promoAgentGradeProfitMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(PromoAgentGradeProfit record)
	{
		return promoAgentGradeProfitMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<PromoAgentGradeProfit> selective(PromoAgentGradeProfit record)
	{
		return promoAgentGradeProfitMapper.selective(record);
	}

	@Override
	public List<PromoAgentGradeProfit> getGradeName()
	{
		// TODO Auto-generated method stub
		return promoAgentGradeProfitMapper.getGradeName();
	}
}
