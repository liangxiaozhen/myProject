package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.ActivityAwardListMapper;
import com.ptpl.model.ActivityAwardList;
import com.ptpl.service.ActivityAwardListServiceI;

public class ActivityAwardListServiceImpl implements ActivityAwardListServiceI {

	@Autowired
	ActivityAwardListMapper activityawardListMapper;
	
	@Override
	public List<ActivityAwardList> getSelectstatus() {
		// TODO Auto-generated method stub
		return activityawardListMapper.getSelectstatus();
	}

	@Override
	public int update(ActivityAwardList activityawardList) {
		// TODO Auto-generated method stub
		return activityawardListMapper.update(activityawardList);
	}

	@Override
	public int insert(ActivityAwardList activityawardList) {
		// TODO Auto-generated method stub
		return activityawardListMapper.insert(activityawardList);
	}

	@Override
	public List<ActivityAwardList> selectByCondition(ActivityAwardList activityawardList) {
		// TODO Auto-generated method stub
		return activityawardListMapper.selectByCondition(activityawardList);
	}

	@Override
	public ActivityAwardList getselectById(BigDecimal id) {
		// TODO Auto-generated method stub
		return activityawardListMapper.getselectById(id);
	}

	@Override
	public ActivityAwardList getselectAwardNo(String awardno) {
		// TODO Auto-generated method stub
		return activityawardListMapper.getselectAwardNo(awardno);
	}

	/*
	 * pxl  根据id更新奖品的发放状态
	 * @date 2016-11-8
	 */
	public int updateAwardStatusById(ActivityAwardList aals) {
		return activityawardListMapper.updateAwardStatusById(aals);
	}

	/*
	 * pxl  根据用户名查询该用户的获奖信息
	 * @date 2016-11-8
	 */
	public List<ActivityAwardList> getAwardInfo(ActivityAwardList aals) {
		return activityawardListMapper.getAwardInfo(aals);
	}

	 /*
	  * pxl  用户名填写完备注信息后并确定,需要将备注中的收货信息存进数据库
	  * @date 2016-11-8
	  */
	public int sureAwardRemark(ActivityAwardList aals) {
		return activityawardListMapper.sureAwardRemark(aals);
	}

	 /*
	  * pxl  用户的收货信息有问题，需要用户再次确认
	  * @date 2016-11-8
	  */
	public int updateAwardStatusAndRemarkById(ActivityAwardList aals) {
		return activityawardListMapper.updateAwardStatusAndRemarkById(aals);
	}

	@Override
	public int insertSelective(ActivityAwardList activityawardList) {
		return activityawardListMapper.insertSelective(activityawardList);
	}

	@Override
	public Map<String, BigDecimal> selectTimesAndNumber(String actno) {
		return activityawardListMapper.selectTimesAndNumber(actno);
	}

	@Override
	public List<ActivityAwardList> selectByConditionTwo(ActivityAwardList aal) {
		
		return activityawardListMapper.selectByConditionTwo(aal);
	}

	/**
	 * 根据奖品id获取奖品属性
	 * @param bigDecimal
	 * @return
	 */
	public ActivityAwardList getAwardattributeById(BigDecimal bigDecimal) {
		return activityawardListMapper.getAwardattributeById(bigDecimal);
	}

	/**
	 * 根据id联合奖品表查询
	 */
	public ActivityAwardList getActivityAwardListById(BigDecimal bigDecimal) {
		return activityawardListMapper.getActivityAwardListById(bigDecimal);
	}
}
