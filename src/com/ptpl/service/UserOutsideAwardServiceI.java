package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.ActivityAwardList;
import com.ptpl.model.UserOutsideAward;

public interface UserOutsideAwardServiceI {
	/**
   	* @Title: getallUserAward
   	* @Description:获取所有的用户获奖信息
   	* @return list 返回类型
   	*/ 
    List<UserOutsideAward> getallUserAward(UserOutsideAward outsideAward);
    /**
   	* @Title: selectByPrimaryKey
   	* @Description:根据id查询返回信息
   	* @return UserOutsideAward 返回类型
   	*/ 
    int insert(UserOutsideAward record);
    /**
   	* @Title: updateByPrimaryKeySelective
   	* @Description:有选择性的跟新用户获奖信息
   	* @return int 返回类型
   	*/ 
    int insertSelective(UserOutsideAward record);
    
    /**
   	* @Title: selectByPrimaryKey
   	* @Description:根据id查询返回信息
   	* @return UserOutsideAward 返回类型
   	*/ 
    UserOutsideAward selectByPrimaryKey(BigDecimal id);

    /**
   	* @Title: updateByPrimaryKeySelective
   	* @Description:有选择性的跟新用户获奖信息
   	* @return int 返回类型
   	*/ 
    int updateByPrimaryKeySelective(UserOutsideAward record);
    
    /**
   	* @Title: updateByPrimaryKey
   	* @Description:跟新用户获奖信息
   	* @return int 返回类型
   	*/ 
    int updateByPrimaryKey(UserOutsideAward record);
    
    /**
	* @Title: delete
	* @Description:根据id删除获奖信息
	* @return int 返回类型
	*/ 
    int deleteByPrimaryKey(BigDecimal id);
    
    List<UserOutsideAward> selectAll();
    

  }
