package com.ptpl.mapper;

import com.ptpl.model.AllTradeFileDetail;

import java.math.BigDecimal;
import java.util.List;

public interface AllTradeFileDetailMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(AllTradeFileDetail record);

    int insertSelective(AllTradeFileDetail record);

    AllTradeFileDetail selectByPrimaryKey(BigDecimal id);
    
    List<AllTradeFileDetail> listAllTradeFileDetail(AllTradeFileDetail record);

    int updateByPrimaryKeySelective(AllTradeFileDetail record);

    int updateByPrimaryKey(AllTradeFileDetail record);
}