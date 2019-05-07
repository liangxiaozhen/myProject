package com.ptpl.service.impl;

import com.ptpl.mapper.UserCommonMaterialMapper;
import com.ptpl.model.UserCommonMaterial;
import com.ptpl.service.UserCommonMaterialServiceI;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

public class UserCommonMaterialServiceImpl implements UserCommonMaterialServiceI {

	@Autowired
	private UserCommonMaterialMapper usermapper;
	
	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return usermapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserCommonMaterial record) {
		return usermapper.insert(record);
	}

	@Override
	public int insertSelective(UserCommonMaterial record) {
		return usermapper.insertSelective(record);
	}

	@Override
	public UserCommonMaterial selectByPrimaryKey(BigDecimal id) {
		return usermapper.selectByPrimaryKey(id);
	}

	@Override
	public List<UserCommonMaterial> selectByTenderitemId(BigDecimal id) {
		return usermapper.selectByTenderitemId(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserCommonMaterial record) {
		return usermapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserCommonMaterial record) {
		return usermapper.updateByPrimaryKey(record);
	}

	@Override
	public List<UserCommonMaterial> selectAllByBaseid(BigDecimal baseid) {
		return usermapper.selectAllByBaseid(baseid);
	}

	@Override
	public int delectBybaseid(BigDecimal baseid) {
		return usermapper.delectBybaseid(baseid);
	}

	@Override
	public List<UserCommonMaterial> selectByLIQNo(String liqno) {
		return usermapper.selectByLIQNo(liqno);
	}

}
