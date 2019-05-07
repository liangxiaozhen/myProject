package com.ptpl.mapper;

import com.ptpl.model.UserIntMsg;

import java.math.BigDecimal;
import java.util.List;

public interface UserIntMsgMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(UserIntMsg record);

    int insertSelective(UserIntMsg record);

    UserIntMsg selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(UserIntMsg record);

    int updateByPrimaryKey(UserIntMsg record);

    List<UserIntMsg> selectByBaseId(BigDecimal baseid);
}