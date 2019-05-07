package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.WithdrawsCashCouponMapper;
 import com.ptpl.model.WithdrawsCashCoupon;
import com.ptpl.service.WithdrawsCashCouponServiceI;
/**
 * 
 * 提抵卷活动规则业务层
 * WithdrawsCashCouponServiceI
 * 创建人:chenjiaming
 * 时间：2016年07月07日 16:15:49
 * @version 1.0.0
 *
 */
public class WithdrawsCashCouponServiceImpl implements WithdrawsCashCouponServiceI{
	
	@Autowired
	private WithdrawsCashCouponMapper withdrawsCashCouponMapper;
	
	@Override
	public int insert(WithdrawsCashCoupon withdrawsCashCoupon) {
 		return withdrawsCashCouponMapper.insert(withdrawsCashCoupon);
	}

	@Override
	public int insertSelective(WithdrawsCashCoupon withdrawsCashCoupon) {
 		return withdrawsCashCouponMapper.insertSelective(withdrawsCashCoupon);
	}

	@Override
	public int deleteById(WithdrawsCashCoupon withdrawsCashCoupon) {
 		return withdrawsCashCouponMapper.deleteById(withdrawsCashCoupon);
	}

	@Override
	public int updateByPrimaryKeySelective(WithdrawsCashCoupon withdrawsCashCoupon) {
 		return withdrawsCashCouponMapper.updateByPrimaryKeySelective(withdrawsCashCoupon);
	}

	@Override
	public List<WithdrawsCashCoupon> findWithdrawsCashCoupons(WithdrawsCashCoupon withdrawsCashCoupon) {
 		return withdrawsCashCouponMapper.findWithdrawsCashCoupons(withdrawsCashCoupon);
	}

	@Override
	public WithdrawsCashCoupon selectByPrimaryKey(BigDecimal id) {
 		return withdrawsCashCouponMapper.selectByPrimaryKey(id);
	}

}
