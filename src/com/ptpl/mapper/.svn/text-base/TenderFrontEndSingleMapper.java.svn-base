package com.ptpl.mapper;

import com.ptpl.model.TenderFrontEndSingle;

import java.math.BigDecimal;
import java.util.List;

public interface TenderFrontEndSingleMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(TenderFrontEndSingle record);

    int insertSelective(TenderFrontEndSingle record);

    TenderFrontEndSingle selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(TenderFrontEndSingle record);

    int updateByPrimaryKey(TenderFrontEndSingle record);

    List<TenderFrontEndSingle> selectByCondition(TenderFrontEndSingle condition);

    List<TenderFrontEndSingle> selectByTno(String tno);

    //获取最后一个排序号
    Integer selectMaxSno(String tno);

    //查询来源为引用且内容不为空的对象 合并查询 来源为新增应用范围为通用的对象  合并查询  来源为新增应用范围为唯一的对象
    List<TenderFrontEndSingle> selectByCondition2(String tno);

    List<TenderFrontEndSingle> selectContentIsNotNull(String tno);


}