package com.ptpl.service.impl;

import com.ptpl.mapper.TenderFeiTypeMapper;
import com.ptpl.model.TenderFeiType;
import com.ptpl.service.TenderFeiTypeServiceI;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 */
public class TenderFeiTypeServiceImpl implements TenderFeiTypeServiceI {
    @Autowired
    private TenderFeiTypeMapper tenderFeiTypeMapper;
    @Override
    public int deleteByPrimaryKey(BigDecimal id) {
        return tenderFeiTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TenderFeiType tenderFeiType) {
        return tenderFeiTypeMapper.insert(tenderFeiType);
    }

    @Override
    public int insertSelective(TenderFeiType tenderFeiType) {
        return tenderFeiTypeMapper.insertSelective(tenderFeiType);
    }

    @Override
    public TenderFeiType selectByPrimaryKey(BigDecimal id) {
        return tenderFeiTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TenderFeiType> selectAllTenderFeiType() {
        return tenderFeiTypeMapper.selectAllTenderFeiType();
    }

    @Override
    public int updateByPrimaryKeySelective(TenderFeiType tenderFeiType) {
        return tenderFeiTypeMapper.updateByPrimaryKeySelective(tenderFeiType);
    }

    @Override
    public int updateByPrimaryKey(TenderFeiType tenderFeiType) {
        return tenderFeiTypeMapper.updateByPrimaryKey(tenderFeiType);
    }
}
