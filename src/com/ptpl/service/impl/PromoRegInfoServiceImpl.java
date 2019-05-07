package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.PromoRegInfoMapper;
import com.ptpl.model.PromoRegInfo;
import com.ptpl.service.PromoRegInfoServiceI;

public class PromoRegInfoServiceImpl implements PromoRegInfoServiceI {

	@Autowired
	PromoRegInfoMapper promoRegInfoMapper;

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		// TODO Auto-generated method stub
		return promoRegInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(PromoRegInfo record) {
		// TODO Auto-generated method stub
		return promoRegInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(PromoRegInfo record) {
		// TODO Auto-generated method stub
		return promoRegInfoMapper.insertSelective(record);
	}

	@Override
	public PromoRegInfo selectByPrimaryKey(BigDecimal id) {
		// TODO Auto-generated method stub
		return promoRegInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(PromoRegInfo record) {
		// TODO Auto-generated method stub
		return promoRegInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(PromoRegInfo record) {
		// TODO Auto-generated method stub
		return promoRegInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<PromoRegInfo> selective(PromoRegInfo promoRegInfo) {
		// TODO Auto-generated method stub
		return promoRegInfoMapper.selective(promoRegInfo);
	}

}
