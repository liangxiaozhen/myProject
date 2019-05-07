package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.RedEnveLopeItemMapper;
import com.ptpl.model.RedEnveLopeItem;
import com.ptpl.service.RedEnveLopeItemServiceI;

public class RedEnveLopeItemServiceImpl implements RedEnveLopeItemServiceI{

	/**
	 * 
	 * 现金红包发放对账记录
	 * RedEnveLopeItemServiceI
	 * 创建人:lihs
	 * 时间：2016年10月13日 15:34:28
	 * @version 1.0.0
	 *
	 */
	
	@Autowired
	RedEnveLopeItemMapper redEnveLopeItemMapper;
	
	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		
		
		
		// TODO Auto-generated method stub
		return redEnveLopeItemMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RedEnveLopeItem record) {
		// TODO Auto-generated method stub
		return redEnveLopeItemMapper.insert(record);
	}

	@Override
	public int insertSelective(RedEnveLopeItem record) {
		// TODO Auto-generated method stub
		return redEnveLopeItemMapper.insertSelective(record);
	}

	@Override
	public RedEnveLopeItem selectByPrimaryKey(BigDecimal id) {
		// TODO Auto-generated method stub
		return redEnveLopeItemMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RedEnveLopeItem record) {
		// TODO Auto-generated method stub
		return redEnveLopeItemMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RedEnveLopeItem record) {
		// TODO Auto-generated method stub
		return redEnveLopeItemMapper.updateByPrimaryKey(record);
	}

	@Override
	public RedEnveLopeItem getByOrderNo(String OrderNo) {
		// TODO Auto-generated method stub
		return redEnveLopeItemMapper.getByOrderNo(OrderNo);
	}

	//查询对账记录
	public List<RedEnveLopeItem> selectByCondition(RedEnveLopeItem reli) {
		return redEnveLopeItemMapper.selectByCondition(reli);
	}

}
