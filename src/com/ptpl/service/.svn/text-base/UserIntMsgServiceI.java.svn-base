package com.ptpl.service;

import com.ptpl.model.UserIntMsg;

import java.math.BigDecimal;
import java.util.List;

/**
 * Date: 2017/3/13
 * author: Ywp
 * Description: 用户站内消息
 * Parameter:
 */
public interface UserIntMsgServiceI {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(UserIntMsg record);

    int insertSelective(UserIntMsg record);

    UserIntMsg selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(UserIntMsg record);

    int updateByPrimaryKey(UserIntMsg record);

    List<UserIntMsg> selectByBaseId(BigDecimal baseid);
}
