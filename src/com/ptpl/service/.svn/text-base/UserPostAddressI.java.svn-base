package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;
import com.ptpl.model.UserPostAddress;

/**
	* @author liuj
	* @Description:用户奖品邮寄地址抽象类
	* @date 2016/8/69 AM:10:13
	*/ 
public interface UserPostAddressI {
	    int deleteByPrimaryKey(BigDecimal id);

	    int insert(UserPostAddress record);
        
	    //添加用户邮寄地址
	    int insertSelective(UserPostAddress record);

	    UserPostAddress selectByPrimaryKey(BigDecimal id);

	    int updateByPrimaryKeySelective(UserPostAddress record);

	    int updateByPrimaryKey(UserPostAddress record);
	    //根据用户ID查信息
	    List<UserPostAddress> selectByuserID(BigDecimal id);
	    //查询所有的信息
	    List<UserPostAddress> selectAllAddress(UserPostAddress address);
}
