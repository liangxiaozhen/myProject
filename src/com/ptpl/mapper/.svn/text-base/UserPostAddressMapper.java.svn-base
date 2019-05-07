package com.ptpl.mapper;

import com.ptpl.model.UserPostAddress;
import java.math.BigDecimal;
import java.util.List;

public interface UserPostAddressMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(UserPostAddress record);

    int insertSelective(UserPostAddress record);

    UserPostAddress selectByPrimaryKey(BigDecimal id);
    
    //根据用户ID查信息
    List<UserPostAddress> selectByuserID(BigDecimal id);
    
    //查询所有的信息
    List<UserPostAddress> selectAllAddress(UserPostAddress address);

    int updateByPrimaryKeySelective(UserPostAddress record);

    int updateByPrimaryKey(UserPostAddress record);
}