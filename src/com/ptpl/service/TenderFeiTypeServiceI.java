package com.ptpl.service;


import com.ptpl.model.TenderFeiType;

import java.math.BigDecimal;
import java.util.List;

public interface TenderFeiTypeServiceI {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(TenderFeiType record);

    int insertSelective(TenderFeiType tenderFeiType);

    TenderFeiType selectByPrimaryKey(BigDecimal id);

    List<TenderFeiType> selectAllTenderFeiType();

    int updateByPrimaryKeySelective(TenderFeiType tenderFeiType);

    int updateByPrimaryKey(TenderFeiType tenderFeiType);
}
