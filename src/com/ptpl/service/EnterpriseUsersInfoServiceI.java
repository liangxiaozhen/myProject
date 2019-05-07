package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.EnterpriseUsersInfo;

public interface EnterpriseUsersInfoServiceI {
	   
	    int deleteByPrimaryKey(BigDecimal id);

	    int insert(EnterpriseUsersInfo record);

	    int insertSelective(EnterpriseUsersInfo record);

	    EnterpriseUsersInfo selectByPrimaryKey(BigDecimal id);

	    int updateByPrimaryKeySelective(EnterpriseUsersInfo record);

	    int updateByPrimaryKey(EnterpriseUsersInfo record);
	    //获取担保企业的信息
	    List<EnterpriseUsersInfo> selectAll();
	    //通过baseid查询信息
	    List<EnterpriseUsersInfo> selectBybaseID(BigDecimal baseid);
}
