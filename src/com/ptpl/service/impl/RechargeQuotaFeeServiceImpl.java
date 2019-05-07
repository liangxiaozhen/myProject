package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.RechargeQuotaFeeMapper;
import com.ptpl.mapper.RechargeSNLLinkMapper;
import com.ptpl.model.RechargeQuotaFee;
import com.ptpl.service.RechargeQuotaFeeServiceI;

public class RechargeQuotaFeeServiceImpl implements RechargeQuotaFeeServiceI{
	@Autowired
	private RechargeQuotaFeeMapper rechargeQuotaFeeMapper;
	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return rechargeQuotaFeeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RechargeQuotaFee record) {
		return rechargeQuotaFeeMapper.insert(record);
	}

	@Override
	public int insertSelective(RechargeQuotaFee record) {
		return rechargeQuotaFeeMapper.insertSelective(record);
	}

	@Override
	public RechargeQuotaFee selectByPrimaryKey(BigDecimal id) {
		return rechargeQuotaFeeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RechargeQuotaFee record) {
		return rechargeQuotaFeeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RechargeQuotaFee record) {
		return rechargeQuotaFeeMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<RechargeQuotaFee> selectByrrid(BigDecimal id) {
		return rechargeQuotaFeeMapper.selectByrrid(id);
	}

}
