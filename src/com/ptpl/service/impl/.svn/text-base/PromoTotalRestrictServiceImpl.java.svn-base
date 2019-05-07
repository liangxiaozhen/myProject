package com.ptpl.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.PromoTotalRestrictMapper;
import com.ptpl.model.PromoTotalRestrict;
import com.ptpl.service.PromoTotalRestrictServiceI;

public class PromoTotalRestrictServiceImpl implements PromoTotalRestrictServiceI {
	@Autowired
	PromoTotalRestrictMapper promoTotalRestrictMapper;

	@Override
	public PromoTotalRestrict selectByPrimaryKey(BigDecimal id)
	{
		// TODO Auto-generated method stub
		return promoTotalRestrictMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(PromoTotalRestrict record)
	{
		// TODO Auto-generated method stub
		return promoTotalRestrictMapper.updateByPrimaryKeySelective(record);
	}

}
