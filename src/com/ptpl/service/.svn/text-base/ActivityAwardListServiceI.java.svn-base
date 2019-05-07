package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ptpl.model.ActivityAwardList;

public interface ActivityAwardListServiceI {
	//查询所有未审核信息
	List<ActivityAwardList> getSelectstatus();
		
	//审核信息
	int update(ActivityAwardList activityawardList);
	
	//添加中奖信息
	int insert (ActivityAwardList activityawardList);
	
	//根据ID查单条详细信息
	List<ActivityAwardList> selectByCondition(ActivityAwardList activityawardList);
	
	//根据ID查对象
	ActivityAwardList getselectById(BigDecimal id);
	
	//根据奖品编号查奖品信息
	ActivityAwardList getselectAwardNo(String awardno);
	
	/*
	 * pxl  根据id更新奖品的发放状态
	 * @date 2016-11-8
	 */
	int updateAwardStatusById(ActivityAwardList aals);
	 
	 /*
	  * pxl  根据用户名查询该用户的获奖信息
	  * @date 2016-11-8
	  */
	 List<ActivityAwardList> getAwardInfo(ActivityAwardList aals);
	 
	 /*
	  * pxl  用户名填写完备注信息后并确定,需要将备注中的收货信息存进数据库
	  * @date 2016-11-8
	  */
	 int sureAwardRemark(ActivityAwardList aals);
	 
	 /*
	  * pxl  用户的收货信息有问题，需要用户再次确认
	  * @date 2016-11-8
	  */
	 int updateAwardStatusAndRemarkById(ActivityAwardList aals);
		
	/**
	 * @Title: insertSelective
	 * @Description: TODO(新增获奖名单（参数可选）)
	 * @param activityawardList
	 * @return int
	 */
	int insertSelective (ActivityAwardList activityawardList);
	
	/**
	 * @Title: selectTimesAndNumber
	 * @Description: TODO(统计获奖人次和人数)
	 * @param actno
	 * @return Map<String,BigDecimal>
	 */
	Map<String,BigDecimal> selectTimesAndNumber (String actno);
	
	/**
	 * 获取除待审核外的获奖名单
	 * @param aal
	 * @return
	 */
	List<ActivityAwardList> selectByConditionTwo(ActivityAwardList aal);

	/**
	 * 根据奖品id获取奖品属性
	 * @param bigDecimal
	 * @return
	 */
	ActivityAwardList getAwardattributeById(BigDecimal bigDecimal);

	/**
	 * 根据id联合奖品表查询
	 */
	ActivityAwardList getActivityAwardListById(BigDecimal bigDecimal);
	 
}
