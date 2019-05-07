package com.ptpl.service;

import com.ptpl.model.UserMsgSet;

import java.math.BigDecimal;
import java.util.List;

/**
 * Date: 2017/3/13
 * author: Ywp
 * Description: 用户消息通知设置
 * Parameter:
 */
public interface UserMsgSetServiceI {
    int deleteByPrimaryKey(BigDecimal id);

    int deleteByMsgType(Short msgtype);

    int insert(UserMsgSet record);

    int insertSelective(UserMsgSet record);

    UserMsgSet selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(UserMsgSet record);

    int updateByPrimaryKey(UserMsgSet record);

    List<UserMsgSet> selectAll();

    List<UserMsgSet> selectByBaseId(BigDecimal id);

    List<UserMsgSet> selectBySelective(UserMsgSet record);
}
