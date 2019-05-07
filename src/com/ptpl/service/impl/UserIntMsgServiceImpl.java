package com.ptpl.service.impl;

import com.ptpl.mapper.UserIntMsgMapper;
import com.ptpl.model.UserIntMsg;
import com.ptpl.service.UserIntMsgServiceI;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * Date: 2017/3/13
 * author: Ywp
 * Description: 用户站内消息
 * Parameter:
 */
public class UserIntMsgServiceImpl implements UserIntMsgServiceI {
    @Autowired
    private UserIntMsgMapper userIntMsgMapper;
    @Override
    public int deleteByPrimaryKey(BigDecimal id) {
        return userIntMsgMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserIntMsg record) {
        return userIntMsgMapper.insert(record);
    }

    @Override
    public int insertSelective(UserIntMsg record) {
        return userIntMsgMapper.insertSelective(record);
    }

    @Override
    public UserIntMsg selectByPrimaryKey(BigDecimal id) {
        return userIntMsgMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserIntMsg record) {
        return userIntMsgMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserIntMsg record) {
        return userIntMsgMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<UserIntMsg> selectByBaseId(BigDecimal baseid) {
        return userIntMsgMapper.selectByBaseId(baseid);
    }


}
