package com.ptpl.mapper;

import com.ptpl.model.UserMsgSet;
import java.math.BigDecimal;
import java.util.List;

public interface UserMsgSetMapper {
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