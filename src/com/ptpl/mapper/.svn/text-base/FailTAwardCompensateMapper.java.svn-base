package com.ptpl.mapper;

import com.ptpl.model.FailTAwardCompensate;
import java.math.BigDecimal;
import java.util.List;

public interface FailTAwardCompensateMapper extends BaseMapper<FailTAwardCompensate> {
	//删除
    int deleteByPrimaryKey(BigDecimal id);
    //添加
    int insertSelective(FailTAwardCompensate record);
    //根据id查信息
    FailTAwardCompensate selectByPrimaryKey(BigDecimal id);
    //更新
    int updateByPrimaryKeySelective(FailTAwardCompensate record);
    //更新
    int updateByPrimaryKey(FailTAwardCompensate record);
    
    //根据tid查询信息
    List<FailTAwardCompensate> selectfailAwardBytid(BigDecimal tid);
    
    List<String> selectAugradesByid(BigDecimal tid);
}