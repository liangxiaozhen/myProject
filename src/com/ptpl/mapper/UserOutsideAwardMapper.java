package com.ptpl.mapper;

import com.ptpl.model.UserOutsideAward;
import java.math.BigDecimal;
import java.util.List;

public interface UserOutsideAwardMapper {
	
	/**
	* @Title: delete
	* @Description:根据id删除获奖信息
	* @return int 返回类型
	*/ 
    int deleteByPrimaryKey(BigDecimal id);

    /**
	* @Title: insert
	* @Description:添加用户获奖信息
	* @return int 返回类型
	*/ 
    int insert(UserOutsideAward record);

    /**
   	* @Title: selectByPrimaryKey
   	* @Description:有选择性的添加用户获奖信息
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
   	* @Title: getallUserAward
   	* @Description:获取所有的用户获奖信息
   	* @return list 返回类型
   	*/ 
    List<UserOutsideAward> getallUserAward(UserOutsideAward outsideAward);
    
    List<UserOutsideAward> selectAll();
}