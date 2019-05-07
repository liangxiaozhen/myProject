package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.UserRedEnvelopeMapper;
import com.ptpl.model.UserRedEnvelope;
import com.ptpl.service.UserRedEnvelopeServiceI;

public class UserRedEnvelopeServiceImpl implements UserRedEnvelopeServiceI{
	
	@Autowired
	UserRedEnvelopeMapper userRedEnvelopeMapper;
	
	@Override
	public List<UserRedEnvelope> findRedEnvelopeList(UserRedEnvelope record){
		return userRedEnvelopeMapper.findRedEnvelopeList(record);
	}
	
	@Override
	public UserRedEnvelope selectByPrimaryKey(BigDecimal id){
		return userRedEnvelopeMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<UserRedEnvelope> selectByBaseid(UserRedEnvelope red) {
		return userRedEnvelopeMapper.selectByBaseid(red);
	}

	@Override
	public int insertSelective(UserRedEnvelope red) {
		return userRedEnvelopeMapper.insertSelective(red);
	}

	@Override
	public int updateByPrimaryKeySelective(UserRedEnvelope red) {
		return userRedEnvelopeMapper.updateByPrimaryKeySelective(red);
	}

	@Override
	public int insert(UserRedEnvelope red) {
		return userRedEnvelopeMapper.insert(red);
	}

	@Override
	public List<UserRedEnvelope> getselectAll(UserRedEnvelope redEnv) {
		return userRedEnvelopeMapper.getselectAll(redEnv);
	}

	/**
	 * 红包作废
	 * @param redEnv
	 */
	public int updateRedEnvById(UserRedEnvelope redEnv) {
		return userRedEnvelopeMapper.updateRedEnvById(redEnv);
	}

	public UserRedEnvelope findUserRedEnvelopeById(BigDecimal id) {
		return userRedEnvelopeMapper.findUserRedEnvelopeById(id);
	}


	/**
     * 查找时间段已领取的红包
     */
	public List<UserRedEnvelope> findUserRedEnvelope(UserRedEnvelope ure) {
		return userRedEnvelopeMapper.findUserRedEnvelope(ure);
	}

	/**
	 *查找最近三个月的已领取的红包
	 */
	public List<UserRedEnvelope> findRedEnvelopeListThreeMonths(UserRedEnvelope ure) {
		return userRedEnvelopeMapper.findRedEnvelopeListThreeMonths(ure);
	}
	

	@Override
	public List<UserRedEnvelope> getUserRedBaoByLoginName(String loginname) {
		// TODO Auto-generated method stub
		return userRedEnvelopeMapper.getUserRedBaoByLoginName(loginname);
	}

	/**
	 * 判断领取后的红包是否到期
	 */
	public List<UserRedEnvelope> findRedEnvelopeListByStatus() {
		return userRedEnvelopeMapper.findRedEnvelopeListByStatus();
	}

}
