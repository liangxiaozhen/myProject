package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.UserBonusPointsMapper;
import com.ptpl.model.UserBonusPoints;
import com.ptpl.service.UserBonusPointsServiceI;

public class UserBonusPointsServiceImpl implements UserBonusPointsServiceI{
	
	@Autowired
	UserBonusPointsMapper userBonusPointsMapper;
	
	@Override
	public int insertSelective(UserBonusPoints record){
		return userBonusPointsMapper.insertSelective(record);
	}
	
	@Override
	public List<UserBonusPoints> findBonusPointsList(UserBonusPoints points){
		return userBonusPointsMapper.findBonusPointsList(points);
	}
	
	@Override
	public UserBonusPoints findPointsDetailById(BigDecimal id){
		return userBonusPointsMapper.findPointsDetailById(id);
	}

	@Override
	public List<UserBonusPoints> findPointsByBaseid(UserBonusPoints points) {
		return userBonusPointsMapper.findPointsByBaseid(points);
	}

	@Override
	public int updateByPrimaryKeySelective(UserBonusPoints record) {
		return userBonusPointsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int insert(UserBonusPoints record) {
		// TODO Auto-generated method stub
		return userBonusPointsMapper.insert(record);
	}

	@Override
	public List<UserBonusPoints> getfindBonusPointsList(UserBonusPoints ubp) {
		// TODO Auto-generated method stub
		return userBonusPointsMapper.getfindBonusPointsList(ubp);
	}

	@Override
	public UserBonusPoints getUserBounsPointsByLoginName(String loginname) {
		// TODO Auto-generated method stub
		return userBonusPointsMapper.getUserBounsPointsByLoginName(loginname);
	}

	/**
	 * 根据时间段来查询积分记录
	 */
	public List<UserBonusPoints> findPointsByPeriod(UserBonusPoints ubp) {
		return userBonusPointsMapper.findPointsByPeriod(ubp);
	}

}
