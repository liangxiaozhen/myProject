package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.OverdueRecovery;

public interface OverdueRecoveryServiceI {
	//删除
    int deleteByPrimaryKey(BigDecimal id);
    //添加
    int insertSelective(OverdueRecovery record);
    //根据id查信息
    OverdueRecovery selectByPrimaryKey(BigDecimal id);
    //更新
    int updateByPrimaryKeySelective(OverdueRecovery record);
    //更新
    int updateByPrimaryKey(OverdueRecovery record);
    
    //根据tid查询信息
    List<OverdueRecovery> selectoverRecBytid(BigDecimal tid);
}
