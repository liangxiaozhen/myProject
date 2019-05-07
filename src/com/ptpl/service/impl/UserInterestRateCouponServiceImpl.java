package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.UserInterestRateCouponMapper;
import com.ptpl.model.UserInterestRateCoupon;
import com.ptpl.service.UserInterestRateCouponServiceI;

public class UserInterestRateCouponServiceImpl implements UserInterestRateCouponServiceI{
	
	@Autowired
	UserInterestRateCouponMapper userInterestRateCouponMapper;

	@Override
	public int insert(UserInterestRateCoupon record) {
		return userInterestRateCouponMapper.insert(record);
	}

	@Override
	public int insertSelective(UserInterestRateCoupon record) {
		return userInterestRateCouponMapper.insertSelective(record);
	}

	@Override
	public List<UserInterestRateCoupon> findCouponList(UserInterestRateCoupon coupon) {
		return userInterestRateCouponMapper.findCouponList(coupon);
	}

	@Override
	public UserInterestRateCoupon findCouponDetailById(BigDecimal id) {
		return userInterestRateCouponMapper.findCouponDetailById(id);
	}

	@Override
	public List<UserInterestRateCoupon> findIsUseByBaseid(UserInterestRateCoupon coupon) {
		return userInterestRateCouponMapper.findIsUseByBaseid(coupon);
	}
	
	@Override
	public List<UserInterestRateCoupon> findOverdueByBaseid(UserInterestRateCoupon coupon) {
		return userInterestRateCouponMapper.findOverdueByBaseid(coupon);
	}

	@Override
	public int updateByPrimaryKeySelective(UserInterestRateCoupon record) {
		return userInterestRateCouponMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * @author pxl
	 * 将券作废掉
	 */
	public int updataCouponById(UserInterestRateCoupon coupon) {
		return userInterestRateCouponMapper.updataCouponById(coupon);
	}
	
	public UserInterestRateCoupon findUserInterestRateCouponById(BigDecimal id) {
		return userInterestRateCouponMapper.findUserInterestRateCouponById(id);
	}

	/**
	 * 根据用户id来查询用户的加息券
	 */
	public List<UserInterestRateCoupon> findCouponByBaseId(UserInterestRateCoupon uirc) {
		return userInterestRateCouponMapper.findCouponByBaseId(uirc);
	}

	/**
	 * 查找所有的记录
	 */
	public List<UserInterestRateCoupon> findUserInterestRateCoupon() {
		return userInterestRateCouponMapper.findUserInterestRateCoupon();
	}

}
