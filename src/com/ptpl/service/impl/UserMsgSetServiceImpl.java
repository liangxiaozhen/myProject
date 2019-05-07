package com.ptpl.service.impl;

import com.ptpl.mapper.UserMsgSetMapper;
import com.ptpl.model.UserMsgSet;
import com.ptpl.service.UserMsgSetServiceI;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * Date: 2017/3/13
 * author: Ywp
 * Description: 用户消息通知设置
 * Parameter:
 */
public class UserMsgSetServiceImpl implements UserMsgSetServiceI {
    @Autowired
    private UserMsgSetMapper userMsgSetMapper;

    @Override
    public int deleteByPrimaryKey(BigDecimal id) {
        return userMsgSetMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByMsgType(Short msgtype) {
        return userMsgSetMapper.deleteByMsgType(msgtype);
    }

    @Override
    public int insert(UserMsgSet record) {
        return userMsgSetMapper.insert(record);
    }

    @Override
    public int insertSelective(UserMsgSet record) {
        return userMsgSetMapper.insertSelective(record);
    }

    @Override
    public UserMsgSet selectByPrimaryKey(BigDecimal id) {
        return userMsgSetMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserMsgSet record) {
        return userMsgSetMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserMsgSet record) {
        return userMsgSetMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<UserMsgSet> selectAll() {
        return userMsgSetMapper.selectAll();
    }

    @Override
    public List<UserMsgSet> selectByBaseId(BigDecimal id) {
        return userMsgSetMapper.selectByBaseId(id);

    }

    @Override
    public List<UserMsgSet> selectBySelective(UserMsgSet record) {
        return userMsgSetMapper.selectBySelective(record);
    }

}
