package com.ptpl.service.impl;

import com.ptpl.mapper.OftenLoanListMapper;
import com.ptpl.model.OftenLoanList;
import com.ptpl.service.OftenLoanListServiceI;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */
public class OftenLoanListServiceImpl implements OftenLoanListServiceI {
    @Autowired
    private OftenLoanListMapper oftenLoanListMapper;
    @Override
    public int deleteByPrimaryKey(BigDecimal id) {
        return oftenLoanListMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OftenLoanList record) {
        return oftenLoanListMapper.insert(record);
    }

    @Override
    public int insertSelective(OftenLoanList record) {
        return oftenLoanListMapper.insertSelective(record);
    }

    @Override
    public OftenLoanList selectByPrimaryKey(BigDecimal id) {
        return oftenLoanListMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<OftenLoanList> selectOftenLoanListByCondition(OftenLoanList oftenLoanList) {
        return oftenLoanListMapper.selectOftenLoanListByCondition(oftenLoanList);
    }

    @Override
    public int updateByPrimaryKeySelective(OftenLoanList record) {
        return oftenLoanListMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OftenLoanList record) {
        return oftenLoanListMapper.updateByPrimaryKey(record);
    }
}
