package com.ptpl.mapper;

import com.ptpl.model.TenderFeiType;

import java.math.BigDecimal;
import java.util.List;

public interface TenderFeiTypeMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(TenderFeiType record);

    int insertSelective(TenderFeiType record);

    TenderFeiType selectByPrimaryKey(BigDecimal id);

    List<TenderFeiType> selectAllTenderFeiType();

    int updateByPrimaryKeySelective(TenderFeiType record);

    int updateByPrimaryKey(TenderFeiType record);
}