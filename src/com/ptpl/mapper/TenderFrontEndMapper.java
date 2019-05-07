package com.ptpl.mapper;

import com.ptpl.model.TenderFrontEnd;

import java.math.BigDecimal;
import java.util.List;

public interface TenderFrontEndMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(TenderFrontEnd record);

    int insertSelective(TenderFrontEnd record);

    TenderFrontEnd selectByPrimaryKey(BigDecimal id);

    TenderFrontEnd selectByInfono(String infono);

    int updateByPrimaryKeySelective(TenderFrontEnd record);

    int updateByPrimaryKey(TenderFrontEnd record);

    List<TenderFrontEnd> selectByCondition(TenderFrontEnd condition);

    //获取最后t一个编号
    String selectLastInfoNo();

}