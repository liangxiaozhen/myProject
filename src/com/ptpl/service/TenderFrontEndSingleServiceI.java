package com.ptpl.service;

import com.ptpl.model.TenderFrontEndSingle;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */
public interface TenderFrontEndSingleServiceI {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(TenderFrontEndSingle record);

    int insertSelective(TenderFrontEndSingle record);

    TenderFrontEndSingle selectByPrimaryKey(BigDecimal id);

    List<TenderFrontEndSingle> selectByTno(String tno);

    int updateByPrimaryKeySelective(TenderFrontEndSingle record);

    int updateByPrimaryKey(TenderFrontEndSingle record);

    List<TenderFrontEndSingle> selectByCondition(TenderFrontEndSingle condition);

    //获取最后一个排序号
    Integer selectMaxSno(String tno);

    List<TenderFrontEndSingle> selectByCondition2(String tno);

    List<TenderFrontEndSingle> selectContentIsNotNull(String tno);


}
