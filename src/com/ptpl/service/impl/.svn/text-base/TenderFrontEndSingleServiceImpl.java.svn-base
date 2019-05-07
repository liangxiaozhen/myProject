package com.ptpl.service.impl;

import com.ptpl.mapper.TenderFrontEndSingleMapper;
import com.ptpl.model.TenderFrontEndSingle;
import com.ptpl.service.TenderFrontEndSingleServiceI;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */
public class TenderFrontEndSingleServiceImpl implements TenderFrontEndSingleServiceI {

    @Autowired
    private TenderFrontEndSingleMapper tenderFrontEndSingleMapper;
    @Override
    public int deleteByPrimaryKey(BigDecimal id) {
        return tenderFrontEndSingleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TenderFrontEndSingle record) {
        return tenderFrontEndSingleMapper.insert(record);
    }

    @Override
    public int insertSelective(TenderFrontEndSingle record) {
        return tenderFrontEndSingleMapper.insertSelective(record);
    }

    @Override
    public TenderFrontEndSingle selectByPrimaryKey(BigDecimal id) {
        return tenderFrontEndSingleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TenderFrontEndSingle> selectByTno(String tno) {
        return tenderFrontEndSingleMapper.selectByTno(tno);
    }

    @Override
    public int updateByPrimaryKeySelective(TenderFrontEndSingle record) {
        return tenderFrontEndSingleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TenderFrontEndSingle record) {
        return tenderFrontEndSingleMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<TenderFrontEndSingle> selectByCondition(TenderFrontEndSingle condition) {
        return tenderFrontEndSingleMapper.selectByCondition(condition);
    }

    @Override
    public Integer selectMaxSno(String tno) {
        return tenderFrontEndSingleMapper.selectMaxSno(tno);
    }

    @Override
    public List<TenderFrontEndSingle> selectByCondition2(String tno) {
        return tenderFrontEndSingleMapper.selectByCondition2(tno);
    }

    @Override
    public List<TenderFrontEndSingle> selectContentIsNotNull(String tno) {
        return tenderFrontEndSingleMapper.selectContentIsNotNull(tno);
    }


}
