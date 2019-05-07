package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.ptpl.mapper.UserPostAddressMapper;
import com.ptpl.model.UserPostAddress;
import com.ptpl.service.UserPostAddressI;

public class UserPostAddressImpl implements UserPostAddressI {
	
	@Autowired
    private UserPostAddressMapper postmapper;
	
	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return postmapper.deleteByPrimaryKey(id);
	}
	@Override
	public int insert(UserPostAddress record) {
		return postmapper.insert(record);
	}
	@Override
	public int insertSelective(UserPostAddress record) {
		return postmapper.insertSelective(record);
	}
	@Override
	public UserPostAddress selectByPrimaryKey(BigDecimal id) {
		return postmapper.selectByPrimaryKey(id);
	}
	@Override
	public int updateByPrimaryKey(UserPostAddress record) {
		return postmapper.updateByPrimaryKey(record);
	}
	@Override
	public int updateByPrimaryKeySelective(UserPostAddress record) {
		return postmapper.updateByPrimaryKeySelective(record);
	}
	 @Override
	public List<UserPostAddress> selectByuserID(BigDecimal id) {
		return postmapper.selectByuserID(id);
	}
	 @Override
	public List<UserPostAddress> selectAllAddress(UserPostAddress address) {
		return postmapper.selectAllAddress(address);
	}
	
}
