package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.UserOutsideAwardMapper;
import com.ptpl.model.UserOutsideAward;
import com.ptpl.service.UserOutsideAwardServiceI;

/**
 * @author liuj
 * @description:TODO(用户站外奖品服务层)
 * @date:PM 2016/8/19 17:00
 * @version 1.0
 */
public class UserOutsideAwardServiceImpl implements UserOutsideAwardServiceI {

	@Autowired
    private UserOutsideAwardMapper outsideAwardMapper;	
	
	@Override
	public List<UserOutsideAward> getallUserAward(UserOutsideAward outsideAward) {
		return outsideAwardMapper.getallUserAward(outsideAward);
	}

	@Override
	public int insert(UserOutsideAward record) {
		return outsideAwardMapper.insert(record);
	}

	@Override
	public int insertSelective(UserOutsideAward record) {
		return outsideAwardMapper.insertSelective(record);
	}
	
	@Override
	public UserOutsideAward selectByPrimaryKey(BigDecimal id) {
		// TODO Auto-generated method stub
		return outsideAwardMapper.selectByPrimaryKey(id);
	}
	@Override
	public int updateByPrimaryKey(UserOutsideAward record) {
		// TODO Auto-generated method stub
		return outsideAwardMapper.updateByPrimaryKey(record);
	}
	@Override
	public int updateByPrimaryKeySelective(UserOutsideAward record) {		 
		return outsideAwardMapper.updateByPrimaryKeySelective(record);
	}
	
    @Override
    public int deleteByPrimaryKey(BigDecimal id) {
    	// TODO Auto-generated method stub
    	return outsideAwardMapper.deleteByPrimaryKey(id);
    }
    @Override
    public List<UserOutsideAward> selectAll() {
    	// TODO Auto-generated method stub
    	return outsideAwardMapper.selectAll();
    }
}
